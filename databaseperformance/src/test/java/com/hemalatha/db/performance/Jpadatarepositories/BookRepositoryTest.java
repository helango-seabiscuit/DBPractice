//package com.hemalatha.db.performance.Jpadatarepositories;
//
//import com.hemalatha.db.performance.config.SpringDataJPAConfig;
//import com.hemalatha.db.performance.model.Author;
//import com.hemalatha.db.performance.model.Book;
//import com.hemalatha.db.performance.model.Review;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import javax.transaction.Transactional;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
//        classes = { SpringDataJPAConfig.class })
//public class BookRepositoryTest {
//
//
//    @Autowired
//    BookRepositroy bookRepositroy;
//
//    @Autowired
//    ReviewRepository reviewRepository;
//
//    @Autowired
//    AuthorRepository authorRepository;
//
//    private Long bookId;
//
//    @Before
//    public void setUp(){
//        bookRepositroy.deleteAll();
//        reviewRepository.deleteAll();
//
//        Book b = new Book();
//        b.setTitle("Effective Java 3");
//        bookRepositroy.save(b);
//        bookId = b.getId();
//    }
//
//    @Test
//    public void testSave(){
//        Book book = bookRepositroy.findOne(bookId);
//
//        Review review = new Review();
//        review.setComment("Awesome");
//        reviewRepository.save(review);
//
//        book.addReviews(review);
//        bookRepositroy.save(book);
//
//        System.out.println(book.getId());
//        System.out.println(review.getId());
//    }
//
//    @Test
//    public void testManyToMany(){
//        Book book = bookRepositroy.findOne(bookId);
//        Author a = new Author();
//        a.setName("Joshua Bosch");
//        a.addBook(book);
//       authorRepository.save(a);
//        bookRepositroy.save(book);
//    }
//
//    @After
//    public void tearDown(){
//
//    }
//
//}
