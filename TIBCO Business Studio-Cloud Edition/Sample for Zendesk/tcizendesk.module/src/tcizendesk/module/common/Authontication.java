package tcizendesk.module.common;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;

public class Authontication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zendesk zd = new Zendesk.Builder("https://tcihackathon.zendesk.com")
        .setUsername("tcihackathon@gmail.com")
        .setToken("TUpvDfKoPYL7gX8Zwi9fWGBRoenJfwX2AdS1AOfU") // or .setPassword("...")
        //.setPassword("password")
        .build();
		
		User usr=zd.getAuthenticatedUser();
		
		System.out.println(usr.getEmail());
		//try{
		//FileOutputStream fout = new FileOutputStream("c:\\address.ser");
		//ObjectOutputStream oos = new ObjectOutputStream(fout);
		//oos.writeObject(zd);
		//}catch(Exception e)
		//{
		//	e.printStackTrace();
		//}
		zd.close();
	}
	

}
