package com.example.travel.dao;

import com.example.travel.mapper.CommentMapper;
import com.example.travel.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private CommentMapper commentMapper;

    public Integer insertComment(Comment comment){
        return commentMapper.insert(comment);
    }

    public Integer deleteComment(List<Long> ids){
        Example example = new Example(Comment.class);
        example.createCriteria().andIn("id",ids);
        return commentMapper.deleteByExample(example);
    }

    public Integer deleteComment(Long id){
        return commentMapper.deleteByPrimaryKey(id);
    }

    public Integer updateComment(Comment comment){
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    public Comment selectCommentById(Long id){
        return commentMapper.selectByPrimaryKey(id);
    }

    public List<Comment> selectAllComment(){
        return commentMapper.selectAll();
    }

    public List<Comment> selectCommentByUserId(Long userId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("userId",userId);
        return commentMapper.selectByExample(example);
    }

    public List<Comment> selectCommentByScenicId(Long scenicSpotId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return commentMapper.selectByExample(example);
    }

    public Integer countAllComment(){
        Example example = new Example(Comment.class);
        return commentMapper.selectCountByExample(example);
    }

    public Integer countByUserId(Long userId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("userId",userId);
        return commentMapper.selectCountByExample(example);
    }

    public Integer countByScenicId(Long scenicSpotId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return commentMapper.selectCountByExample(example);
    }
}
