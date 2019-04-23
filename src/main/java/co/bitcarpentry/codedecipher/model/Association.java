package co.bitcarpentry.codedecipher.model;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type="ASSOCIATED_TO")
public class Association extends Relationship {

	@Override
	public String toString() {
		return this.fromClass+" ASSOCIATED_TO ("+this.name+") "+this.toClass;
	}
	
}
