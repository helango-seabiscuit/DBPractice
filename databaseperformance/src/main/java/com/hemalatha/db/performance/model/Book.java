package com.hemalatha.db.performance.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedNativeQuery(
        name = "booknativequery",
        query = "SELECT id,title from book where id=?",
        resultClass = Book.class
)
@NamedQuery(
      name = "booknamedquery",
      query = "SELECT book from com.hemalatha.db.performance.model.Book book where id=:id"

)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false,nullable = false)
    private Long id;


    @Column
    @Basic(fetch = FetchType.LAZY)
    private String title;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "book_author",
    joinColumns = {@JoinColumn(name = "fk_book")},
    inverseJoinColumns = {@JoinColumn(name = "fk_author")})
    private List<Author> authors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReviews(Review review) {
        this.reviews.add(review);
        review.setBook(this);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
