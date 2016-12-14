package com.tci.tibco.mongo.util;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.net.SocketFactory;



public class MongoConnector {

    //private static int maxConnections = 5;
    private int socketTimeout = 30000; // millisecond
    private boolean isSSLEnabled=false;    
    private int maxConnectionIdleTime=120000;
    private int maxWaitTime=120000;
    //private static SocketFactory socketFactory=null;
    private boolean socketKeepAlive=false;
    private boolean sslInvalidHostNameAllowed=true;
    private int threadsAllowedToBlockForConnectionMultiplier=5;
    private WriteConcern writeConcern=new WriteConcern(1,0) ;
    
    public MongoClient client=null;
    DB db=null;
    
    public MongoConnector(){
    	//empty constructor
    }
    public MongoConnector(boolean isSSLEnabled){
    	//empty constructor
    	this.isSSLEnabled=isSSLEnabled;
    	this.sslInvalidHostNameAllowed=false;
    	Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Object MongoConnector Created"));
    }
    
    
    public boolean init(String host, int port, String userName, String password, String dbName, String trustStore, String trustStore_pwd){
    	
    	/*
    	 * Below JVM parameter should be set properly if SSL to be used
    	 * javax.net.ssl.trustStore
    	 * javax.net.ssl.trustStorePassword
    	 */
    	
    	//System.setProperty("javax.net.ssl.trustStore", "C:\\AfterFrameworks\\Mongo\\Certs\\cacerts");
    	//System.setProperty("javax.net.ssl.trustStorePassword", "changeit"); 
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStore);
    	System.setProperty("javax.net.ssl.trustStorePassword", trustStore_pwd);


    	
    	System.out.println(System.getProperty("javax.net.ssl.trustStore"));
    	System.out.println(System.getProperty("javax.net.ssl.keyStore"));
    	
    	Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoConnector.Init() :: ", "Trying to connect to ", host,":: SSL Enabled = ",isSSLEnabled));
    	
    	MongoCredential credential = MongoCredential.createMongoCRCredential(userName, dbName, password.toCharArray());
    	MongoClientOptions options = MongoClientOptions.builder().cursorFinalizerEnabled(false).maxConnectionIdleTime(maxConnectionIdleTime).maxWaitTime(maxWaitTime).socketTimeout(socketTimeout).sslEnabled(isSSLEnabled).socketKeepAlive(socketKeepAlive).sslInvalidHostNameAllowed(sslInvalidHostNameAllowed).threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier).writeConcern(writeConcern).build();  
    	client = new MongoClient(new ServerAddress(host,port),Arrays.asList(credential),options);
    	Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Exiting MongoConnector.Init() :: ", "Connected to ", host,":: SSL Enabled = ",isSSLEnabled));
    	
    	return true;
    }
    
    public MongoClient getMongoClient(){
    	return client;
    }
    
 
    
    public String readCollection(String dbName, String collectionName, int count){
    	Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoConnector.readCollection() :: ", "Trying to read DB : ", dbName,"& Collection : ",collectionName));
    	MongoOperator mtu = new MongoOperator(dbName, this);
    	
    	if(count>0)
    	{
    		String records=mtu.readDocsPartial(collectionName, count);
    		records=records.replaceAll("Document", "");
    		records="{\"records\":{\"record\":[" + records + "]}}";
    		return records;
    	}
    	else
    	{
    		String records=mtu.readDocsAll(collectionName);
    		records=records.replaceAll("Document", "");
    		records="{\"records\":{\"record\":[" + records + "]}}";
    		return records;
    	}
    }
    	
    
    public String insertRecord(String dbName, String collectionName, String record)
	{
		MongoOperator mtu = new MongoOperator(dbName, this);
		return mtu.insertRecord(collectionName,record);
		
	}
	
    public String deleteRecord(String dbName, String collectionName, String filter) 
	{
    	MongoOperator mtu = new MongoOperator(dbName, this);
		return mtu.deleteRecord(collectionName, filter);
	
	}
	
	public String updateRecord(String dbName, String collectionName, String filter, String update) 
	{
		return updateRecord(dbName, collectionName, filter, update, false, false, "");
	}
	
	public String updateRecord(String dbName, String collectionName, String filter, String update, Boolean upsert, Boolean returnOriginal, String sort) 
	{
    	MongoOperator mtu = new MongoOperator(dbName, this);
		return mtu.updateRecord(collectionName, filter, update, upsert, returnOriginal, sort);

	}
	
	
	
	public String replaceRecord(String dbName, String collectionName, String filter, String newRecord) 
	{
		return replaceRecord(dbName, collectionName, filter, newRecord, false, false, "");
	}
	
	public String replaceRecord(String dbName, String collectionName, String filter, String newRecord, Boolean upsert, Boolean returnOriginal, String sort) 
	{
    	MongoOperator mtu = new MongoOperator(dbName, this);
		return mtu.replaceRecord(collectionName, filter, newRecord, upsert, returnOriginal, sort);

	}
    

}