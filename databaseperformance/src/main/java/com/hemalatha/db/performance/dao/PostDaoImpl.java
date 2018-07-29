package com.hemalatha.db.performance.dao;

import com.hemalatha.db.performance.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Post findById(Integer id) {
           return  entityManager.find(Post.class,id);
    }

    @Override
    public void insert(Post p) {
        entityManager.persist(p);
    }

    @Override
    public void delete(Integer id) {
        Post p = findById(id);
        entityManager.remove(p);
    }
}
