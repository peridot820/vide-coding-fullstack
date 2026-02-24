package com.example.vibeapp.post;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post WHERE no = #{no}")
    Post findById(Long no);

    @Select("SELECT * FROM post")
    List<Post> findAll();

    @Insert("INSERT INTO post (title, content) VALUES (#{title}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    void insert(Post post);

    @Update("UPDATE post SET title = #{title}, content = #{content} WHERE no = #{no}")
    void update(Post post);

    @Delete("DELETE FROM post WHERE no = #{no}")
    void delete(Long no);
}
