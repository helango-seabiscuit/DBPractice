package com.hemalatha.db.performance.Jpadatarepositories;

import com.hemalatha.db.performance.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositroy extends CrudRepository<Book,Long> {
}
