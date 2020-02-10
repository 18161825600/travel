package com.example.travel.service.impl;

import com.example.travel.dao.CommentDao;
import com.example.travel.dao.ScenicSpotDao;
import com.example.travel.dao.UserDao;
import com.example.travel.pojo.Comment;
import com.example.travel.pojo.ScenicSpot;
import com.example.travel.pojo.User;
import com.example.travel.request.AddCommentRequest;
import com.example.travel.request.UpdateCommentRequest;
import com.example.travel.response.CommentResponse;
import com.example.travel.response.CommentScenicResponse;
import com.example.travel.response.CommentUserResponse;
import com.example.travel.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
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
    public Integer deleteComment(List<Long> ids) {
        return commentDao.deleteComment(ids);
    }

    @Override
    public Integer updateComment(UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentDao.selectCommentById(updateCommentRequest.getId());
        comment.setComment(updateCommentRequest.getComment());
        comment.setUpdateTime(new Date());
        return commentDao.updateComment(comment);
    }

    @Override
    public CommentResponse selectCommentById(Long id) {
        Comment comment = commentDao.selectCommentById(id);
        CommentResponse commentResponse = changeCommentResponse(comment);
        commentResponse.setTotal(1);
        return commentResponse;
    }

    @Override
    public PageInfo<CommentResponse> selectAllComment(Integer pageNum) {
        List<Comment> comments = commentDao.selectAllComment();
        PageHelper.startPage(pageNum,10);
        List<CommentResponse> list = new ArrayList<>();
        for(Comment comment : comments){
            CommentResponse commentResponse = changeCommentResponse(comment);
            commentResponse.setTotal(commentDao.countAllComment());
            list.add(commentResponse);
        }
        PageInfo<CommentResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<CommentUserResponse> selectCommentByUserId(Long userId, Integer pageNum) {
        List<Comment> comments = commentDao.selectCommentByUserId(userId);
        PageHelper.startPage(pageNum,10);
        List<CommentUserResponse> list = new ArrayList<>();
        for(Comment comment : comments){
            CommentUserResponse commentUserResponse = new CommentUserResponse();
            commentUserResponse.setCreateTime(comment.getCreateTime());
            commentUserResponse.setComment(comment.getComment());
            commentUserResponse.setTotal(commentDao.countByUserId(userId));

            ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(comment.getScenicSpotId());
            commentUserResponse.setScenicSpotName(scenicSpot.getScenicSpotName());

            list.add(commentUserResponse);
        }
        PageInfo<CommentUserResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<CommentScenicResponse> selectCommentByScenicId(Long scenicSpotId, Integer pageNum) {
        List<Comment> comments = commentDao.selectCommentByScenicId(scenicSpotId);
        PageHelper.startPage(pageNum, 10);
        List<CommentScenicResponse> list = new ArrayList<>();
        for(Comment comment : comments){
            CommentScenicResponse commentScenicResponse = new CommentScenicResponse();
            commentScenicResponse.setComment(comment.getComment());
            commentScenicResponse.setCreateTime(comment.getCreateTime());
            commentScenicResponse.setTotal(commentDao.countByScenicId(scenicSpotId));

            User user = userDao.selectUserById(comment.getUserId());
            commentScenicResponse.setNickName(user.getNickName());

            list.add(commentScenicResponse);
        }
        PageInfo<CommentScenicResponse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public CommentResponse changeCommentResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(comment,commentResponse);

        User user = userDao.selectUserById(comment.getUserId());
        commentResponse.setNickName(user.getNickName());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(comment.getScenicSpotId());
        commentResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        return commentResponse;
    }
}
