package com.example.watson.rr;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.ibm.watson.developer_cloud.retrieve_and_rank.v1.RetrieveAndRank;
import com.ibm.watson.developer_cloud.retrieve_and_rank.v1.model.Ranker;

@SuppressWarnings("serial")
public class CreateRanker implements java.io.Serializable{
	
	public static String createRanker(String fileURL, String rankerName,String username, String password) throws IOException {
		
		// 1 create the service
	    RetrieveAndRank service = new RetrieveAndRank();
	    service.setUsernameAndPassword(username, password);
	    System.out.println("Retrieve Rank Service : " + service);

	    // 2 create a ranker
	    URL url = new URL(fileURL);
	    System.out.println("Training File URL : " + url);
	    File file = new File("training-data.txt");
	    FileUtils.copyURLToFile(url, file);
	    System.out.println("Created new training file : " + file);
	    Ranker ranker = service.createRanker(rankerName, file).execute();
		
		return ranker.toString();
	}

}

