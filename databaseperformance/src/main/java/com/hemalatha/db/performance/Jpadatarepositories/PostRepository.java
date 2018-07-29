package com.hemalatha.db.performance.Jpadatarepositories;

import com.hemalatha.db.performance.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
