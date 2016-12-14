package tcizendesk.module.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.hc.Category;
import org.zendesk.client.v2.model.hc.Section;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ZenArticleSection {
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * To create category
	 * @param sessionId
	 * @param name
	 * @param description
	 * @param locale
	 * @return
	 */
public String createArticleSection(String sessionId,long categoryId, String name, String description, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Section secg=new Section();
		secg.setName(name);
		secg.setLocale(locale);
		secg.setCategoryId(categoryId);
		secg.setDescription(description);
		
		secg=dk.createSection(secg);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Created Section " + secg.getId() + " " + secg.getName());
		}
		/*
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(secg);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the json " + json);
		}
		*/
		return secg.getId()+"";
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
}
/**
 * Update existing Section
 * @param sessionId
 * @param categoryId
 * @param name
 * @param description
 * @param locale
 * @return
 */
public String updateArticleSection(String sessionId,int sectionId, int categoryId, String name, String description, String locale)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		
		Section secg=new Section();
		secg.setName(name);
		secg.setDescription(description);
		secg.setLocale(locale);
		secg.setCategoryId(categoryId);
		secg.setId((long)sectionId);
		
		secg=dk.updateSection(secg);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Update Section " + secg.getId() + " " + secg.getName());
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(secg);
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
 * To get all the sections from category
 * @param sessionId
 * @return
 */
public String getAllSections(String sessionId,int categoryId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Category catg=new Category();
		catg.setId((long)categoryId);
		Iterable<Section> catIt=dk.getSections(catg);
		
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
/**
 * To get all the section
 * @param sessionId
 * @return
 */
public String getAllSections(String sessionId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Iterable catIt=dk.getSections();
		
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
/**
 * To get category based on ID
 * @param sessionId
 * @param categoryId
 * @return
 */
public String getArticleSection(String sessionId,int sectionId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Section sec=dk.getSection(sectionId);
		
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got Catgory " + sec.getId() + " " + sec.getName());
		}
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String json = gson.toJson(sec);
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
