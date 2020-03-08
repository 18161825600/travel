package com.example.travel.service;

import com.example.travel.request.*;
import com.example.travel.response.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    Integer insertComment(AddCommentRequest addCommentRequest);

    Integer deleteComment(IdsRequest idsRequest);

    Integer updateComment(UpdateCommentRequest updateCommentRequest);

    CommentResponse selectCommentById(IdRequest idRequest);

    AllCommentResponse selectAllComment(PageNumRequest pageNumRequest);

    AllCommentUserResponse selectCommentByUserId(SelectCommentByUserIdRequest selectCommentByUserIdRequest);

    AllCommentScenicResponse selectCommentByScenicId(SelectCommentByScenicIdRequest selectCommentByScenicIdRequest);
}
