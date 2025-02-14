package io.dataease.controller.dataset;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.dataease.auth.annotation.DePermission;
import io.dataease.auth.annotation.DePermissions;
import io.dataease.base.domain.DatasetGroup;
import io.dataease.commons.constants.DePermissionType;
import io.dataease.commons.constants.ResourceAuthLevel;
import io.dataease.controller.request.dataset.DataSetGroupRequest;
import io.dataease.dto.dataset.DataSetGroupDTO;
import io.dataease.service.dataset.DataSetGroupService;
import io.dataease.service.dataset.ExtractDataService;
import io.dataease.service.kettle.KettleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author gin
 * @Date 2021/2/20 8:29 下午
 */
@Api(tags = "数据集：数据集组")
@ApiSupport(order = 40)
@RestController
@RequestMapping("dataset/group")
public class DataSetGroupController {
    @Resource
    private DataSetGroupService dataSetGroupService;
    @Resource
    private KettleService kettleService;

    @DePermissions(value = {
            @DePermission(type = DePermissionType.DATASET, value = "id"),
            @DePermission(type = DePermissionType.DATASET, value = "pid", level = ResourceAuthLevel.DATASET_LEVEL_MANAGE)
    }, logical = Logical.AND)
    @ApiOperation("保存")
    @PostMapping("/save")
    public DataSetGroupDTO save(@RequestBody DatasetGroup datasetGroup) throws Exception {
        return dataSetGroupService.save(datasetGroup);
    }

    @ApiIgnore
    @PostMapping("/tree")
    public List<DataSetGroupDTO> tree(@RequestBody DataSetGroupRequest datasetGroup) {
        return dataSetGroupService.tree(datasetGroup);
    }

    @ApiIgnore
    @PostMapping("/treeNode")
    public List<DataSetGroupDTO> treeNode(@RequestBody DataSetGroupRequest datasetGroup) {
        return dataSetGroupService.treeNode(datasetGroup);
    }

    @DePermission(type = DePermissionType.DATASET, level = ResourceAuthLevel.DATASET_LEVEL_MANAGE)
    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public void tree(@PathVariable String id) throws Exception {
        dataSetGroupService.delete(id);
    }

    @ApiIgnore
    @PostMapping("/getScene/{id}")
    public DatasetGroup getScene(@PathVariable String id) {
        return dataSetGroupService.getScene(id);
    }

    @ApiIgnore
    @PostMapping("/isKettleRunning")
    public boolean isKettleRunning() {
        return kettleService.isKettleRunning();
    }
}
