package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PostRepository {

    int save(Post post);

    List<Post> findAll();

    // MyBatis accepts params for paging
    List<Post> findAllWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int count();

    Post findById(Long no);

    void deleteById(Long no);
}
