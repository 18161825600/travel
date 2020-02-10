package com.example.travel.service;

import com.example.travel.request.AddCommentRequest;
import com.example.travel.request.UpdateCommentRequest;
import com.example.travel.response.CommentResponse;
import com.example.travel.response.CommentScenicResponse;
import com.example.travel.response.CommentUserResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    Integer insertComment(AddCommentRequest addCommentRequest);

    Integer deleteComment(List<Long> ids);

    Integer updateComment(UpdateCommentRequest updateCommentRequest);

    CommentResponse selectCommentById(Long id);

    PageInfo<CommentResponse> selectAllComment(Integer pageNum);

    PageInfo<CommentUserResponse> selectCommentByUserId(Long userId, Integer pageNum);

    PageInfo<CommentScenicResponse> selectCommentByScenicId(Long scenicSpotId, Integer pageNum);
}
