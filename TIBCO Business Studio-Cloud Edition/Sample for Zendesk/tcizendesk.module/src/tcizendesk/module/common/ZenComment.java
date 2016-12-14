package tcizendesk.module.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Ticket;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ZenComment {
	private final Logger       logger = LoggerFactory.getLogger(getClass());
/**
 * To list comments from ticket
 * @param sessionId
 * @param ticketId
 * @return
 */
public String listComments(String sessionId, String ticketId)
{
	try{
		Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
		if(dk==null)
		{
			return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
		}
		Ticket ticket=dk.getTicket((long)Integer.parseInt(ticketId));
		if(ticket==null)
		{
			return ErrorMessages.INVALID_TICKET;
		}
		Iterable<Comment> comments=dk.getTicketComments((long)Integer.parseInt(ticketId));
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Got the comments");
		}
		StringBuffer buff=new StringBuffer();
		for (Comment item : comments) {
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			String json = gson.toJson(item);
			buff.append(json);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Converted into Json " + json);
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
	 * To create a new comment
	 * @param sessionId
	 * @param ticketId
	 * @param commentId
	 * @param Type
	 * @param body
	 * @param html_body
	 * @param publicbo
	 * @param author_id
	 * @return
	 */
public String insertComment(String sessionId, String ticketId, String Type, 
		String body, String html_body, boolean publicbo, int author_id)
		{
			try{
				Zendesk dk=SessionManagement.getUserSessions().getUserSession(sessionId);
				if(dk==null)
				{
					return ErrorMessages.TCI_BW_SESSION_NOT_FOUND;
				}
				Ticket ticket=dk.getTicket((long)Integer.parseInt(ticketId));
				if(ticket==null)
				{
					return ErrorMessages.INVALID_TICKET;
				}
				Comment comment = new Comment();
				comment.setBody(body);
				comment.setPublic(publicbo);
				if(author_id >0 )
				{
					comment.setId((long)author_id);
				}else
					comment.setId(dk.getCurrentUser().getId());
				
				Ticket tick=dk.createComment(ticket.getId(),comment);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Created Comment ticket");
				}
				Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
				String json = gson.toJson(tick.getComment());
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
