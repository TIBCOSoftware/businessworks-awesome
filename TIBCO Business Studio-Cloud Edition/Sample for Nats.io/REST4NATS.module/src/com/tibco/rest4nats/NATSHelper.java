package com.tibco.rest4nats;

import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;
import io.nats.client.Message;
import io.nats.client.Subscription;
import io.nats.client.SyncSubscription;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

public class NATSHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public NATSHelper() {
		
	}

	public static boolean isPingable (String url) throws Exception 
	{
		/* 
		URI uri = new URI (url);
		InetAddress inet = InetAddress.getByName(uri.getHost());

		if (!inet.isReachable(500000)) return false;
		*/
        ConnectionFactory cf = new ConnectionFactory(url);
        try (Connection nc = cf.createConnection()) {
            if (nc.getConnectedUrl().equals(url)) return true;
            nc.close();
        } catch (TimeoutException e) {
        	return false;
        }
        return false;
	}

	public static void publish (String url, String subject, String msg) throws Exception 
	{
		ConnectionFactory cf = new ConnectionFactory(url);
	    Connection nc = cf.createConnection();
	    nc.publish(subject, msg.getBytes(Charset.forName("UTF-8")));
	    nc.close();
	}
	
	public static String request (String url, String subject, String msg, long reply_timeout ) throws Exception 
	{
		ConnectionFactory cf = new ConnectionFactory(url);
	    Connection nc = cf.createConnection();

	    Message message = nc.request(subject, msg.getBytes(Charset.forName("UTF-8")), reply_timeout);

	    nc.close();

	    return toJSON(message.getReplyTo(), message.getData());

	}
	
	public static String subscribe (String url, String subject, long timeout) throws Exception
	{
		ConnectionFactory cf = new ConnectionFactory(url);
	    Connection nc = cf.createConnection();
	    
	    SyncSubscription sub = nc.subscribeSync(subject);
	    Message msg = sub.nextMessage(timeout);
	    sub.unsubscribe();
	    nc.close();

	    return toJSON(msg.getReplyTo(), msg.getData());
	}
	
	public static String subscribe_to_queue (String url, String subject, String queue, long timeout) throws Exception
	{
		ConnectionFactory cf = new ConnectionFactory(url);
	    Connection nc = cf.createConnection();
	    
	    SyncSubscription sub = nc.subscribeSync(subject, queue);
	    Message msg = sub.nextMessage(timeout);
	    sub.unsubscribe();
	    nc.close();

	    return toJSON(msg.getReplyTo(), msg.getData());
	}
	
	private static String toJSON (String replyTo, byte[] body)
	{
		StringBuffer sb = new StringBuffer("{");
		
		if (replyTo != null && replyTo.length() > 0) {
			sb.append("\"reply_to\":" + '"' + replyTo +'"'+',');
		}
		
		String fromUTF = new String(body, Charset.forName("UTF-8"));
		sb.append(" \"body\":"+'"' + fromUTF + '"' + "}");
		return sb.toString();
	}
	
}
