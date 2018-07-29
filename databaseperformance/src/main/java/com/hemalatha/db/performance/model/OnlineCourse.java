package com.hemalatha.db.performance.model;


import com.hemalatha.db.performance.converters.DurationConverter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Table(name = "online_course")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OnlineCourse {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    //@Convert(converter = DurationConverter.class)
    private Duration videoduration;

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

    public Duration getVideoduration() {
        return videoduration;
    }

    public void setVideoduration(Duration videoduration) {
        this.videoduration = videoduration;
    }
}
