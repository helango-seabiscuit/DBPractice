package com.hemalatha.db.performance;

import com.hemalatha.db.performance.model.Book;
import com.hemalatha.db.performance.model.Review;
import org.springframework.expression.spel.ast.Selection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriteriaQueryTest {

    public static void main(String[] args) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPGPU");
        EntityManager manager = emf.createEntityManager();

        testFindCriteria(manager);

        manager.close();
        emf.close();
    }

    private static void testFindCriteria(EntityManager manager) {
        manager.getTransaction().begin();
        Book book = new Book();
        book.setTitle("Effective Java");

        Book book2 = new Book();
        book2.setTitle("You are a badass");

        Review review = new Review();
        review.setComment("Awesome");
        manager.persist(review);

        book.addReviews(review);

        manager.persist(book);
        manager.persist(book2);
         Book resBook = manager.createQuery("select b from Book b , Review r where b = r.book and r.comment='Awesome'",Book.class).getSingleResult();

//        Book result = findBook(manager,book.getId());
//        System.out.println("Result: " +result.getId());
//
//        List<Book> bookList = findBookMultipleJoins(manager,book.getId());

//        List<Object[]> books = findBookMultipleSelects(manager);
//        List<Tuple> books2 = findBookMultipleSelects2(manager);
//        List<BookInfo> books3 = findBookMultipleSelects3(manager);
//        Book b = findBookByNamedNativeQuery(manager,book.getId());
//        System.out.println(b);
//
//        Book b1 = findBookByNamedQuery(manager,book.getId());
//        System.out.println(b1);
//
        List<Book> res = findBookOrderBy(manager);

        manager.getTransaction().commit();
    }

    private static List<Book> findBookOrderBy(EntityManager manager){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.orderBy(cb.desc(root.get("title")));
        query.select(root);

//        ParameterExpression<Long> bookIdParam = cb.parameter(Long.class,"id");
//        query.where(cb.equal(root.get("id"),bookIdParam));

        return manager.createQuery(query).getResultList();
    }

    private static List<Book> findBookMultipleJoins(EntityManager manager,Long id) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Root<Review> root2 = query.from(Review.class);
        query.select(root)
           .where(cb.equal(root, root2.get("book")));
        return manager.createQuery(query)
                .getResultList();
    }

    private static List hierarchicalQuerySample(EntityManager manager){
        String ORG_QUERY ="SELECT emp_id, name, salary, manager_id, dept_id, address_id " +
                          "FROM emp " +
                          "START WITH manager_id = ? " +
                          "CONNECT BY PRIOR emp_id = manager_id";
//        manager.createNativeQuery(ORG_QUERY,some class)
//         .setParameter(1,id)
//                .getResultList();
        return null;

    }

    private static List<Object[]> findBookMultipleSelects(EntityManager manager) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Book> root = query.from(Book.class);
        query.multiselect(root.get("title"),root.get("id"));
        return manager.createQuery(query)
                .getResultList();
    }

    private static List<Tuple> findBookMultipleSelects2(EntityManager manager) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<Book> root = query.from(Book.class);
        query.multiselect(root.get("title"),root.get("id"));
        return manager.createQuery(query)
                .getResultList();
    }

    private static List<BookInfo> findBookMultipleSelects3(EntityManager manager) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<BookInfo> query = cb.createQuery(BookInfo.class);
        Root<Book> root = query.from(Book.class);
        query.multiselect(root.get("id"),root.get("title"));
        return manager.createQuery(query)
                .getResultList();
    }

    private static Book findBookByNamedQuery(EntityManager manager,Long id){
        return manager.createNamedQuery("booknamedquery",Book.class)
                .setParameter("id",id).getResultList()
                .get(0);
    }


    private static Book findBookByNamedNativeQuery(EntityManager manager,Long id){
        return manager.createNamedQuery("booknativequery",Book.class)
                .setParameter(0,id).getResultList()
                .get(0);
    }

    private static Book findBook(EntityManager manager,Long id) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
       // query.select(root.get("title"));
        query.select(root);
        query.distinct(true);
        Join<Book,Review> bookReviewJoin = root.join("reviews", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();
        ParameterExpression<Long> p = cb.parameter(Long.class,"id");
        criteria.add(cb.equal(root.get("id"),p));

        ParameterExpression<String> p2 = cb.parameter(String.class,"comment");
        criteria.add(cb.equal(bookReviewJoin.get("comment"),p2));


        query.where(cb.and(criteria.toArray(new Predicate[0])));
        return manager.createQuery(query).setParameter("id",id)
                .setParameter("comment","Awesome")
                .getSingleResult();
    }

    static class BookInfo {
        Long id;
        String bookname;

        public BookInfo(Long id, String bookname) {
            this.id = id;
            this.bookname = bookname;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }
    }
}
