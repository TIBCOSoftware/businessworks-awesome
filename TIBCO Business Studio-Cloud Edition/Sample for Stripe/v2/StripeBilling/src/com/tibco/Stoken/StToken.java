package com.tibco.Stoken;

import java.util.HashMap;

import java.util.Map;


import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Token;
import com.google.gson.Gson;
//Modified by Nitin start 15-08-2016//
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;
//Modified by Nitin end 15-08-2016//

public class StToken  {
	// Modified by Nitin start 15-08-2016//
	@SuppressWarnings("rawtypes")
	
	public static ActivityContext activityConfigContext = null;
	@BWActivityContext
	public static void setActivityContext(@SuppressWarnings("rawtypes") ActivityContext act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		activityConfigContext =  act;
	}
	// Modified by Nitin end 15-08-2016//
	
	public static String createToken(String number,String exp_month,String exp_year,String cvc)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		
		// Modified by Nitin start 15-08-2016//
		//Stripe.apiKey = "sk_test_R6YhheMbEXzja80Gk70GrSlr";
		activityConfigContext.registerModuleProperty("STRIPE_APIKEY");
		System.out.println("Module property STRIPE_APIKEY value : " + activityConfigContext.getModuleProperty("STRIPE_APIKEY"));
		Stripe.apiKey = activityConfigContext.getModuleProperty("STRIPE_APIKEY");
		// Modified by Nitin end 15-08-2016//

		Map<String, Object> tokenParams = new HashMap<String, Object>();
		Map<String, Object> cardParams = new HashMap<String, Object>();
		cardParams.put("number", number);
		cardParams.put("exp_month", exp_month);
		cardParams.put("exp_year", exp_year);
		cardParams.put("cvc", cvc);
		tokenParams.put("card", cardParams);

		try
		{
		Token token = Token.create(tokenParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		System.out.println("Execute success");
		return token.getId(); 
		
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}
	public static String createToken(String number,String exp_month,String exp_year,String cvc, String stripeApikey){
		
		Stripe.apiKey = stripeApikey;
	
		Map<String, Object> tokenParams = new HashMap<String, Object>();
		Map<String, Object> cardParams = new HashMap<String, Object>();
		cardParams.put("number", number);
		cardParams.put("exp_month", exp_month);
		cardParams.put("exp_year", exp_year);
		cardParams.put("cvc", cvc);
		tokenParams.put("card", cardParams);

		try
		{
		Token token = Token.create(tokenParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		System.out.println("Execute success");
		return token.getId(); 
		
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}
}