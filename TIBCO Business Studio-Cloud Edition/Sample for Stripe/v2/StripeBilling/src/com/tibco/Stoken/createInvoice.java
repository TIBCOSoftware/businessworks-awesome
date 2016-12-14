package com.tibco.Stoken;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Invoice;
//Modified by Nitin start 15-08-2016//
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;
//Modified by Nitin end 15-08-2016//
public class createInvoice {
	// Modified by Nitin start 15-08-2016//
			@SuppressWarnings("rawtypes")
			
			public static ActivityContext activityConfigContext = null;
			@BWActivityContext
			public static void setActivityContext(@SuppressWarnings("rawtypes") ActivityContext act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
				activityConfigContext =  act;
			}
			// Modified by Nitin end 15-08-2016//
	public String generateInvoice(String CustomerId)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		// Modified by Nitin start 15-08-2016//
				//Stripe.apiKey = "sk_test_R6YhheMbEXzja80Gk70GrSlr";
				activityConfigContext.registerModuleProperty("STRIPE_APIKEY");
				System.out.println("Module property STRIPE_APIKEY value : " + activityConfigContext.getModuleProperty("STRIPE_APIKEY"));
				Stripe.apiKey = activityConfigContext.getModuleProperty("STRIPE_APIKEY");
				// Modified by Nitin end 15-08-2016//

		Map<String, Object> invoiceParams = new HashMap<String, Object>();
		invoiceParams.put("customer", "cus_92XAV9zeo1fd3D");

		try {
			Invoice invoice=Invoice.create(invoiceParams);
			
			String out=invoice.toString();
			String returnValue=out.substring(out.indexOf('{'));
			return returnValue;
		} catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
		
	}
	public String generateInvoice(String CustomerId, String apikey)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
		Stripe.apiKey = apikey;
		Map<String, Object> invoiceParams = new HashMap<String, Object>();
		invoiceParams.put("customer", "cus_92XAV9zeo1fd3D");

		try {
			Invoice invoice=Invoice.create(invoiceParams);
			
			String out=invoice.toString();
			String returnValue=out.substring(out.indexOf('{'));
			return returnValue;
		} catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
		
	}
	
}
