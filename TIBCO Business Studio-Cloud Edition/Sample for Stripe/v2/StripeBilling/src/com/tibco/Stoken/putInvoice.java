package com.tibco.Stoken;
//Based on listCharges//

import java.util.*;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.Customer;
import com.stripe.model.InvoiceItem;
import com.stripe.model.Token;
import com.google.gson.Gson;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.palette.shared.java.BWActivityContext;
import com.tibco.bw.runtime.ModulePropertyNotFoundException;
import com.tibco.bw.runtime.ModulePropertyNotRegisteredException;

public class putInvoice {
		@SuppressWarnings("rawtypes")
		
		public static ActivityContext activityConfigContext = null;
		@BWActivityContext
		public static void setActivityContext(@SuppressWarnings("rawtypes") ActivityContext act) throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{
			activityConfigContext =  act;
		}
	public static String putInvoiceItem(double amount, String currency, String customer, String description)throws ModulePropertyNotFoundException, ModulePropertyNotRegisteredException{

		activityConfigContext.registerModuleProperty("STRIPE_APIKEY");
		System.out.println("Module property STRIPE_APIKEY value : " + activityConfigContext.getModuleProperty("STRIPE_APIKEY"));
		Stripe.apiKey = activityConfigContext.getModuleProperty("STRIPE_APIKEY");
		
		Map<String, Object> invoiceParams = new HashMap<String, Object>();
		invoiceParams.put("customer", customer);
		invoiceParams.put("amount", (int) amount*100);
		invoiceParams.put("currency", currency);
		invoiceParams.put("description", description);
		
		try {
			InvoiceItem putInvoice = InvoiceItem.create(invoiceParams);
					
			System.out.println("Execute success");
			return putInvoice.toString();
	
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}
public static String putInvoiceItem(double amount, String currency, String customer, String description, String stripeApikey){

		Stripe.apiKey = stripeApikey;
		
		Map<String, Object> invoiceParams = new HashMap<String, Object>();
		invoiceParams.put("customer", customer);
		invoiceParams.put("amount", (int) amount*100);
		invoiceParams.put("currency", currency);
		invoiceParams.put("description", description);
		
		try {
			InvoiceItem putInvoice = InvoiceItem.create(invoiceParams);
					
			System.out.println("Execute success");
			return putInvoice.toString();
	
		}
		catch(Exception E){
			System.out.println(E);
			return("Not generated");
		}
		finally{
			
		}
	}

}
