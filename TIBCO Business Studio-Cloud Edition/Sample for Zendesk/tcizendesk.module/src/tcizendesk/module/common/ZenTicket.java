package tcizendesk.module.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Audit;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.SatisfactionRating;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.Type;

import tcizendesk.module.common.vos.TicketVo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ZenTicket {

	private final Logger       logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionManagement mgmt=SessionManagement.getUserSessions();
		String sessionid=mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU");
		//Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
		//long sessionId= zd2.getCurrentUser().getId();
		System.out.println("got it");
		ZenTicket tick=new ZenTicket();		
		System.out.println("got it 1");
		//String ticket=tick.createTicket(sessionid,"Test Subject","test Comment", "test Description");
		System.out.println("got it 2");
		tick.getTickets(sessionid);
		//System.out.println(ticket);
		mgmt.closeSession(sessionid);
		//zd2.close();
		//zd.get
	}
	/**
	 * To get ticket audits
	 * @param sessionId
	 * @param ticketId
	 * @return
	 */
	public String getTicketAudit(String sessionId, String ticketId)
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
			
			Ticket ticket=dk.getTicket((long)Integer.parseInt(ticketId));
			
			if(ticket==null)
			{
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ErrorMessages.INVALID_TICKET);
				}
				return ErrorMessages.INVALID_TICKET;
			}
			Iterable<Audit> audits=dk.getTicketAudits(ticket);
			StringBuffer buff=new StringBuffer();
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			for(Audit aut:audits)
			{
				buff.append(gson.toJson(aut));
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(aut.toString());
				}
			}
			
			
			//String auditJson = gson.toJson(audits);
			//String userJson = gson.toJson(ticket);
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
	 * To retrive a ticket
	 * @param sessionId
	 * @param ticketid
	 * @return
	 */
	public String getTicket(String sessionId,String ticketId)
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
		Ticket ticket=dk.getTicket((long)Integer.parseInt(ticketId));
		if(ticket==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(ErrorMessages.INVALID_TICKET);
			}
			return ErrorMessages.INVALID_TICKET;
		}
		//Dirty fix .BW doesn't handle null in long
		if(ticket.getAssigneeId()==null)
			ticket.setAssigneeId(0l);
		
		if(ticket.getGroupId()==null)
			ticket.setGroupId(0l);
		
		if(ticket.getSubmitterId()==null)
			ticket.setSubmitterId(0l);
		
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String userJson = gson.toJson(ticket);
		//String userJson = gson.toJson(ticket);
		return userJson;
	}catch(Exception e)
	{
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(e.toString(),e);
		}
		return "ERROR : "+ e.toString();
	}
		//return "sucessfull";
	}
	/**
	 * To delete existing ticket
	 * @param sessionId
	 * @param ticketid
	 * @return
	 */
	public String deleteTicket(String sessionId,String ticketid)
	{
		try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}
			dk.deleteTicket((long)Integer.parseInt(ticketid));
			return ErrorMessages.SUCCESS;
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(e.toString(),e);
			}
			return "ERROR : "+ e.toString();
		}
	}
	/**
	 * Internal method to avoid boiler plate code
	 * @param ticket
	 * @param sessionId
	 * @param ticketId
	 * @param url
	 * @param external_id
	 * @param type
	 * @param subject
	 * @param description
	 * @param priority
	 * @param status
	 * @param recipient
	 * @param requester_id
	 * @param submitter_id
	 * @param assignee_id
	 * @param organization_id
	 * @param group_id
	 * @param collaborator_ids
	 * @param problem_id
	 * @param due_at
	 * @param comment
	 * @return
	 */
	private String saveTicket(Ticket ticket,String sessionId,String ticketId,String external_id, String type, String subject, 
			String priority, String status, String recipient, long requester_id, long submitter_id, long assignee_id, 
			int organization_id, long group_id, String collaborator_ids, int problem_id, Date due_at)
	{
		try{
		if(assignee_id>0)
			ticket.setAssigneeId((long)assignee_id);
		//if(url!=null)
		//	ticket.setUrl(url);
		//ticket.setBrandId(brandId);
		//ticket.setCollaboratorIds(collaboratorIds);
		//if(comment!=null)
		//	ticket.setComment(new Comment(comment));
		if(external_id!=null)
			ticket.setExternalId(external_id);
		if(type!=null)
		{
		if(type.equalsIgnoreCase("incident"))
			 ticket.setType(Type.INCIDENT);
		 else if(type.equalsIgnoreCase("problem"))
			 ticket.setType(Type.PROBLEM);
		 else if(type.equalsIgnoreCase("question"))
			 ticket.setType(Type.QUESTION);
		 else if(type.equalsIgnoreCase("task"))
			 ticket.setType(Type.TASK);
		}
		if(priority!=null)
		{
		if(priority.equalsIgnoreCase("high"))
			  ticket.setPriority(Priority.HIGH);
		  else if(priority.equalsIgnoreCase("urgent"))
			  ticket.setPriority(Priority.URGENT);
		  else if(priority.equalsIgnoreCase("normal"))
			  ticket.setPriority(Priority.NORMAL);
		  else if(priority.equalsIgnoreCase("low"))
			  ticket.setPriority(Priority.LOW);
		}
		if(subject!=null)
			ticket.setSubject(subject);
		//if(description!=null)
		//	ticket.setDescription(description);
		if(status != null)
		{
		if(status.equalsIgnoreCase("new"))
			  ticket.setStatus(Status.NEW);
		  else if(status.equalsIgnoreCase("open"))
			  ticket.setStatus(Status.OPEN);
		  else if(status.equalsIgnoreCase("pending"))
			  ticket.setStatus(Status.PENDING);
		  else if(status.equalsIgnoreCase("hold"))
			  ticket.setStatus(Status.HOLD);
		  else if(status.equalsIgnoreCase("solved"))
			  ticket.setStatus(Status.SOLVED);
		  else if(status.equalsIgnoreCase("closed"))
			  ticket.setStatus(Status.CLOSED);
		}
		if(recipient!=null)
			ticket.setRecipient(recipient);
		if(requester_id>0)
			ticket.setRequesterId((long)requester_id);
		if(submitter_id>0)
			ticket.setSubmitterId((long)submitter_id);
		if(assignee_id>0)
			ticket.setAssigneeId((long)assignee_id);
		if(organization_id>0)
			ticket.setOrganizationId((long)organization_id);
		if(group_id>0)
			ticket.setGroupId((long)group_id);
		if(problem_id>0)
			ticket.setProblemId((long)problem_id);
		//int problem_id, Date due_at, Date created_at, Date updated_at, boolean is_public, String comment
		//icket.setComment(comment);
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk!=null)
		{
			Ticket tick=dk.createTicket(ticket);
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			String userJson = gson.toJson(tick);
			return userJson;
		}else
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(e.toString(),e);
			}
			return "ERROR : "+ e.toString();
		}
		
	}
	/**
	 * The following method will update an existing ticket
	 * @param sessionId
	 * @param id
	 * @param url
	 * @param external_id
	 * @param type
	 * @param subject
	 * @param description
	 * @param priority
	 * @param status
	 * @param recipient
	 * @param requester_id
	 * @param submitter_id
	 * @param assignee_id
	 * @param organization_id
	 * @param group_id
	 * @param collaborator_ids
	 * @param problem_id
	 * @param due_at
	 * @param comment
	 * @return
	 */
	public String updateTicket(String sessionId,String ticketId, String url,String external_id, String type, String subject, 
			String description, String priority, String status, String recipient, long requester_id, long submitter_id, long assignee_id, 
			int organization_id, long group_id, String collaborator_ids, int problem_id, Date due_at, String comment, boolean is_public)
	{
		Ticket ticket=new Ticket();		
		ticket.setId((long)Integer.parseInt(ticketId));
		Comment comm=new Comment(comment);
		comm.setPublic(is_public);
		ticket.setComment(comm);
		ticket.setUrl(url);
		String returnVal=saveTicket(ticket,sessionId,ticketId, external_id, type, subject, 
				 priority, status, recipient,requester_id, submitter_id, assignee_id, 
				organization_id, group_id, collaborator_ids, problem_id, due_at);
		
		return returnVal;
		//return "Failed due to unknown reason";
	}
	/**
	 * The following method will create a ticket
	 * @param sessionId
	 * @param id
	 * @param url
	 * @param external_id
	 * @param type
	 * @param subject
	 * @param description
	 * @param priority
	 * @param status
	 * @param recipient
	 * @param requester_id
	 * @param submitter_id
	 * @param assignee_id
	 * @param organization_id
	 * @param group_id
	 * @param collaborator_ids
	 * @param problem_id
	 * @param due_at
	 * @param comment
	 * @return
	 */
	public String createTicket(String sessionId,String ticketId, String url,String external_id, String type, String subject, 
			String description, String priority, String status, String recipient, int requester_id, int submitter_id, int assignee_id, 
			int organization_id, int group_id, String collaborator_ids, int problem_id, Date due_at, String comment, boolean is_public)
	{
		Ticket ticket=new Ticket();
		ticket.setDescription(description);
		Comment comm=new Comment(comment);
		comm.setPublic(is_public);
		ticket.setComment(comm);
		
		String returnVal=saveTicket(ticket,sessionId,ticketId,external_id, type, subject, 
				 priority, status, recipient,requester_id, submitter_id, assignee_id, 
				organization_id, group_id, collaborator_ids, problem_id, due_at);
		
		return returnVal;
		
		//return "Failed due to unknown reason";
	}
	/**
	 * To create a simple ticket
	 * @param dk
	 * @param subject
	 * @param comment
	 * @return
	 */
  public String createTicket(String sessionId, String subject, String comment, String description)
  {
	  try{
			Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
			if(dk==null)
			{
				return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
			}
			 Comment commen = new Comment(comment);
			  Ticket ticket=new Ticket(dk.getCurrentUser().getId(),subject,commen);
			 
			  ticket.setDescription(description);
			  ticket=dk.createTicket(ticket);			  
			  
			  TicketVo vo= new TicketVo();
			  vo.setId(ticket.getId());
			  //vo.setAssigneeId(ticket.getAssigneeId());
			  vo.setBrandId(ticket.getBrandId());
			  vo.setDueAt(ticket.getDueAt());
			  vo.setHasIncidents(ticket.isHasIncidents());
			  vo.setUrl(ticket.getUrl());
			  vo.setSubject(ticket.getSubject());
			  vo.setDescription(ticket.getDescription());
			  if(ticket.getStatus()!=null)
				  vo.setStatus(ticket.getStatus().name());
			  vo.setRequesterId(ticket.getRequesterId());
			  vo.setOrganizationId(ticket.getOrganizationId());
			  if(ticket.getPriority()!=null)
				  vo.setPriority(ticket.getPriority().name());
			  
			  if(ticket.getType()!=null)
				  vo.setType(ticket.getType().name());
			  vo.setCreatedAt(ticket.getCreatedAt());
			  vo.setUpdatedAt(ticket.getUpdatedAt());
			
			 
			 // System.out.println("created");
			 /*
			//Dirty fix .BW doesn't handle null in long
				if(ticket.getAssigneeId()==null)
					ticket.setAssigneeId(0l);
				if(ticket.getRequesterId()==null)
					ticket.setRequesterId(0l);
				if(ticket.getGroupId()==null)
					ticket.setGroupId(0l);				
				if(ticket.getSubmitterId()==null)
					ticket.setSubmitterId(0l);
				   ticket.setVia(null);
				if(ticket.getOrganizationId()==null)
					ticket.setOrganizationId(0l);
				if(ticket.getProblemId()==null)
					ticket.setProblemId(0l);
					
				*/
			  //System.out.println(ticket);
			  Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			  String value = gson.toJson(vo);
			  System.out.println("gson " + value);
			  return value;
				//return userJson;
			//	return "{\"SubmitterId\":9364075967,\"AssigneeId\":0,\"GroupId\":30517867,\"CollaboratorIds\":[],\"ProblemId\":0,\"HasIncidents\":false,\"Tags\":[],\"CustomFields\":}";
			//return ErrorMessages.SUCCESS;
		}catch(Exception e)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(e.toString(),e);
			}
			return "ERROR : "+ e.toString();
		}
	  
  }
  /**
   * To get all the tickets from the system. It is only for demo purpose and meant for small number of tickets. Search would be the ideal way to get a list.
   * @param dk
   * @return
   */
  public String getTickets(String sessionId)
  {
	  Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
	  Iterable<Ticket> list=dk.getTickets();
	  Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
	  StringBuffer buff=new StringBuffer();
	  
	  for(Ticket tick : list)
	  {
		  String value = gson.toJson(tick);
		  if (this.logger.isDebugEnabled()) {
				this.logger.debug(tick.toString());
			}
		  buff.append(value);
		  
	  }
	  
	 // System.out.println("gson " + value);
	  return buff.toString();
	  //return list;
  }
  /**
   * To update existing ticket
   * @param dk
   * @param ticket
   * @return
   */
  public Ticket updateTicket(Zendesk dk, Ticket ticket)
  {
	  Ticket ticketl=dk.updateTicket(ticket);
	  return ticketl;
  }
  /**
   * To delete an existing ticket
   * @param dk
   * @param ticket
   * @return
   */
  public boolean deleteTicket(Zendesk dk, long id)
  {
	  dk.deleteTicket(id);
	  return true;
  }
  /**
   *  The type of this ticket, i.e. "problem", "incident", "question" or "task"
   * @param dk
   * @param id
   * @param type
   * @return
   * @throws Exception
   */
  public String setType(String sessionId, long id, String type)
  {
	 //Type type= new Type();
	  Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
			}
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
	 Ticket ticket= dk.getTicket(id);
	 if(type.equalsIgnoreCase("incident"))
		 ticket.setType(Type.INCIDENT);
	 else if(type.equalsIgnoreCase("problem"))
		 ticket.setType(Type.PROBLEM);
	 else if(type.equalsIgnoreCase("question"))
		 ticket.setType(Type.QUESTION);
	 else if(type.equalsIgnoreCase("task"))
		 ticket.setType(Type.TASK);
	 else
		 return ErrorMessages.TYPE_NOTFOUND;
	 updateTicket(dk,ticket);
	 return ErrorMessages.UPDATE_SUCCESSFULLY;
  }
  
  public Ticket setSubject(Zendesk dk, long id, String subject) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setSubject(subject);
	  return updateTicket(dk,ticket);
	  
  }
  /**
   *  Priority, defines the urgency with which the ticket should be addressed: "urgent", "high", "normal", "low"
   * @param dk
   * @param id
   * @param priority
   * @return
   * @throws Exception
   */
  public String setPriority(String sessionId, long id, String priority) 
  {
	  Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
			}
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
	  Ticket ticket= dk.getTicket(id);
	  if(priority.equalsIgnoreCase("high"))
		  ticket.setPriority(Priority.HIGH);
	  else if(priority.equalsIgnoreCase("urgent"))
		  ticket.setPriority(Priority.URGENT);
	  else if(priority.equalsIgnoreCase("normal"))
		  ticket.setPriority(Priority.NORMAL);
	  else if(priority.equalsIgnoreCase("low"))
		  ticket.setPriority(Priority.LOW);
	  else
		  return ErrorMessages.PRIORITY_NOTFOUND;
		 updateTicket(dk,ticket);
	return ErrorMessages.UPDATE_SUCCESSFULLY;
	  
  }
  /**
   * The state of the ticket, "new", "open", "pending", "hold", "solved", "closed"
   * @param dk
   * @param id
   * @param status
   * @return
   * @throws Exception
   */
  public String setStatus(String sessionId, long id, String status) throws Exception
  {
	  Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
			}
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
	  Ticket ticket= dk.getTicket(id);
	  if(status.equalsIgnoreCase("new"))
		  ticket.setStatus(Status.NEW);
	  else if(status.equalsIgnoreCase("open"))
		  ticket.setStatus(Status.OPEN);
	  else if(status.equalsIgnoreCase("pending"))
		  ticket.setStatus(Status.PENDING);
	  else if(status.equalsIgnoreCase("hold"))
		  ticket.setStatus(Status.HOLD);
	  else if(status.equalsIgnoreCase("solved"))
		  ticket.setStatus(Status.SOLVED);
	  else if(status.equalsIgnoreCase("closed"))
		  ticket.setStatus(Status.CLOSED);
	  
	  else
		  return ErrorMessages.STATUS_NOTFOUND;
		 updateTicket(dk,ticket);
	return ErrorMessages.UPDATE_SUCCESSFULLY;
	  
  }
  /**
   * To set ticket recipient
   * @param dk
   * @param id
   * @param recipient
   * @return
   * @throws Exception
   */
  public Ticket setRecipient(Zendesk dk, long id, String recipient) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setRecipient(recipient);
	  return updateTicket(dk,ticket);
	  
  }
  /**
   * To set ticket requester 
   * @param dk
   * @param id
   * @param requesterId
   * @return
   * @throws Exception
   */
  public Ticket setRequester_id(Zendesk dk, long id, long requesterId) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setRequesterId(requesterId);
	  return updateTicket(dk,ticket);
	  
  }
  /**
   * To set the submitter id
   * @param dk
   * @param id
   * @param submitterId
   * @return
   * @throws Exception
   */
  public Ticket setSubmitter_id(Zendesk dk, long id, long submitterId) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setSubmitterId(submitterId);
	  return updateTicket(dk,ticket);
	  
  }
  /**
   * To assign the ticket
   * @param dk
   * @param id
   * @param assigneeId
   * @return
   * @throws Exception
   */
  public String setAssigneeId(String sessionId, long id, long assigneeId) 
  {
	  Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			if (this.logger.isDebugEnabled()) {
				this.logger.debug(ErrorMessages.TCI_BW_SESSION_NOT_FOUND);
			}
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
	  Ticket ticket= dk.getTicket(id);
	  ticket.setAssigneeId(assigneeId);
	  updateTicket(dk,ticket);
	  return ErrorMessages.UPDATE_SUCCESSFULLY;
  }
  /**
   * To set group id
   * @param dk
   * @param id
   * @param groupId
   * @return
   * @throws Exception
   */
  public Ticket setGroupId(Zendesk dk, long id, long groupId) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setGroupId(groupId);
	  return updateTicket(dk,ticket);
  }
  /**
   * To set collaborators
   * @param dk
   * @param id
   * @param collaborators
   * @return
   * @throws Exception
   */
  public Ticket setCollaboratorIds(Zendesk dk, long id, List<Long> collaborators) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setCollaboratorIds(collaborators);
	  return updateTicket(dk,ticket);
  }
  /**
   * To set problem id
   * @param dk
   * @param id
   * @param problemId
   * @return
   * @throws Exception
   */
  public Ticket setProblemID(Zendesk dk, long id, long problemId) throws Exception
  {
	  Ticket ticket= dk.getTicket(id);
	  ticket.setProblemId(problemId);
	  return updateTicket(dk,ticket);
  }
  /**
   * To check if it is problem
   * @param dk
   * @param id
   * @return
   */
  public boolean isItAProblem(Zendesk dk, long id)
  {
	  
	  Ticket ticket= dk.getTicket(id);
	  return ticket.isHasIncidents();
	
  }
  /**
   * To get due date
   * @param dk
   * @param id
   * @return
   */
  public Date dueAt(Zendesk dk, long id)
  {
	  
	  Ticket ticket= dk.getTicket(id);
	  return ticket.getDueAt();
	
  }
  /**
   * To get satisfaction rating
   * @param dk
   * @param id
   * @return
   */
  public SatisfactionRating getSatisfactionRating(Zendesk dk, long id)
  {
	  
	  Ticket ticket= dk.getTicket(id);
	  SatisfactionRating rating= ticket.getSatisfactionRating();
	  return rating;
  }
  
}

