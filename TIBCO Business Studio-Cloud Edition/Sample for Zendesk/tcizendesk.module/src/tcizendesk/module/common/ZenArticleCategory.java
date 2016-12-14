package tcizendesk.module.common;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.hc.Article;
import org.zendesk.client.v2.model.hc.Category;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZenArticleCategory {
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionManagement mgmt=SessionManagement.getUserSessions();
		ZenArticleCategory cate= new ZenArticleCategory();
		//Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
		String sessionid=mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU");
		System.out.println("sessionid " + sessionid);
		String catID=cate.createArticleCategory(sessionid, "testCategory", "Test Description", "en-us");
		System.out.println("CatID " + catID);
		ZenArticleSection sec= new ZenArticleSection();
		String secId=sec.createArticleSection(sessionid, Long.parseLong(catID), "TestSection" , "Test Section", "en-us");
		System.out.println("secId " + secId);
		ZenArticle art=new ZenArticle();
		//String sessionId,long sectionId, String title, long author_id, String body, String locale
		String articleId=art.createArticle(sessionid, Long.parseLong(secId), "Article Title", 0, "Article Body", "en-us");
		System.out.println(articleId);
		String arti=art.getArticle(sessionid, Integer.parseInt(articleId));
		System.out.println(arti);
		mgmt.closeSession(sessionid);
	}
	/**
	 * To create category
	 * @param sessionId
	 * @param name
	 * @param description
	 * @param locale
	 * @return
	 */
public String createArticleCategory(String sessionId,String name, String description, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Category catg=new Category();
		catg.setDescription(description);
		catg.setName(name);
		catg.setLocale(locale);
		Category  catgt=dk.createCategory(catg);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Created Catgory " + catgt.getId() + " " + catgt.getName());
		}
		/*
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(catgt);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		*/
		return catgt.getId()+"";
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}
/**
 * Update existing category
 * @param sessionId
 * @param categoryId
 * @param name
 * @param description
 * @param locale
 * @return
 */
public String updateArticleCategory(String sessionId,int categoryId,String name, String description, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Category catg=new Category();
		catg.setDescription(description);
		catg.setName(name);
		catg.setLocale(locale);
		catg.setId((long)categoryId);
		
		catg=dk.updateCategory(catg);
		
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Update Catgory " + catg.getId() + " " + catg.getName());
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(catg);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		return json;
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}

/**
 * To get all the category
 * @param sessionId
 * @return
 */
public String getAllCategory(String sessionId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Iterable catIt=dk.getCategories();
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Catgory List");
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(catIt);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		return json;
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}
/**
 * To get category based on ID
 * @param sessionId
 * @param categoryId
 * @return
 */
public String getArticleCategory(String sessionId,int categoryId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Category catg=dk.getCategory(categoryId);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Catgory " + catg.getId() + " " + catg.getName());
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(catg);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		return json;
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}

}
