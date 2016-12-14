package com.tibco.Stoken;
import java.util.*;


import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.google.gson.Gson;

//Modified by Nitin start 15-08-2016//
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;
//Modified by Nitin end 15-08-2016//
public class getCustomer  {

	
	// Modified by Nitin start 15-08-2016//
		@SuppressWarnings("rawtypes")
		
		public static ActivityContext activityConfigContext = null;
		@BWActivityContext
		public static void setActivityContext(@SuppressWarnings("rawtypes") ActivityContext act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
			activityConfigContext =  act;
		}
		// Modified by Nitin end 15-08-2016//
	public static String createCustomer(String desc,String source,String email)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		
		
		// Modified by Nitin start 15-08-2016//
		//Stripe.apiKey = "sk_test_R6YhheMbEXzja80Gk70GrSlr";
		activityConfigContext.registerModuleProperty("STRIPE_APIKEY");
		System.out.println("Module property STRIPE_APIKEY value : " + activityConfigContext.getModuleProperty("STRIPE_APIKEY"));
		Stripe.apiKey = activityConfigContext.getModuleProperty("STRIPE_APIKEY");
		// Modified by Nitin end 15-08-2016//
		
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", "Customer for test@example.com");
		customerParams.put("source", source);// obtained with Stripe.js
		customerParams.put("email", email);

		

		try
		{
			Customer customer=Customer.create(customerParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		System.out.println("Execute success");
		return customer.getId(); 
		
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}
	public static String createCustomer(String desc,String source,String email, String stripeApikey)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		
		
		Stripe.apiKey = stripeApikey;
		
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", "Customer for test@example.com");
		customerParams.put("source", source);// obtained with Stripe.js
		customerParams.put("email", email);

		

		try
		{
			Customer customer=Customer.create(customerParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		System.out.println("Execute success");
		return customer.getId(); 
		
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}
	
}
