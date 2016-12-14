package com.tci.tibco.mongo.util;

import java.util.logging.Level;
import java.util.logging.Logger;







import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.util.JSON;

public class MongoOperator {
	
	private MongoDatabase mongoDB = null;
	private MongoClient mClient = null;
	
	
	public MongoOperator(String dbName, MongoConnector mConnector){
		mClient = mConnector.getMongoClient();
		mongoDB = mClient.getDatabase(dbName);
		Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Object MongoTransactionsUtil Created"));
	}
	
	public String readDocsPartial(String collectionName, int count){
		String records="";
		Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoTransactionsUti:readDocsPartial()"));
		
		MongoCollection<DBObject> mCollection=mongoDB.getCollection(collectionName,DBObject.class);
		FindIterable<DBObject> mIterator = mCollection.find();
		MongoCursor<DBObject> mCursor=mIterator.iterator();
		
		int i=0;
		try {
		   while(mCursor.hasNext() && i < count) {
			   Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoTransactionsUti:readDocsPartial():While:", i));
			  
			   DBObject dbo=mCursor.next();
			   dbo.removeField("_id");
			   records += dbo;
			   if(mCursor.hasNext())
				   records += ",";
			   i++;
		   }
		} finally {
		   mCursor.close();
		}

		Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Exiting MongoTransactionsUti:readDocsPartial()"));
		return records;
	}
	
	public String readDocsAll(String collectionName){
		String records="";
		
		Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoTransactionsUti:readDocsAll() "));
		MongoCollection<DBObject> mCollection=mongoDB.getCollection(collectionName,DBObject.class);
		FindIterable<DBObject> mIterator = mCollection.find();
		MongoCursor<DBObject> mCursor=mIterator.iterator();
		
		
		
		int i=0;
		try {
		   while(mCursor.hasNext()) {
			   Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Inside MongoTransactionsUti:readDocsAll():While:", i));
			   
			   DBObject dbo=mCursor.next();
			   
			   dbo.removeField("_id");
			   records += dbo;
			   if(mCursor.hasNext())
				   records += ",";
			   i++;
		   }
		} finally {
		   mCursor.close();
		}
		
		Logger.getLogger(MongoConnector.class.getName()).log(Level.INFO, String.format("Exiting MongoTransactionsUti:readDocAlls()"));
		return records;
	}
	
	
	public String insertRecord(String collectionName, String S) 
	{
		try{
		MongoCollection<DBObject> mCollection = mongoDB.getCollection(collectionName, DBObject.class);
		
		DBObject dbObject = (DBObject) JSON
				.parse(S);
		
		mCollection.insertOne(dbObject);
		
		return "Success";
		
		}
		catch(MongoException me)
		{
			return me.toString();
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	
	public String deleteRecord(String collectionName, String filter) 
	{
		try{
		MongoCollection<DBObject> mCollection = mongoDB.getCollection(collectionName, DBObject.class);
		
		DBObject dbObject = (DBObject) JSON.parse(filter);
		DBObject dbo = mCollection.findOneAndDelete((BasicDBObject) dbObject );
		String deletedRecord="";
		deletedRecord += dbo;
		return deletedRecord;
		
		}
		catch(MongoException me)
		{
			return me.toString();
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	
	public String updateRecord(String collectionName, String filter, String update) 
	{
		return updateRecord(collectionName, filter, update, false, false, "");
	}
	
	public String updateRecord(String collectionName, String filter, String update, Boolean upsert, Boolean returnOriginal, String sort) 
	{
		try{
		MongoCollection<DBObject> mCollection = mongoDB.getCollection(collectionName, DBObject.class);
		
		DBObject dbObject = (DBObject) JSON.parse(filter);
		DBObject dbObject1 = (DBObject) JSON.parse(update);
		
		
		FindOneAndUpdateOptions fOpt =new FindOneAndUpdateOptions();
		if(returnOriginal==true)
			fOpt.returnDocument(ReturnDocument.BEFORE);
		else
			fOpt.returnDocument(ReturnDocument.AFTER);
		fOpt.upsert(upsert);
		if(sort != null && sort.length() > 0)
		{
			DBObject dbObject2 = (DBObject) JSON.parse(sort);
			fOpt.sort((BasicDBObject) dbObject2);
		}	
		DBObject dbo = mCollection.findOneAndUpdate((BasicDBObject) dbObject,(BasicDBObject) dbObject1, fOpt);
		String updatedRecord="";
		updatedRecord += dbo;
		return updatedRecord;
		
		}
		catch(MongoException me)
		{
			return me.toString();
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	
	
	
	public String replaceRecord(String collectionName, String filter, String newRecord) 
	{
		return replaceRecord(collectionName, filter, newRecord, false, false, "");
	}
	
	public String replaceRecord(String collectionName, String filter, String newRecord, Boolean upsert, Boolean returnOriginal, String sort) 
	{
		try{
		MongoCollection<DBObject> mCollection = mongoDB.getCollection(collectionName, DBObject.class);
		
		DBObject dbObject = (DBObject) JSON.parse(filter);
		DBObject dbObject1 = (DBObject) JSON.parse(newRecord);
		
		
		FindOneAndReplaceOptions fOpt =new FindOneAndReplaceOptions();
		if(returnOriginal==true)
			fOpt.returnDocument(ReturnDocument.BEFORE);
		else
			fOpt.returnDocument(ReturnDocument.AFTER);
		fOpt.upsert(upsert);
		if(sort != null && sort.length() > 0)
		{
			DBObject dbObject2 = (DBObject) JSON.parse(sort);
			fOpt.sort((BasicDBObject) dbObject2);
		}	
		DBObject dbo = mCollection.findOneAndReplace((BasicDBObject) dbObject, dbObject1 , fOpt);
		String replacedRecord="";
		replacedRecord += dbo;
		return replacedRecord;
		
		}
		catch(MongoException me)
		{
			return me.toString();
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	

}
