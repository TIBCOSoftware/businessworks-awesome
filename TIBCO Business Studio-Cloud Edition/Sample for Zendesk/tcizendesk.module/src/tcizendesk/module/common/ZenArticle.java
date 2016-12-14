package tcizendesk.module.common;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.hc.Article;
import org.zendesk.client.v2.model.hc.Category;
import org.zendesk.client.v2.model.hc.Section;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ZenArticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionManagement mgmt=SessionManagement.getUserSessions();
		//Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "engineer@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
		//zd.get
		Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
		//zd.get
		Category categ=new Category();
		categ.setName("testCat1");
		categ.setLocale("en-us");
		
		Iterable<Category> ite1=zd2.getCategories();
		for (Category item : ite1) {
	        System.out.println(item.getName());
	        System.out.println(item.getId());
	        System.out.println(item.getLocale());
	    }
		//203715268
		//Category cat=zd2.createCategory(categ);
		//System.out.println(cat.getId());
		
		Section sec = new Section();
		sec.setCategoryId(203624407);
		sec.setName("testSection1");
		sec.setLocale("en-us");
		
		Section sec1=zd2.createSection(sec);
		
		Iterable<Section> ite=zd2.getSections();
		for (Section item : ite) {
	        System.out.println(item.getName());
	        System.out.println(item.getId());
	    }
		
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		  String value = gson.toJson(sec1);
		  System.out.println("gson " + value);
		  zd2.close();
		  /*
		Article art=new Article();
		art.setTitle("My test Article");
		art.setBody("My test article body");
		art.setPromoted(true);
		Article art1=zd2.createArticle(art);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		  String value = gson.toJson(art1);
		  System.out.println("gson " + value);
		  zd2.close();
		  */
	}
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * To create category
	 * @param sessionId
	 * @param name
	 * @param description
	 * @param locale
	 * @return
	 */
public String createArticle(String sessionId,long sectionId, String title, long author_id, String body, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Article article = new Article();
		//article.setAuthorId(author_id);
		article.setBody(body);
		//article.setCommentsDisabled(comments_disabled);
		//article.setPromoted(promoted);
		article.setLocale(locale);
		article.setTitle(title);
		article.setSectionId(sectionId);
		article.setPromoted(true);
		
		article=dk.createArticle(article);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Created Article " + article.getId() + " " + article.getAuthorId());
		}
		/*
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(article);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		*/
		return article.getId()+"";
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}
/**
 * Update existing Article
 * @param sessionId
 * @param categoryId
 * @param name
 * @param description
 * @param locale
 * @return
 */
public String updateArticle(long articleId,String sessionId,long sectionId, String title, long author_id, boolean comments_disabled, boolean promoted, String body, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Article article = new Article();
		article.setAuthorId(author_id);
		article.setBody(body);
		article.setCommentsDisabled(comments_disabled);
		article.setPromoted(promoted);
		article.setLocale(locale);
		article.setTitle(title);
		article.setSectionId(sectionId);
		article.setId(articleId);
		
		article=dk.createArticle(article);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Updated Article " + article.getId() + " " + article.getAuthorId());
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(article);
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getArticle(String sessionId,int articleId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Article catIt=dk.getArticle(articleId);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the article");
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getAllArticlesOnUser(String sessionId,String user)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Iterable<Article> articles=dk.getArticlesOnUser(user);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Article List");
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(articles);
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getAllArticlesOnSection(String sessionId,String section)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Iterable<Article> catIt=dk.getArticlesOnSection(section);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Article List");
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getAllArticlesOnCategory(String sessionId,String category)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Iterable<Article> catIt=dk.getArticlesOnCategory(category);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Article List");
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getAllArticlesOnLocal(String sessionId,String local)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Iterable<Article> catIt=dk.getArticlesOnLocal(local);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Article List");
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
 * To get all the Articles
 * @param sessionId
 * @return
 */
public String getAllArticles(String sessionId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}

		Iterable<Article> catIt=dk.getArticles();
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Section List");
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


}
