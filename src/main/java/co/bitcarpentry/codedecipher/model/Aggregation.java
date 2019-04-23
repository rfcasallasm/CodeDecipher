package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type="HAS_A")
public class Aggregation extends Association {
	
	@Override
	public String toString() {
		return this.fromClass+" HAS_A ("+this.name+") "+this.toClass;
	}
	
}
