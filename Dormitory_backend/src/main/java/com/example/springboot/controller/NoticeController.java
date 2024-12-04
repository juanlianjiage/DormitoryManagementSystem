package com.example.springboot.controller;


import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.service.NoticeService;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    /**
     * 公告添加，并对所有学生发送邮件
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Notice notice) {

        noticeService.addNewNotice(notice);
//        try {
//
//        }catch (RuntimeException e){
//            log.error("由于邮件发送失败，请重新添加公告");
//            return Result.error("-1", "添加失败");
//        }
        return Result.success();

    }

    /**
     * 公告更新
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Notice notice) {
        int i = noticeService.updateNewNotice(notice);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    /**
     * 公告删除
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = noticeService.deleteNotice(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 公告查找
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = noticeService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

    /**
     * 首页公告展示
     */
    @GetMapping("/homePageNotice")
    public Result<?> homePageNotice() {
        List<?> list = noticeService.homePageNotice();
        if (list != null) {
            return Result.success(list);
        } else {
            return Result.error("-1", "首页公告查询失败");
        }
    }
}
