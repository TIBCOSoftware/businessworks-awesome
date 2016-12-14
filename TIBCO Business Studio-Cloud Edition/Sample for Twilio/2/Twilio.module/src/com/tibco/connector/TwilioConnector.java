package com.tibco.connector;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.tibco.connector.TwilioConstants;

public class TwilioConnector implements TwilioConstants{

   

	public static String makeCall(String to,String ACCOUNT_SID,String AUTH_TOKEN ,String From){
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", to); // Replace with your phone number
        callParams.put("From", From); // Replace with a Twilio number
        callParams.put("Url", Url);
        // Make the call
        Call call = null;
		try {
			call = callFactory.create(callParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Print the call SID (a 32 digit hex like CA123..)
        return call.getSid();
	}
	
	public static String sendSMS(String to,String ACCOUNT_SID,String AUTH_TOKEN,String From){
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		String sid=null;
        Account account = client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", to)); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("From", From)); // Replace with a valid phone number for your account.
        params.add(new BasicNameValuePair("Body",SMS));
        try {
			Message sms = messageFactory.create(params);
			sid=sms.getSid();
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return sid;
	}
	
	public static String getStatusofCall(String sid,String ACCOUNT_SID,String AUTH_TOKEN) throws TwilioRestException {
		    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		    
		    Call call = client.getAccount().getCall(sid);
		    return call.getStatus();
		    
		  }
	public static String getSMSStstus(String sid,String ACCOUNT_SID,String AUTH_TOKEN) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

	    
	    

	    // Get an object from its sid. If you do not have a sid,
	    // check out the list resource examples on this page
	    return client.getAccount().getSms(sid).getStatus();
	    
		
	    
	  }
	
	
}