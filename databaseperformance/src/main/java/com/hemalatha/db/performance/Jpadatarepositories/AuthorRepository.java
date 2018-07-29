package com.hemalatha.db.performance.Jpadatarepositories;

import com.hemalatha.db.performance.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
