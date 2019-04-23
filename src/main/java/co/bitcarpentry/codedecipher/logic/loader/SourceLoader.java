package co.bitcarpentry.codedecipher.logic.loader;

import java.net.URI;

public interface SourceLoader {

	public boolean loadFromSource(URI from, URI to);
	
}
