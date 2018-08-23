package com.hemalatha.db.performance.model.practicethrow.throwsoon;

import com.hemalatha.db.performance.generators.PublisherCodePrefixedSequenceIdGenerator;
import com.hemalatha.db.performance.generators.StringPrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_seq_custom")
	@GenericGenerator(
			name = "book_seq_custom",
//			strategy = "com.hemalatha.db.performance.generators.StringPrefixedSequenceIdGenerator",
//			parameters = {
//					//@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
//					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,value = "B_"),
//					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
//			}
//			strategy = "com.hemalatha.db.performance.generators.DatePrefixedSequenceIdGenerator"
			strategy = "com.hemalatha.db.performance.generators.PublisherCodePrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = PublisherCodePrefixedSequenceIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
					@Parameter(name = PublisherCodePrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")}
	)
	private String id;

	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String title;

	@Column
	private LocalDate publishingDate;

	@ManyToOne
	@JoinColumn(name = "publisherId")
	private Publisher publisher;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
