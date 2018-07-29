package com.hemalatha.db.performance.dao;

import com.hemalatha.db.performance.model.Post;
import org.springframework.stereotype.Repository;

public interface PostDao {

    Post findById(Integer id);

    void insert(Post p);

    void delete(Integer id);

}
