package com.tibco.Stoken;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
//Modified by Nitin start 15-08-2016//
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;
//Modified by Nitin end 15-08-2016//
public class invoice_create {
	// Modified by Nitin start 15-08-2016//
			@SuppressWarnings("rawtypes")
			
			public static ActivityContext activityConfigContext = null;
			@BWActivityContext
			public static void setActivityContext(@SuppressWarnings("rawtypes") ActivityContext act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
				activityConfigContext =  act;
			}
			// Modified by Nitin end 15-08-2016//
public static String invoiceCreate(String CustomerId)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		
		
	// Modified by Nitin start 15-08-2016//
			//Stripe.apiKey = "sk_test_R6YhheMbEXzja80Gk70GrSlr";
			activityConfigContext.registerModuleProperty("STRIPE_APIKEY");
			System.out.println("Module property STRIPE_APIKEY value : " + activityConfigContext.getModuleProperty("STRIPE_APIKEY"));
			Stripe.apiKey = activityConfigContext.getModuleProperty("STRIPE_APIKEY");
			// Modified by Nitin end 15-08-2016//

	Map<String, Object> invoiceParams = new HashMap<String, Object>();
	invoiceParams.put("customer", CustomerId);
		//invoiceParams.put("limit", 3);



		

		try
		{
			Invoice invoice=Invoice.create(invoiceParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		//System.out.println("Execute success");
		//System.out.println(invoice);
		//System.out.println("Execute getData()");
		//System.out.println(invoice.getData());
		//System.out.println("Execute success of getData()");
		String test=invoice.toString();
		/*Gson gson = new Gson(); 
		String json = gson.toJson(invoice);*/ 
		String returnValue=test.substring(test.indexOf("{"));
	
		return returnValue; 
		
		}
		catch(Exception E){
			System.out.println(E);
			return(null);
		}
		finally{
			
		}
	}
public static String invoiceCreate(String CustomerId, String stripeApikey){
		
		
			Stripe.apiKey = stripeApikey;

	Map<String, Object> invoiceParams = new HashMap<String, Object>();
	invoiceParams.put("customer", CustomerId);
		//invoiceParams.put("limit", 3);



		

		try
		{
			Invoice invoice=Invoice.create(invoiceParams);
		
		
		//Token token = null;
		//token = Token.create(tokenParams);
		//token = com.stripe.model.Token.create(tokenParams);
		//System.out.println("Execute success");
		//System.out.println(invoice);
		//System.out.println("Execute getData()");
		//System.out.println(invoice.getData());
		//System.out.println("Execute success of getData()");
		String test=invoice.toString();
		/*Gson gson = new Gson(); 
		String json = gson.toJson(invoice);*/ 
		String returnValue=test.substring(test.indexOf("{"));
	
		return returnValue; 
		
		}
		catch(Exception E){
			System.out.println(E);
			return(null);
		}
		finally{
			
		}
	}

}
