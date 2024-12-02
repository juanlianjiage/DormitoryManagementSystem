package com.example.springboot.service.impl;

import com.example.springboot.utils.EmailUtils;
import com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Notice;
import com.example.springboot.entity.Student;
import com.example.springboot.mapper.NoticeMapper;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    /**
     * 注入DAO层对象
     */
    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    ExecutorService executorService;

    @Resource
    private EmailUtils emailUtils;


    /**
     * 公告添加
     */
    @Transactional
    @Override
    public void addNewNotice(Notice notice) {
        int insert = noticeMapper.insert(notice);
        if (insert != 1){
            throw new RuntimeException("邮件添加失败！");
        }
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("where 1 = 1");
        List<Student> students = studentMapper.selectList(queryWrapper);
        List<String> emails = new ArrayList<>();
        students.forEach(student -> {
            if (student.getEmail() != null && student.getEmail().length() > 0 ){
                emails.add(student.getEmail());
            }
        });
        try{
            List<List<String>> emailsPartitions = Lists.partition(emails, 50);
            emailsPartitions.forEach(ep -> {
                ep.forEach(email->{
                    executorService.execute(()->{
                       emailUtils.sendEmail(email, notice.getTitle(), notice.getContent());
                    });
                });
            });
        }catch (RuntimeException e){
            System.out.println("邮件发送失败，请重试！");
            throw e;
        }

    }

    /**
     * 公告查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.like("title", search);
//        Assert.notNull(qw,"不可为空");
        Page noticePage = noticeMapper.selectPage(page, qw);
        return noticePage;
    }

    /**
     * 公告更新
     */
    @Override
    public int updateNewNotice(Notice notice) {
        int i = noticeMapper.updateById(notice);
        return i;
    }

    /**
     * 公告删除
     */
    @Override
    public int deleteNotice(Integer id) {
        int i = noticeMapper.deleteById(id);
        return i;
    }

    /**
     * 首页公告展示
     */
    @Override
    public List<?> homePageNotice() {
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.orderByDesc("release_time");
        List<Notice> noticeList = noticeMapper.selectList(qw);
        return noticeList;
        
    }

}
