package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.R;
import com.macro.mall.model.SmsHomeRecommendSubject;
import com.macro.mall.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页专题推荐管理Controller
 * Created by macro on 2018/11/6.
 */
@Controller
@Api(tags = "SmsHomeRecommendSubjectController")
@Tag(name = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService recommendSubjectService;

    @ApiOperation("添加首页推荐专题")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        int count = recommendSubjectService.create(homeRecommendSubjectList);
        if (count > 0) {
            return R.success(count);
        }
        return R.failed();
    }

    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendSubjectService.updateSort(id, sort);
        if (count > 0) {
            return R.success(count);
        }
        return R.failed();
    }

    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public R delete(@RequestParam("ids") List<Long> ids) {
        int count = recommendSubjectService.delete(ids);
        if (count > 0) {
            return R.success(count);
        }
        return R.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return R.success(count);
        }
        return R.failed();
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public R<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendSubject> homeRecommendSubjectList = recommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return R.success(CommonPage.restPage(homeRecommendSubjectList));
    }
}
