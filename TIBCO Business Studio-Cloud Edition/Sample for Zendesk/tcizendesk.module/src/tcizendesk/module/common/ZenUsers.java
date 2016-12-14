package tcizendesk.module.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Audit;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ZenUsers {
	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zendesk zd;
		try {
			SessionManagement mgmt=SessionManagement.getUserSessions();
			String sesson = mgmt.getSession("https://tcihackathon.zendesk.com", "tcihackathon@gmail.com", "", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU");
			ZenUsers users= new ZenUsers();			
			String user=users.getUsers(sesson);
			System.out.println(user);
			user=users.getUserOnRole(sesson, "agent");
			System.out.println(user);
			user=users.getUserOnEmail(sesson, "engineer@test.com");
			System.out.println(user);
			/*
			 for (User item : user) {				
				 System.out.println(item.getName());
				 System.out.println(item.getRole());
			        System.out.println(item.getId());
			    }
			    */
			 mgmt.closeSession(sesson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
 public User createUser(Zendesk zd,String userName,String emailId)
 {
	 User usr=new User();
	 usr.setName(userName);
	 return usr;
	// usr.setEmail(email);
	//usr.set
	 
 }
 /**
  * To get users based on role
  * @param sessionId
  * @param role
  * @return
  */
 public String getUserOnEmail(String sessionId, String emailId)
 {	
	 try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
				}
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}
			
			
			
			Iterable<User> users=dk.getUsers();
						
			StringBuffer buff=new StringBuffer();
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			for(User aut:users)
			{
				if(aut.getEmail().equalsIgnoreCase(emailId))
				{
					buff.append(gson.toJson(aut));
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(aut.toString());
				}
					return buff.toString();
				}
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
 /**
  * To get users based on role
  * @param sessionId
  * @param role
  * @return
  */
 public String getUserOnRole(String sessionId, String role)
 {	
	 try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
				}
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}
			
			Iterable<User> users=dk.getUsersByRole(role);
						
			StringBuffer buff=new StringBuffer();
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			for(User aut:users)
			{
				buff.append(gson.toJson(aut));
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(aut.toString());
				}
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
/**
 * 
 * @param sessionId
 * @return
 */
 public String getUsers(String sessionId)
 {	
	 try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
				}
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}
			
			Iterable<User> users=dk.getUsers();
						
			StringBuffer buff=new StringBuffer();
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			for(User aut:users)
			{
				buff.append(gson.toJson(aut));
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(aut.toString());
				}
			}						
			
			return buff.toString();
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(e.toString(),e);
			}
			return "ERROR : "+e.toString();
		}
 }
}
