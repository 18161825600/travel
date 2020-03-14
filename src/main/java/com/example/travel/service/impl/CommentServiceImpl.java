package com.example.travel.service.impl;

import com.example.travel.dao.CommentDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Comment;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.User;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentRequest,comment);
        comment.setCreateTime(new Date());
        return commentDao.insertComment(comment);
    }

    @Override
    public Integer deleteComment(IdsRequest idsRequest) {
        return commentDao.deleteComment(idsRequest.getIds());
    }

    @Override
    public Integer updateComment(UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentDao.selectCommentById(updateCommentRequest.getId());
        comment.setComment(updateCommentRequest.getComment());
        comment.setUpdateTime(new Date());
        return commentDao.updateComment(comment);
    }

    @Override
    public CommentResponse selectCommentById(IdRequest idRequest) {
        Comment comment = commentDao.selectCommentById(idRequest.getId());
        CommentResponse commentResponse = changeCommentResponse(comment);
        return commentResponse;
    }

    @Override
    public AllCommentResponse selectAllComment(PageNumRequest pageNumRequest) {
        PageHelper.startPage(1,pageNumRequest.getPageNum()*10);
        List<Comment> comments = commentDao.selectAllComment();
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        List<Comment> commentList = pageInfo.getList();
        AllCommentResponse allCommentResponse = new AllCommentResponse();
        List<CommentResponse> list = new ArrayList<>();
        for(Comment comment : commentList){
            CommentResponse commentResponse = changeCommentResponse(comment);
            list.add(commentResponse);
        }
        allCommentResponse.setCommentResponseList(list);
        allCommentResponse.setTotal(commentDao.countAllComment());
        return allCommentResponse;
    }

    @Override
    public AllCommentUserResponse selectCommentByUserId(SelectCommentByUserIdRequest selectCommentByUserIdRequest) {
        PageHelper.startPage(selectCommentByUserIdRequest.getPageNum(),10);
        List<Comment> comments = commentDao.selectCommentByUserId(selectCommentByUserIdRequest.getUserId());
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        List<Comment> commentList = pageInfo.getList();
        AllCommentUserResponse allCommentUserResponse = new AllCommentUserResponse();
        List<CommentUserResponse> list = new ArrayList<>();
        for(Comment comment : commentList){
            CommentUserResponse commentUserResponse = new CommentUserResponse();
            commentUserResponse.setCreateTime(changeDate(comment.getCreateTime()));
            commentUserResponse.setComment(comment.getComment());

            ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(comment.getScenicSpotId());
            commentUserResponse.setScenicSpotName(scenicSpot.getScenicSpotName());

            list.add(commentUserResponse);
        }
        allCommentUserResponse.setCommentUserResponseList(list);
        allCommentUserResponse.setTotal(commentDao.countByUserId(selectCommentByUserIdRequest.getUserId()));
        return allCommentUserResponse;
    }

    @Override
    public AllCommentScenicResponse selectCommentByScenicId(SelectCommentByScenicIdRequest selectCommentByScenicIdRequest) {
        PageHelper.startPage(selectCommentByScenicIdRequest.getPageNum(), 10);
        List<Comment> comments = commentDao.selectCommentByScenicId(selectCommentByScenicIdRequest.getScenicSpotId());
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        List<Comment> commentList = pageInfo.getList();
        AllCommentScenicResponse allCommentScenicResponse = new AllCommentScenicResponse();
        List<CommentScenicResponse> list = new ArrayList<>();
        int number=1;
        for(Comment comment : commentList){
            CommentScenicResponse commentScenicResponse = new CommentScenicResponse();
            commentScenicResponse.setNumber(number);
            commentScenicResponse.setComment(comment.getComment());
            commentScenicResponse.setCreateTime(changeDate(comment.getCreateTime()));

            User user = userDao.selectUserById(comment.getUserId());
            commentScenicResponse.setNickName(user.getNickName());
            commentScenicResponse.setImgUrl(user.getImgUrl());

            number=number+1;
            list.add(commentScenicResponse);
        }
        allCommentScenicResponse.setCommentScenicResponseList(list);
        allCommentScenicResponse.setTotal(commentDao.countByScenicId(selectCommentByScenicIdRequest.getScenicSpotId()));
        return allCommentScenicResponse;
    }

    private CommentResponse changeCommentResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComment(comment.getComment());
        commentResponse.setCreateTime(changeDate(comment.getCreateTime()));

        User user = userDao.selectUserById(comment.getUserId());
        commentResponse.setNickName(user.getNickName());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(comment.getScenicSpotId());
        commentResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        return commentResponse;
    }

    public String changeDate(Date date){
        log.info("date-{}",date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("dateFormat-{}",dateFormat.format(date));
        return dateFormat.format(date);
    }
}
