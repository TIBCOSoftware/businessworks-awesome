package tcizendesk.module.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.Zendesk.Builder;
import org.zendesk.client.v2.model.User;

import tcizendesk.module.common.vos.UserVo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * This purpose of this class is to manage all the user session level objects.
 * @author sponnusa@tibco.com
 *
 */
public class SessionManagement {
	private static HashMap<String,Zendesk> sessMap = new HashMap();
	private static SessionManagement sessManagement=null;
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public SessionManagement()
	{
		;
			
	}
	/**
	 * To get the zendesk session object
	 * @param sessionId
	 * @return
	 */
	public Zendesk getUserSession(String sessionId)
	{
		Zendesk zd=sessMap.get(sessionId);
		return zd;
		
	}
	/**
	 * To check if a user session valid
	 * @param userName
	 * @return
	 */
	public boolean isValid(String sessionId)
	{
		Zendesk zd=sessMap.get(sessionId);
		if(zd!=null)
		{
			return true;
		}
		return false;
	}
	/**
	 * To create an instance of this class.
	 * @return
	 */
	public SessionManagement init()
	{
		if(sessManagement==null)
		{
			sessManagement =  new SessionManagement();
		}
		return sessManagement;
	}
	/**
	 * To get the instance of this object
	 * @return
	 */
	public static SessionManagement getUserSessions()
	{
		if(sessManagement==null)
		{
			sessManagement =  new SessionManagement();
		}
		return sessManagement;
	}
	
	/**
	 * To close an active user session
	 * @param userName
	 * @return
	 */
	public String closeSession(String sessionId)
	{
		Zendesk zd=sessMap.get(sessionId);
		if(zd!=null)
		{
			zd.close();
			sessMap.remove(sessionId);
			return ErrorMessages.SUCCESS;
		}
		return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
	}
	
	/**
	 * To get the user details 
	 * @param sessionId
	 * @return
	 */
	public String getUserDetails(String sessionId)
	{
		try{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("session id " + sessionId);
			this.logger.debug("size " + sessMap.size());
		}
		Zendesk zd=sessMap.get(sessionId);
		System.out.println(zd);
		
		if(zd != null)
		{
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			User usr=zd.getCurrentUser();
			//Change the data 
			UserVo vo= new UserVo();
			vo.setId(usr.getId());
			vo.setActive(usr.getActive());
			vo.setCreatedAt(usr.getCreatedAt());
			vo.setEmail(usr.getEmail());
			vo.setLastLoginAt(usr.getLocaleId());
			vo.setLocaleId(usr.getLocaleId());
			vo.setName(usr.getName());
			vo.setOrganizationId(usr.getOrganizationId());
			if(usr.getRole()!=null)
				vo.setRole(usr.getRole().name());
			vo.setShared(usr.getShared());
			vo.setSuspended(usr.getSuspended());
			vo.setTimeZone(usr.getTimeZone());
			vo.setUpdatedAt(usr.getUpdatedAt());
			vo.setUrl(usr.getUrl());
			vo.setVerified(usr.getVerified());
			
			
			String userJson = gson.toJson(vo);
			return userJson;
		}else
			return ErrorMessages.SESSION_NOTFOUND;
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
                this.logger.debug(ErrorMessages.GETUSERDETAILS_FAILED, e);
            }
			return ErrorMessages.GETUSERDETAILS_FAILED;
		}
	}
	/**
	 * To create a session based on a userID. Password will be taken if provided. Password needs to be empty if token needs to be considered. Exception will be throws if neither password nor token is provided. 
	 * @param domainName
	 * @param username
	 * @param password
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String getSession(String domainName, String userName,String password,String authToken)
	{
		System.out.println("this.logger.isDebugEnabled() "+this.logger.isDebugEnabled());
		try{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("session id " + userName);
			}
		Zendesk zd=sessMap.get(userName);
		long usersessionid=0;
		if(zd==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Making builder");
			}
			 Builder builder= new Zendesk.Builder(domainName);
			 builder.setUsername(userName);
			 if (this.logger.isDebugEnabled()) {
					this.logger.debug("After setting username");
			 }
			 if(password!=null && password.length()>0) //Take the passsword
				{
				 builder.setPassword(password);
				}
			 else if(authToken!=null || authToken.length()>0)//Error 
				{
				 builder.setToken(authToken);
				}
			 else
				 {
				 return ErrorMessages.INVALID_USER_INPUT;
				 }
			 
			 if (this.logger.isDebugEnabled()) {
					this.logger.debug("Going to build");
			 }
			 zd=builder.build();
			 if (this.logger.isDebugEnabled()) {
					this.logger.debug("Build sucessful");
			 }
			 //zd.getCurrentUser().getActive();//To make sure that the login works fine
			  usersessionid = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			  if (this.logger.isDebugEnabled()) {
					this.logger.debug("UserID Created");
			  }
			  try{
			 if(zd.getCurrentUser().getEmail().equalsIgnoreCase(userName))
			 {
				 if (this.logger.isDebugEnabled()) {
						this.logger.debug("Valid User");
				 }
				 sessMap.put(usersessionid+"", zd);
				 if (this.logger.isDebugEnabled()) {
						this.logger.debug("**Size*** " +sessMap.size());
				 }
			 }
			 else
			 {
				 if (this.logger.isDebugEnabled()) {
						this.logger.debug("Invalid User");
				 }
				 zd.close();
				 return ErrorMessages.INVALID_CREDENTIALS;
			 }
			  }catch(Exception e)
			  {
				  if (this.logger.isDebugEnabled()) {
						this.logger.debug("Exception happened " + e.toString());
				  }
				  return ErrorMessages.INVALID_CREDENTIALS;
			  }
			 return usersessionid+"";
		}else
			return usersessionid+"";
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
                this.logger.debug(ErrorMessages.GETUSERDETAILS_FAILED, e);
            }
			return ErrorMessages.GETUSERDETAILS_FAILED;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			
					
			SessionManagement mgmt=SessionManagement.getUserSessions();
			String session=mgmt.getSession("https://tibcohack.zendesk.com", "sivag2012@gmail.com", "password", "");
			System.out.println(session);
			String sess=mgmt.getUserDetails(session);
			System.out.println(sess);
			String clos=mgmt.closeSession(session);
			System.out.println(clos);
			
			/*
			Zendesk zd=mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "tcihackathon@gmail.com", "", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
			Zendesk zd1=mgmt.getUserSession(mgmt.getSession("https://tibcohack.zendesk.com", "sivag2012@gmail.com", "password", ""));
			System.out.println(zd.getCurrentUser().getEmail());
			System.out.println(zd1.getCurrentUser().getEmail());
			System.out.println(zd.getCurrentUser().getEmail());
			System.out.println(zd1.getCurrentUser().getEmail());
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
