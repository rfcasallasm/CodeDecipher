package co.bitcarpentry.codedecipher.logic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.graphdb.factory.GraphDatabaseSettings.BoltConnector;
import org.neo4j.logging.slf4j.Slf4jLogProvider;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class GraphDatabaseFacade {

	private static volatile GraphDatabaseFacade _instance;
	
	public static GraphDatabaseFacade getInstance() {
		if(_instance == null) {
			synchronized (GraphDatabaseFacade.class) {
				if(_instance == null) {
					_instance = new GraphDatabaseFacade();
				}				
			}
		}
		return _instance;
	}
	
	private GraphDatabaseFactory graphDatabaseFactory;
	private Map<String,GraphDatabaseService> graphDatabaseMap;
	private Map<String,SessionFactory> sessionFactoryMap;
	private int currentBoltPort;
	private Map<String,Integer> boltPortMap;
	
	private GraphDatabaseFacade() {
		super();
		this.graphDatabaseFactory = new GraphDatabaseFactory();		
		this.graphDatabaseFactory.setUserLogProvider( /*new JavaLogProvider()*/new Slf4jLogProvider() );
		this.graphDatabaseMap     = new HashMap<>();
		this.sessionFactoryMap    = new HashMap<>();
		this.boltPortMap          = new HashMap<>();
		this.currentBoltPort      = 7687;
	}
	
	private File resolveBy(String name) {
		return new File(System.getProperty("java.io.tmpdir")+"code-decipher/"+name+"/db");
	}
	
	public Set<String> getAllNames(){
		return this.graphDatabaseMap.keySet();
	}
	
	public GraphDatabaseService getDatabase(String name) {
		if( !this.graphDatabaseMap.containsKey( name ) ) {			
			//this.graphDatabaseMap.put(name, this.graphDatabaseFactory.newEmbeddedDatabase(this.resolveBy(name)));
			GraphDatabaseSettings.BoltConnector boltSettings = GraphDatabaseSettings.boltConnector( "0" );			
			this.graphDatabaseMap.put(
						name, 
						this.graphDatabaseFactory.newEmbeddedDatabaseBuilder( this.resolveBy(name) )
							.setConfig( boltSettings.type, "BOLT" )
							.setConfig( boltSettings.enabled, "true" )							
							.setConfig( boltSettings.address, "0.0.0.0:"+this.currentBoltPort)
							.setConfig( boltSettings.listen_address, "0.0.0.0:"+this.currentBoltPort)
							.setConfig( boltSettings.encryption_level, BoltConnector.EncryptionLevel.OPTIONAL.toString())
							.newGraphDatabase()
					);
			this.boltPortMap.put(name, this.currentBoltPort);
			this.currentBoltPort++;
		}
		return this.graphDatabaseMap.get(name);
	}
	
	public void deleteDatabase(String name) {
		GraphDatabaseService gdService = this.graphDatabaseMap.get(name);
		if( gdService != null ) {
			gdService.shutdown();
		}
		this.graphDatabaseMap.remove(name);
		this.resolveBy(name).delete();		
	}
	
	public Session createSession(String name) {
		if( !this.sessionFactoryMap.containsKey( name ) ) {
			GraphDatabaseService graphDatabaseService = this.getDatabase(name);
			this.sessionFactoryMap.put(name, new SessionFactory( new EmbeddedDriver(graphDatabaseService), "co.bitcarpentry.codedecipher.model" ) );
		}
		return this.sessionFactoryMap.get(name).openSession();
	}
	
	public Integer getBoltPort(String name) {
		return this.boltPortMap.get(name);
	}
	
	public void shutdown() {
		for( String name : this.graphDatabaseMap.keySet() ) {
			this.deleteDatabase( name );
		}
	}
	
}
