package com.hemalatha.db.performance.model.collectionmapping;



import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Map;

@Embeddable
class EmbeddableKey implements Serializable {
	private String keyfield1;
	private String keyfield2;

}

@Embeddable
class EmbeddableValue implements Serializable {
	private String embeddableValue1;
	private String embeddableValue2;


}

@Entity
public class Project implements Serializable {

	@Id
	private long id;
	@ElementCollection
			@CollectionTable(name = "proj_metadata")
			@AttributeOverrides({@AttributeOverride(name = "key.keyfield1",column = @Column(name = "key1")),
			@AttributeOverride(name = "key.keyfield2",column = @Column(name = "key2")),
			@AttributeOverride(name = "value.embeddableValue1",column = @Column(name = "value1")),
			@AttributeOverride(name = "value.embeddableValue2",column =@Column(name ="value2"))})
	Map<EmbeddableKey, EmbeddableValue> embeddedData;
}