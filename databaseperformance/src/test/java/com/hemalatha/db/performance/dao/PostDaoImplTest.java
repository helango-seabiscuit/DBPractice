package com.hemalatha.db.performance.dao;

import com.hemalatha.db.performance.config.DataDomainConfig;
import com.hemalatha.db.performance.model.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { DataDomainConfig.class })
public class PostDaoImplTest {

    @Autowired
    PostDao postDao;

    @Before
    public void setUp() throws Exception {
       Post p = new Post();
       p.setId(1);
       p.setTitle("Hello test");
       postDao.insert(p);
    }

    @After
    public void tearDown() throws Exception {
        postDao.delete(1);
    }


    @Test
    public void findById() throws Exception {
        Post p = postDao.findById(1);
        assertNotNull(p);
        System.out.println(p);
    }

}