package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.*;
import com.example.travel.response.*;
import com.example.travel.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "insert/comment")
    public TravelJsonResult<String> insertComment(@RequestBody AddCommentRequest addCommentRequest){
        Integer integer = commentService.insertComment(addCommentRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @DeleteMapping(value = "delete/comment")
    public TravelJsonResult<String> deleteComment(@RequestBody IdsRequest idsRequest){
        return TravelJsonResult.ok("删除了"+commentService.deleteComment(idsRequest)+"条记录");
    }

    @PutMapping(value = "update/comment")
    public TravelJsonResult<String> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){
        Integer integer = commentService.updateComment(updateCommentRequest);
        if(integer==1){
            return TravelJsonResult.ok();
        }else return TravelJsonResult.errorMsg("false");
    }

    @PostMapping(value = "select/comment/by/id")
    public TravelJsonResult<CommentResponse> selectCommentById(@RequestBody IdRequest idRequest){
        return TravelJsonResult.ok(commentService.selectCommentById(idRequest));
    }

    @PostMapping(value = "select/all/comment")
    public TravelJsonResult<AllCommentResponse> selectAllComment(@RequestBody PageNumRequest pageNumRequest){
        return TravelJsonResult.ok(commentService.selectAllComment(pageNumRequest));
    }

    @PostMapping(value = "select/comment/by/userId")
    public TravelJsonResult<AllCommentUserResponse> selectCommentByUserId(@RequestBody SelectCommentByUserIdRequest selectCommentByUserIdRequest){
        return TravelJsonResult.ok(commentService.selectCommentByUserId(selectCommentByUserIdRequest));
    }

    @PostMapping(value = "select/comment/by/scenicId")
    public TravelJsonResult<AllCommentScenicResponse> selectCommentByScenicId(@RequestBody SelectCommentByScenicIdRequest selectCommentByScenicIdRequest){
        return TravelJsonResult.ok(commentService.selectCommentByScenicId(selectCommentByScenicIdRequest));
    }
}
