package com.hemalatha.db.performance;

import com.hemalatha.db.performance.Jpadatarepositories.PostRepository;
import com.hemalatha.db.performance.configs.HibernateSpringConfig;
import com.hemalatha.db.performance.configs.SpringDataJPAConfig;
import com.hemalatha.db.performance.dao.PostDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateSpringConfigTest {

    public static void main(String[] args) {
       // testDaoImplement();

        testSpringDataJpaImplement();
    }

    private static void testSpringDataJpaImplement() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);

        PostRepository postRepository = applicationContext.getBean(PostRepository.class);
       // System.out.println(postRepository.findOne(1));
        applicationContext.close();
    }

    private static void testDaoImplement() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateSpringConfig.class);

        PostDao dao = applicationContext.getBean(PostDao.class);
        System.out.println(dao.findById(1));
        applicationContext.close();
    }
}
