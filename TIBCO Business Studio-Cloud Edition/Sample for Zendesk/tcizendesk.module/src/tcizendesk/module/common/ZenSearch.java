package tcizendesk.module.common;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.SearchResultEntity;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.hc.Article;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ZenSearch {
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZenSearch sear=new ZenSearch();
		SessionManagement mgmt=SessionManagement.getUserSessions();
		//Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "engineer@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
		//zd.get
		String session =mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU");
		sear.search(session, "test");
		mgmt.closeSession(session);
		
		//zd.get
	}
	/**
	 * To get all the Articles
	 * @param sessionId
	 * @return
	 */
	public String search(String sessionId,String query)
	{
		try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}

			Iterable<Ticket> results=dk.getSearchResults(Ticket.class,query);
			
			
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Got the Results");
			}
			
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			StringBuffer buff=new StringBuffer();
			
			for(SearchResultEntity result:results)
			{
				System.out.println(result.toString());
				buff.append(gson.toJson(result));
			}
			//String json = gson.toJson(results);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Got the json " + buff.toString());
			}
			return buff.toString();
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(e.toString(),e);
			}
			return "ERROR : "+ e.toString();
		}
	}
}
