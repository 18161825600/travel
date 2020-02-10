package com.example.travel.mapper;

import com.example.travel.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends CommonMapper<Comment> {
}