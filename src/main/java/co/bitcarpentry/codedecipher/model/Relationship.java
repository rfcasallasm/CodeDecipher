package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="IS_RELATED_TO")
public class Relationship {

	@Id @GeneratedValue
	protected Long id;
	
	@Property
	protected String name;
	
	@StartNode
	protected Class fromClass;
	
	@EndNode
	protected Class toClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getFromClass() {
		return fromClass;
	}

	public void setFromClass(Class fromClass) {
		this.fromClass = fromClass;
	}

	public Class getToClass() {
		return toClass;
	}

	public void setToClass(Class toClass) {
		this.toClass = toClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relationship other = (Relationship) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.fromClass+" IS_RELATED_TO ("+this.name+") "+this.toClass;
	}
	
	
	
}
