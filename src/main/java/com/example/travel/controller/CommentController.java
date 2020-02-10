package com.example.travel.controller;

import com.example.travel.common.TravelJsonResult;
import com.example.travel.request.AddCommentRequest;
import com.example.travel.request.UpdateCommentRequest;
import com.example.travel.response.CommentResponse;
import com.example.travel.response.CommentScenicResponse;
import com.example.travel.response.CommentUserResponse;
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
            return TravelJsonResult.ok("添加成功");
        }else return TravelJsonResult.errorMsg("添加失败");
    }

    @DeleteMapping(value = "delete/comment")
    public TravelJsonResult<String> deleteComment(@RequestBody List<Long> ids){
        return TravelJsonResult.ok("删除了"+commentService.deleteComment(ids)+"条记录");
    }

    @PutMapping(value = "update/comment")
    public TravelJsonResult<String> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){
        Integer integer = commentService.updateComment(updateCommentRequest);
        if(integer==1){
            return TravelJsonResult.ok("修改成功");
        }else return TravelJsonResult.errorMsg("修改失败");
    }

    @GetMapping(value = "select/comment/by/id")
    public TravelJsonResult<CommentResponse> selectCommentById( Long id){
        return TravelJsonResult.ok(commentService.selectCommentById(id));
    }

    @GetMapping(value = "select/all/comment")
    public TravelJsonResult<PageInfo<CommentResponse>> selectAllComment(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return TravelJsonResult.ok(commentService.selectAllComment(pageNum));
    }

    @GetMapping(value = "select/comment/by/userId")
    public TravelJsonResult<PageInfo<CommentUserResponse>> selectCommentByUserId(Long userId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(commentService.selectCommentByUserId(userId, pageNum));
    }

    @GetMapping(value = "select/comment/by/scenicId")
    public TravelJsonResult<PageInfo<CommentScenicResponse>> selectCommentByScenicId(Long scenicSpotId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return TravelJsonResult.ok(commentService.selectCommentByScenicId(scenicSpotId, pageNum));
    }
}
