package tcizendesk.module.common;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.Type;
import org.zendesk.client.v2.model.hc.Article;

public class MainTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SessionManagement mgmt=SessionManagement.getUserSessions();
			Zendesk zd = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "customer@gmail.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
			//zd.get
			//Article art= new Article();
			
			Zendesk zd1 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "engineer@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
			//zd.get
			Zendesk zd2 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "admin@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
			//zd.get
			
			//int i =9354245207;
			
			Zendesk zd3 = mgmt.getUserSession(mgmt.getSession("https://tcihackathon.zendesk.com", "engineer1@test.com", "password", "TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU"));
			//zd.get
			
			System.out.println(zd.getCurrentUser().getEmail());
			System.out.println(zd1.getCurrentUser().getEmail());
			System.out.println(zd2.getCurrentUser().getEmail());
			
			System.out.println("Customer "+ zd.getCurrentUser().getId());
			System.out.println("Engineer " +zd1.getCurrentUser().getId()+ " " + zd3.getCurrentUser().getId());
			System.out.println("Admin "+zd2.getCurrentUser().getId());
			
			//SessionManagement.getUserSessions().closeSession("customer@test.com");
			//SessionManagement.getUserSessions().closeSession("engineer@test.com");
			//SessionManagement.getUserSessions().closeSession("admin@test.com");
			
			//System.out.println(zd.getCurrentUser().getEmail());
			//System.out.println(zd1.getCurrentUser().getEmail());
			//System.out.println(zd2.getCurrentUser().getEmail());
			
			//Create a ticket
			
			//ZenTicket ticket= new ZenTicket();
			//Ticket tick=ticket.createTicket(zd, "System not working", "Please attend it ASAP");
			//ticket.setAssigneeId(dk, id, assigneeId)
			
			
			/*
			ZenTicket ticket= new ZenTicket();
			Ticket tick=ticket.createTicket("1234", "System not working again new solved", "Please attend it ASAP","");
			ticket.setAssigneeId(zd2, tick.getId(), zd3.getCurrentUser().getId());
			
			tick.setPriority(Priority.HIGH);
			tick.setType(Type.PROBLEM);
			ticket.updateTicket(zd3, tick);
			
			ticket.setStatus(zd3, tick.getId(), "solved");
			
			ticket.getTickets(zd2);
			
			*/
			
			SessionManagement.getUserSessions().closeSession("customer@test.com");
			SessionManagement.getUserSessions().closeSession("engineer@test.com");
			SessionManagement.getUserSessions().closeSession("engineer1@test.com");
			SessionManagement.getUserSessions().closeSession("admin@test.com");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public static void createTicket()
{
	
}
}
