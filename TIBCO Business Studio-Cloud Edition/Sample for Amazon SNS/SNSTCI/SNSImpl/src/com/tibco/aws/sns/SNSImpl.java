package com.tibco.aws.sns;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathFactory;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.AddPermissionRequest;
import com.amazonaws.services.sns.model.AddPermissionResult;
import com.amazonaws.services.sns.model.CheckIfPhoneNumberIsOptedOutRequest;
import com.amazonaws.services.sns.model.CheckIfPhoneNumberIsOptedOutResult;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.DeleteEndpointResult;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.DeletePlatformApplicationResult;
import com.amazonaws.services.sns.model.DeleteTopicResult;
import com.amazonaws.services.sns.model.GetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesResult;
import com.amazonaws.services.sns.model.GetSMSAttributesRequest;
import com.amazonaws.services.sns.model.GetSMSAttributesResult;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationRequest;
import com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationResult;
import com.amazonaws.services.sns.model.ListPhoneNumbersOptedOutRequest;
import com.amazonaws.services.sns.model.ListPhoneNumbersOptedOutResult;
import com.amazonaws.services.sns.model.ListPlatformApplicationsRequest;
import com.amazonaws.services.sns.model.ListPlatformApplicationsResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.OptInPhoneNumberRequest;
import com.amazonaws.services.sns.model.OptInPhoneNumberResult;
import com.amazonaws.services.sns.model.RemovePermissionRequest;
import com.amazonaws.services.sns.model.RemovePermissionResult;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.SetEndpointAttributesResult;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesResult;
import com.amazonaws.services.sns.model.SetSMSAttributesRequest;
import com.amazonaws.services.sns.model.SetSMSAttributesResult;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.SetTopicAttributesRequest;
import com.amazonaws.services.sns.model.SetTopicAttributesResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sns.model.UnsubscribeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class SNSImpl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static AmazonSNSClient snsClient= null;
	private static boolean debugflag=true;

	/**
     * <p>
     * Initializes region & credentials of the AWS account
     *  user.
     * </p>
     *
     */
	
	public SNSImpl(String accessKey, String secretKey)
	{
		
		if (debugflag==true) System.out.println("===========================================");
        if (debugflag==true) System.out.println("*********** TIBCO Amazon SNS **************");
        if (debugflag==true) System.out.println("===========================================\n");
        
		BasicAWSCredentials credentials = null;
		credentials = new BasicAWSCredentials(accessKey, secretKey);
		Region region = Region.getRegion(Regions.US_EAST_1);
		
		AmazonSNSClient snsClientObject = new AmazonSNSClient(credentials);	
		snsClientObject.setRegion(region);
        snsClient = snsClientObject;
		
	}


	/**
     * <p>
     * Converts Java objects to JSON strings
     *  user.
     * </p>
     *
     */	
	public static String jsonify(Object message) {
		try {
			if (debugflag==true) if (debugflag==true) System.out.println("jsonify output: "+objectMapper.writeValueAsString(message));
			return objectMapper.writeValueAsString(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw (RuntimeException) e;
		}
	}

	/**
     * <p>
     * Adds a statement to a topic's access control policy, granting access
     * for the specified AWS accounts to the specified actions.
     * </p>
     *
     */

	public static String  addSNSPermission(String topicArn, String label, String aWSAccountIdsJSON, String actionNamesJSON)throws AmazonServiceException, AmazonClientException
 {
	
		if (debugflag==true) System.out.println("aWSAccountIdsJSON - " + aWSAccountIdsJSON);
		if (debugflag==true) System.out.println("actionNamesJSON - " + actionNamesJSON);

		
        Type accountIdsToken = new TypeToken<List<String>>(){}.getType();
        List<String> aWSAccountIds = new Gson().fromJson(aWSAccountIdsJSON.substring(aWSAccountIdsJSON.indexOf("["), aWSAccountIdsJSON.indexOf("]")+1), accountIdsToken);
        
        Type actionNamesToken = new TypeToken<List<String>>(){}.getType();
        List<String> actionNames = new Gson().fromJson(actionNamesJSON.substring(actionNamesJSON.indexOf("["), actionNamesJSON.indexOf("]")+1), actionNamesToken);
		
			AddPermissionRequest addPermissionRequest = new AddPermissionRequest();
			addPermissionRequest.setTopicArn(topicArn);
			addPermissionRequest.setLabel(label);
			addPermissionRequest.setAWSAccountIds(aWSAccountIds);
			addPermissionRequest.setActionNames(actionNames);
			AddPermissionResult addPermissionResult=snsClient.addPermission(addPermissionRequest);

			if (debugflag==true) System.out.println("AddPermissionRequest - " + addPermissionRequest);
		    if (debugflag==true) System.out.println("AddPermissionResult - " +  addPermissionResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(addPermissionRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    AddPermissionResponse addPermissionResponse=new AddPermissionResponse();			    
		   
		    addPermissionResponse.setResponseMetadata(responseMetadata);
			
			
			return jsonify(addPermissionResponse);
	
	}

	/**
     * <p>
     * Checks if phone number is opted out from receiving 
     * notifications.
     * </p>
     *
     */

	public static String  checkSNSIfPhoneNumberIsOptedOut(String phoneNumber)throws AmazonServiceException, AmazonClientException {
	
	       CheckIfPhoneNumberIsOptedOutRequest  checkIfPhoneNumberIsOptedOutRequest = new CheckIfPhoneNumberIsOptedOutRequest();
		   checkIfPhoneNumberIsOptedOutRequest.setPhoneNumber(phoneNumber);
		   CheckIfPhoneNumberIsOptedOutResult checkIfPhoneNumberIsOptedOutResult=snsClient.checkIfPhoneNumberIsOptedOut(checkIfPhoneNumberIsOptedOutRequest);
	     
		   
			if (debugflag==true) System.out.println("CheckIfPhoneNumberIsOptedOutRequest - " + checkIfPhoneNumberIsOptedOutRequest);
		    if (debugflag==true) System.out.println("CheckIfPhoneNumberIsOptedOutResult - " +  checkIfPhoneNumberIsOptedOutResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(checkIfPhoneNumberIsOptedOutRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    CheckIfPhoneNumberIsOptedOutResponse checkIfPhoneNumberIsOptedOutResponse=new CheckIfPhoneNumberIsOptedOutResponse();			    
		    
		    checkIfPhoneNumberIsOptedOutResponse.setCheckIfPhoneNumberIsOptedOutResult(checkIfPhoneNumberIsOptedOutResult);
		    checkIfPhoneNumberIsOptedOutResponse.setResponseMetadata(responseMetadata);

		   
	 		return jsonify(checkIfPhoneNumberIsOptedOutResponse);
	
	   }

	
	/**
     * <p>
     * Verifies an endpoint owner's intent to receive messages by validating
     * the token sent to the endpoint by an earlier <code>Subscribe</code>
     * action. If the token is valid, the action creates a new subscription
     * and returns its Amazon Resource Name (ARN). This call requires an AWS
     * signature only when the <code>AuthenticateOnUnsubscribe</code> flag is
     * set to "true".
     * </p>
     *
     */

	public static String  confirmSNSSubscription(String authenticateOnUnsubscribe, String token,String topicArn) throws AmazonServiceException, AmazonClientException{
	
			ConfirmSubscriptionRequest confirmSubscriptionRequest = new ConfirmSubscriptionRequest();
	        confirmSubscriptionRequest.setTopicArn(topicArn);
	        confirmSubscriptionRequest.setToken(token);
	        ConfirmSubscriptionResult confirmSubscriptionResult=snsClient.confirmSubscription(confirmSubscriptionRequest);
	
			if (debugflag==true) System.out.println("ConfirmSubscriptionRequest - " + confirmSubscriptionRequest);
		    if (debugflag==true) System.out.println("ConfirmSubscriptionResult - " +  confirmSubscriptionResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(confirmSubscriptionRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    ConfirmSubscriptionResponse confirmSubscriptionResponse=new ConfirmSubscriptionResponse();			    
		    confirmSubscriptionResponse.setConfirmSubscriptionResult(confirmSubscriptionResult);
		    confirmSubscriptionResponse.setResponseMetadata(responseMetadata);
	
	        return jsonify(confirmSubscriptionResponse);
	}

	/**
     * <p>
     * Creates a platform application object for one of the supported push
     * notification services, such as APNS and GCM, to which devices and
     * mobile apps may register. You must specify PlatformPrincipal and
     * PlatformCredential attributes when using the
     * <code>CreatePlatformApplication</code> action. The PlatformPrincipal
     * is received from the notification service. For APNS/APNS_SANDBOX,
     * PlatformPrincipal is "SSL certificate". For GCM, PlatformPrincipal is
     * not applicable. For ADM, PlatformPrincipal is "client id". The
     * PlatformCredential is also received from the notification service. For
     * APNS/APNS_SANDBOX, PlatformCredential is "private key". For GCM,
     * PlatformCredential is "API key". For ADM, PlatformCredential is
     * "client secret". The PlatformApplicationArn that is returned when
     * using <code>CreatePlatformApplication</code> is then used as an
     * attribute for the <code>CreatePlatformEndpoint</code> action. For more
     * information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     */
	
	public static String   createSNSPlatformApplication(String attributesJSON, String name, String platform) throws AmazonServiceException, AmazonClientException{
	
		    CreatePlatformApplicationRequest createPlatformApplicationRequest = new CreatePlatformApplicationRequest();
			

			
	        Type token = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);
			
			createPlatformApplicationRequest.setAttributes(attributes);
			createPlatformApplicationRequest.setName(name);
			createPlatformApplicationRequest.setPlatform(platform);
			CreatePlatformApplicationResult createPlatformApplicationResult=snsClient.createPlatformApplication(createPlatformApplicationRequest);
	
			if (debugflag==true) System.out.println("CreatePlatformApplicationRequest - " + createPlatformApplicationRequest);
		    if (debugflag==true) System.out.println("CreatePlatformApplicationResult - " +  createPlatformApplicationResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(createPlatformApplicationRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    CreatePlatformApplicationResponse createPlatformApplicationResponse=new CreatePlatformApplicationResponse();			    
		    createPlatformApplicationResponse.setCreatePlatformApplicationResult(createPlatformApplicationResult);;
		    createPlatformApplicationResponse.setResponseMetadata(responseMetadata);
	
			
			return jsonify(createPlatformApplicationResponse);
	
	   }

	/**
     * <p>
     * Creates an endpoint for a device and mobile app on one of the
     * supported push notification services, such as GCM and APNS.
     * <code>CreatePlatformEndpoint</code> requires the
     * PlatformApplicationArn that is returned from
     * <code>CreatePlatformApplication</code> . The EndpointArn that is
     * returned when using <code>CreatePlatformEndpoint</code> can then be
     * used by the <code>Publish</code> action to send a message to a mobile
     * app or by the <code>Subscribe</code> action for subscription to a
     * topic. The <code>CreatePlatformEndpoint</code> action is idempotent,
     * so if the requester already owns an endpoint with the same device
     * token and attributes, that endpoint's ARN is returned without creating
     * a new endpoint. For more information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     * 
     */

	public static String  createSNSPlatformEndpoint(String attributesJSON, String customUserData,String platformApplicationArn, String platformToken) throws AmazonServiceException, AmazonClientException{
			CreatePlatformEndpointRequest createPlatformEndpointRequest = new CreatePlatformEndpointRequest();
			createPlatformEndpointRequest.setCustomUserData(customUserData);

	        Type token = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);
	

				createPlatformEndpointRequest.setAttributes(attributes);
			
			
			createPlatformEndpointRequest.setPlatformApplicationArn(platformApplicationArn);
			createPlatformEndpointRequest.setToken(platformToken);
			CreatePlatformEndpointResult createPlatformEndpointResult=snsClient.createPlatformEndpoint(createPlatformEndpointRequest);
	
			if (debugflag==true) System.out.println("CreatePlatformEndpointRequest - " + createPlatformEndpointRequest);
		    if (debugflag==true) System.out.println("CreatePlatformEndpointResult - " +  createPlatformEndpointResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(createPlatformEndpointRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    CreatePlatformEndpointResponse createPlatformEndpointResponse=new CreatePlatformEndpointResponse();			    
		    createPlatformEndpointResponse.setCreatePlatformEndpointResult(createPlatformEndpointResult);
		    createPlatformEndpointResponse.setResponseMetadata(responseMetadata);
	
			return jsonify(createPlatformEndpointResponse);
	
	   }

	
	/**
     * <p>
     * Creates a topic to which notifications can be published. . For more information, see
     * <a href="http://aws.amazon.com/sns/"> http://aws.amazon.com/sns </a>
     * . This action is idempotent, so if the requester already owns a topic
     * with the specified name, that topic's ARN is returned without creating
     * a new topic.
     * </p>
     *
     */

	public  static String createSNSTopic(String topicArn)throws AmazonServiceException, AmazonClientException
	{
				//create a new SNS topic
				CreateTopicRequest createTopicRequest = new CreateTopicRequest(topicArn);
				CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
	
				//get request id for CreateTopicRequest from SNS metadata		
				if (debugflag==true) System.out.println("CreateTopicRequest - " + createTopicRequest);
			    if (debugflag==true) System.out.println("CreateTopicResult - " +  createTopicResult);			   		
			    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(createTopicRequest);				
			    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
			    
			    CreateTopicResponse createTopicResponse=new CreateTopicResponse();			    
			    createTopicResponse.setCreateTopicResult(createTopicResult);
			    createTopicResponse.setResponseMetadata(responseMetadata);
			    //String topicArn = createTopicResult.getTopicArn();
				return jsonify(createTopicResponse);
	}
	
	  /**
     * <p>
     * Deletes the endpoint from Amazon SNS. This action is idempotent. For
     * more information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */


	public static String deleteSNSEndpoint(String endpointArn) throws AmazonServiceException, AmazonClientException{
		   
		    DeleteEndpointRequest deleteEndpointRequest=new DeleteEndpointRequest();   
		    deleteEndpointRequest.setEndpointArn(endpointArn);
		    DeleteEndpointResult deleteEndpointResult=snsClient.deleteEndpoint(deleteEndpointRequest);
	
		    if (debugflag==true) System.out.println("DeleteEndpointRequest - " + deleteEndpointRequest);
		    if (debugflag==true) System.out.println("DeleteEndpointResult - " +  deleteEndpointResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(deleteEndpointRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    DeleteEndpointResponse deleteEndpointResponse=new DeleteEndpointResponse();			    
		    
		    deleteEndpointResponse.setResponseMetadata(responseMetadata);
	
	 		return jsonify(deleteEndpointResponse);   
	
	   }
 
	/**
     * <p>
     * Deletes a platform application object for one of the supported push
     * notification services, such as APNS and GCM. For more information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */

	public static String deleteSNSPlatformApplication(String applicationArn)throws AmazonServiceException, AmazonClientException {
	
	    DeletePlatformApplicationRequest deletePlatformApplicationRequest = new DeletePlatformApplicationRequest();
		deletePlatformApplicationRequest.setPlatformApplicationArn(applicationArn);
		DeletePlatformApplicationResult deletePlatformApplicationResult=snsClient.deletePlatformApplication(deletePlatformApplicationRequest);
	
		if (debugflag==true) System.out.println("DeletePlatformApplicationRequest - " + deletePlatformApplicationRequest);
	    if (debugflag==true) System.out.println("DeletePlatformApplicationResult - " +  deletePlatformApplicationResult);			   		
	    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(deletePlatformApplicationRequest);				
	    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
	    
	    DeletePlatformApplicationResponse deletePlatformApplicationResponse=new DeletePlatformApplicationResponse();			    
	
	    deletePlatformApplicationResponse.setResponseMetadata(responseMetadata);
	
		return jsonify(deletePlatformApplicationResponse);
	
	}

    /**
     * <p>
     * Deletes a topic and all its subscriptions. Deleting a topic might
     * prevent some messages previously sent to the topic from being
     * delivered to subscribers. This action is idempotent, so deleting a
     * topic that does not exist does not result in an error.
     * </p>
     *
     *
     */
	
	public  static String deleteSNSTopic(String topicArn)throws AmazonServiceException, AmazonClientException
	{
		
			DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
			DeleteTopicResult deleteTopicResult = snsClient.deleteTopic(deleteTopicRequest);
			
	
			if (debugflag==true) System.out.println("DeleteTopicRequest - " + deleteTopicRequest);
		    if (debugflag==true) System.out.println("DeleteTopicResult - " +  deleteTopicResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(deleteTopicRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    DeleteTopicResponse deleteTopicResponse=new DeleteTopicResponse();			    
	
		    deleteTopicResponse.setResponseMetadata(responseMetadata);		    
			
			return jsonify(deleteTopicResponse);
		
	}

    /**
     * <p>
     * Retrieves the endpoint attributes for a device on one of the
     * supported push notification services, such as GCM and APNS. For more
     * information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */
	
	public static String getSNSEndpointAttributes(String endpointArn) throws AmazonServiceException, AmazonClientException{
	
		   	GetEndpointAttributesRequest getEndpointAttributesRequest = new GetEndpointAttributesRequest();
	        getEndpointAttributesRequest.setEndpointArn(endpointArn);
	        GetEndpointAttributesResult getEndpointAttributesResult=snsClient.getEndpointAttributes(getEndpointAttributesRequest);
	
	            if (debugflag==true) System.out.println("GetEndpointAttributesRequest - " + getEndpointAttributesRequest);
			    if (debugflag==true) System.out.println("GetEndpointAttributesResult - " +  getEndpointAttributesResult);			   		
			    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(getEndpointAttributesRequest);				
			    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
			    
			    GetEndpointAttributesResponse getEndpointAttributesResponse=new GetEndpointAttributesResponse();			    
			    getEndpointAttributesResponse.setGetEndpointAttributesResult(getEndpointAttributesResult);
			    getEndpointAttributesResponse.setResponseMetadata(responseMetadata);
	
			    return jsonify(getEndpointAttributesResponse);
	
	   }

	
	/**
     * <p>
     * Retrieves the attributes of the platform application object for the
     * supported push notification services, such as APNS and GCM. For more
     * information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */

	public static String getSNSPlatformApplicationAttributes(String platformApplicationArn) throws AmazonServiceException, AmazonClientException{
		   
		   GetPlatformApplicationAttributesRequest getPlatformApplicationAttributesRequest = new GetPlatformApplicationAttributesRequest();
		   getPlatformApplicationAttributesRequest.setPlatformApplicationArn(platformApplicationArn);
		   GetPlatformApplicationAttributesResult getPlatformApplicationAttributesResult=snsClient.getPlatformApplicationAttributes(getPlatformApplicationAttributesRequest);
	
	        if (debugflag==true) System.out.println("GetPlatformApplicationAttributesRequest - " + getPlatformApplicationAttributesRequest);
		    if (debugflag==true) System.out.println("GetPlatformApplicationAttributesResult - " +  getPlatformApplicationAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(getPlatformApplicationAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    GetPlatformApplicationAttributesResponse getPlatformApplicationAttributesResponse=new GetPlatformApplicationAttributesResponse();			    
		    getPlatformApplicationAttributesResponse.setGetPlatformApplicationAttributesResult(getPlatformApplicationAttributesResult);
		    getPlatformApplicationAttributesResponse.setResponseMetadata(responseMetadata);
	
		   
		   return jsonify(getPlatformApplicationAttributesResponse);
	
	    
	   }

	/**
     * <p>
     * Retrieves the attributes of the SMS.for the 
     * For moreinformation, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */
	

	public static String getSNSSMSAttributes(String attributesJSON)throws AmazonServiceException, AmazonClientException {
	
		if (debugflag==true) System.out.println("attributesJSON - " + attributesJSON);
		   	GetSMSAttributesRequest getSMSAttributesRequest = new GetSMSAttributesRequest();
		   	
		       Type token = new TypeToken<List<String>>(){}.getType();
		        List<String> attributes = new Gson().fromJson(attributesJSON.substring(attributesJSON.indexOf("["), attributesJSON.indexOf("]")+1), token);

		   	
		   	getSMSAttributesRequest.setAttributes(attributes);
		   	GetSMSAttributesResult getSMSAttributesResult=snsClient.getSMSAttributes(getSMSAttributesRequest);
	
	           if (debugflag==true) System.out.println("GetSMSAttributesRequest - " + getSMSAttributesRequest);
			    if (debugflag==true) System.out.println("GetSMSAttributesResult - " +  getSMSAttributesResult);			   		
			    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(getSMSAttributesRequest);				
			    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
			    
			    GetSMSAttributesResponse getSMSAttributesResponse=new GetSMSAttributesResponse();			    
			    getSMSAttributesResponse.setGetSMSAttributesResult(getSMSAttributesResult);
			    getSMSAttributesResponse.setResponseMetadata(responseMetadata);
	
		   	
		   	return jsonify(getSMSAttributesResponse);
	    
	   }
 
	/**
     * <p>
     * Returns all of the properties of a subscription.
     * </p>
     * 
     */

	public static String getSNSSubscriptionAttributes(String subscriptionArn)throws AmazonServiceException, AmazonClientException {
	
		    GetSubscriptionAttributesRequest getSubscriptionAttributesRequest = new GetSubscriptionAttributesRequest();
	        getSubscriptionAttributesRequest.setSubscriptionArn(subscriptionArn);
	        GetSubscriptionAttributesResult getSubscriptionAttributesResult= snsClient.getSubscriptionAttributes(getSubscriptionAttributesRequest);
	
	        if (debugflag==true) System.out.println("GetSubscriptionAttributesRequest - " + getSubscriptionAttributesRequest);
		    if (debugflag==true) System.out.println("GetSubscriptionAttributesResult - " +  getSubscriptionAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(getSubscriptionAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    GetSubscriptionAttributesResponse getSubscriptionAttributesResponse=new GetSubscriptionAttributesResponse();			    
		    getSubscriptionAttributesResponse.setGetSubscriptionAttributesResult(getSubscriptionAttributesResult);
		    getSubscriptionAttributesResponse.setResponseMetadata(responseMetadata);
	
		    return jsonify(getSubscriptionAttributesResponse);
	
	   }

    /**
     * <p>
     * Returns all of the properties of a topic. Topic properties returned
     * might differ based on the authorization of the user.
     * </p>
     *
     */
	
	public static String getSNSTopicAttributes(String topicArn)throws AmazonServiceException, AmazonClientException {
	       
		    GetTopicAttributesRequest getTopicAttributesRequest = new GetTopicAttributesRequest();
	        getTopicAttributesRequest.setTopicArn(topicArn);
	        GetTopicAttributesResult getTopicAttributesResult=snsClient.getTopicAttributes(getTopicAttributesRequest);
	
	        if (debugflag==true) System.out.println("GetTopicAttributesRequest - " + getTopicAttributesRequest);
		    if (debugflag==true) System.out.println("GetTopicAttributesResult - " +  getTopicAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(getTopicAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    GetTopicAttributesResponse getTopicAttributesResponse=new GetTopicAttributesResponse();			    
		    getTopicAttributesResponse.setGetTopicAttributesResult(getTopicAttributesResult);
		    getTopicAttributesResponse.setResponseMetadata(responseMetadata);
	
		    return jsonify(getTopicAttributesResponse);
	
	    
	   }

    /**
     * <p>
     * Lists the endpoints and endpoint attributes for devices in a
     * supported push notification service, such as GCM and APNS. The results
     * for <code>ListEndpointsByPlatformApplication</code> are paginated and
     * return a limited list of endpoints, up to 100. If additional records
     * are available after the first page results, then a NextToken string
     * will be returned. To receive the next page, you call
     * <code>ListEndpointsByPlatformApplication</code> again using the
     * NextToken string received from the previous call. When there are no
     * more records to return, NextToken will be null. For more information,
     * see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */
	
	public static String  listSNSEndpointsByPlatformApplication(String platformApplicationArn)throws AmazonServiceException, AmazonClientException {
	
			ListEndpointsByPlatformApplicationRequest listEndpointsByPlatformApplicationRequest = new ListEndpointsByPlatformApplicationRequest();
			listEndpointsByPlatformApplicationRequest.setPlatformApplicationArn(platformApplicationArn);;
			ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplicationResult= snsClient.listEndpointsByPlatformApplication(listEndpointsByPlatformApplicationRequest);
	
			if (debugflag==true) System.out.println("ListEndpointsByPlatformApplicationRequest - " + listEndpointsByPlatformApplicationRequest);
		    if (debugflag==true) System.out.println("ListEndpointsByPlatformApplicationResult - " +  listEndpointsByPlatformApplicationResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listEndpointsByPlatformApplicationRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    ListEndpointsByPlatformApplicationResponse listEndpointsByPlatformApplicationResponse=new ListEndpointsByPlatformApplicationResponse();			    
		    listEndpointsByPlatformApplicationResponse.setListEndpointsByPlatformApplicationResult(listEndpointsByPlatformApplicationResult);
		    listEndpointsByPlatformApplicationResponse.setResponseMetadata(responseMetadata);
	
			return jsonify(listEndpointsByPlatformApplicationResponse);
	
	}

    /**
     * <p>
     * Lists the endpoints and endpoint attributes for devices in a
     * supported push notification service, such as GCM and APNS. 
     * For more information See,
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */
	public static String  listSNSPhoneNumbersOptedOut() throws AmazonServiceException, AmazonClientException {
			
				ListPhoneNumbersOptedOutRequest listPhoneNumbersOptedOutRequest = new ListPhoneNumbersOptedOutRequest();
				ListPhoneNumbersOptedOutResult listPhoneNumbersOptedOutResult= snsClient.listPhoneNumbersOptedOut(listPhoneNumbersOptedOutRequest);
	
				if (debugflag==true) System.out.println("ListPhoneNumbersOptedOutRequest - " + listPhoneNumbersOptedOutRequest);
			    if (debugflag==true) System.out.println("ListPhoneNumbersOptedOutResult - " +  listPhoneNumbersOptedOutResult);			   		
			    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listPhoneNumbersOptedOutRequest);				
			    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
			    
			    ListPhoneNumbersOptedOutResponse listPhoneNumbersOptedOutResponse=new ListPhoneNumbersOptedOutResponse();			    
			    listPhoneNumbersOptedOutResponse.setListPhoneNumbersOptedOutResult(listPhoneNumbersOptedOutResult);
			    listPhoneNumbersOptedOutResponse.setResponseMetadata(responseMetadata);
	
			    return jsonify(listPhoneNumbersOptedOutResponse);
	}

    /**
     * <p>
     * Lists the platform application objects for the supported push
     * notification services, such as APNS and GCM. The results for
     * <code>ListPlatformApplications</code> are paginated and return a
     * limited list of applications, up to 100. If additional records are
     * available after the first page results, then a NextToken string will
     * be returned. To receive the next page, you call
     * <code>ListPlatformApplications</code> using the NextToken string
     * received from the previous call. When there are no more records to
     * return, NextToken will be null. For more information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     * 
     */
	
	public static String  listSNSPlatformApplications() throws AmazonServiceException, AmazonClientException {
	
			ListPlatformApplicationsRequest listPlatformApplicationsRequest=new ListPlatformApplicationsRequest();
			ListPlatformApplicationsResult listPlatformApplicationsResult=snsClient.listPlatformApplications(listPlatformApplicationsRequest);
	
			if (debugflag==true) System.out.println("ListPlatformApplicationsRequest - " + listPlatformApplicationsRequest);
		    if (debugflag==true) System.out.println("ListPlatformApplicationsResult - " +  listPlatformApplicationsResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listPlatformApplicationsRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    ListPlatformApplicationsResponse listPlatformApplicationsResponse=new ListPlatformApplicationsResponse();			    
		    listPlatformApplicationsResponse.setListPlatformApplicationsResult(listPlatformApplicationsResult);
		    listPlatformApplicationsResponse.setResponseMetadata(responseMetadata);
	
			
			return jsonify(listPlatformApplicationsResponse);
			
	}

    /**
     * <p>
     * Returns a list of the requester's subscriptions. Each call returns a
     * limited list of subscriptions, up to 100. If there are more
     * subscriptions, a <code>NextToken</code> is also returned. Use the
     * <code>NextToken</code> parameter in a new
     * <code>ListSubscriptions</code> call to get further results.
     * </p>
     * 
     * 
     */
	
	public static String  listSNSSubscriptions() throws AmazonServiceException, AmazonClientException {
		ListSubscriptionsRequest listSubscriptionsRequest =new ListSubscriptionsRequest();
			ListSubscriptionsResult listSubscriptionsResult=snsClient.listSubscriptions();
	
			if (debugflag==true) System.out.println("ListSubscriptionsRequest - " + listSubscriptionsRequest);
		    if (debugflag==true) System.out.println("ListSubscriptionsResult - " +  listSubscriptionsResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listSubscriptionsRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    ListSubscriptionsResponse listSubscriptionsResponse=new ListSubscriptionsResponse();			    
		    listSubscriptionsResponse.setListSubscriptionsResult(listSubscriptionsResult);
		    listSubscriptionsResponse.setResponseMetadata(responseMetadata);
	
		    return jsonify(listSubscriptionsResponse);
	}

	   /**
     * <p>
     * Returns a list of the requester's subscriptions for a particular topic. Each call returns a
     * limited list of subscriptions, up to 100. If there are more
     * subscriptions, a <code>NextToken</code> is also returned. Use the
     * <code>NextToken</code> parameter in a new
     * <code>ListSubscriptions</code> call to get further results.
     * </p>
     * 
     */
	
	public static String  listSNSSubscriptionsByTopic(String topicArn) throws AmazonServiceException, AmazonClientException{
		
		if(topicArn==null){
			return listSNSSubscriptions();
		}
		else
		{
	        
				ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();
				listSubscriptionsByTopicRequest.setTopicArn(topicArn);
				ListSubscriptionsByTopicResult listSubscriptionsByTopicResult= snsClient.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
			
				if (debugflag==true) System.out.println("ListSubscriptionsByTopicRequest - " + listSubscriptionsByTopicRequest);
			    if (debugflag==true) System.out.println("ListSubscriptionsByTopicResult - " +  listSubscriptionsByTopicResult);			   		
			    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listSubscriptionsByTopicRequest);				
			    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
			    
			    ListSubscriptionsByTopicResponse listSubscriptionsByTopicResponse=new ListSubscriptionsByTopicResponse();			    
			    listSubscriptionsByTopicResponse.setListSubscriptionsByTopicResult(listSubscriptionsByTopicResult);
			    listSubscriptionsByTopicResponse.setResponseMetadata(responseMetadata);
			
				return jsonify(listSubscriptionsByTopicResponse);
		}
	}

    /**
     * <p>
     * Returns a list of the requester's topics. Each call returns a limited
     * list of topics, up to 100. If there are more topics, a
     * <code>NextToken</code> is also returned. Use the
     * <code>NextToken</code> parameter in a new <code>ListTopics</code> call
     * to get further results.
     * </p>
     * 
     */
	
	public static String  listSNSTopics() throws AmazonServiceException, AmazonClientException {
		
		ListTopicsRequest 	listTopicsRequest=new ListTopicsRequest();
		ListTopicsResult listTopicsResult=snsClient.listTopics(listTopicsRequest);
	
			if (debugflag==true) System.out.println("ListTopicsRequest - " + listTopicsRequest);
		    if (debugflag==true) System.out.println("ListTopicsResult - " +  listTopicsResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(listTopicsRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    ListTopicsResponse listTopicsResponse=new ListTopicsResponse();			    
		    listTopicsResponse.setListTopicsResult(listTopicsResult);
		    listTopicsResponse.setResponseMetadata(responseMetadata);
	
			return jsonify(listTopicsResponse);
	}
    /**
     * <p>
     * Used by requestor to opt in for a phone number to receive notifications
     * 
     * </p>
     */
	

	public static String  optInSNSPhoneNumber(String phoneNumber) throws AmazonServiceException, AmazonClientException{
		 
	   OptInPhoneNumberRequest  optInPhoneNumberRequest = new OptInPhoneNumberRequest();
	   optInPhoneNumberRequest.setPhoneNumber(phoneNumber);
	   OptInPhoneNumberResult optInPhoneNumberResult=snsClient.optInPhoneNumber(optInPhoneNumberRequest);
	
	   if (debugflag==true) System.out.println("OptInPhoneNumberRequest - " + optInPhoneNumberRequest);
	    if (debugflag==true) System.out.println("OptInPhoneNumberResult - " +  optInPhoneNumberResult);			   		
	    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(optInPhoneNumberRequest);				
	    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
	    
	    OptInPhoneNumberResponse optInPhoneNumberResponse=new OptInPhoneNumberResponse();			    
	   // optInPhoneNumberResponse.setOptInPhoneNumberResult(optInPhoneNumberResult);
	    optInPhoneNumberResponse.setResponseMetadata(responseMetadata);
	
	    return jsonify(optInPhoneNumberResponse);
	
	   }

    /**
     * <p>
     * Sends a message to all of a topic's subscribed endpoints. When a
     * <code>messageId</code> is returned, the message has been saved and
     * Amazon SNS will attempt to deliver it to the topic's subscribers
     * shortly. The format of the outgoing message to each subscribed
     * endpoint depends on the notification protocol selected.
     * </p>
     * <p>
     * To use the <code>Publish</code> action for sending a message to a
     * mobile endpoint, such as an app on a Kindle device or mobile phone,
     * you must specify the EndpointArn. The EndpointArn is returned when
     * making a call with the <code>CreatePlatformEndpoint</code> action. 
     * </p>
     * 
     * 
     */
	
	
	public  static String publishSNSTopic(String message,String messageAttributes,String messageStructure,String phoneNumber, String subject, String targetArn)throws AmazonServiceException, AmazonClientException
	
	{
			//publish to an SNS topic
			//String msg = "My text published to SNS topic with email endpoint";
			PublishRequest publishRequest = new PublishRequest(targetArn, message,subject);
			PublishResult publishResult = snsClient.publish(publishRequest);
	
			if (debugflag==true) System.out.println("PublishRequest - " + publishRequest);
		    if (debugflag==true) System.out.println("PublishResult - " +  publishResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(publishRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    PublishResponse publishResponse=new PublishResponse();			    
		    publishResponse.setPublishResult(publishResult);
		    publishResponse.setResponseMetadata(responseMetadata);
		    
			return jsonify(publishResponse);
	}

    /**
     * <p>
     * Removes a statement from a topic's access control policy.
     * </p>
     *
     */
	
	public static String  removeSNSPermission(String label,String topicArn )throws AmazonServiceException, AmazonClientException {
	
			RemovePermissionRequest removePermissionRequest = new RemovePermissionRequest();
			removePermissionRequest.setTopicArn(topicArn);
			removePermissionRequest.setLabel(label);
			RemovePermissionResult removePermissionResult=snsClient.removePermission(removePermissionRequest);
	
			if (debugflag==true) System.out.println("RemovePermissionRequest - " + removePermissionRequest);
		    if (debugflag==true) System.out.println("RemovePermissionResult - " +  removePermissionResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(removePermissionRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    RemovePermissionResponse removePermissionResponse=new RemovePermissionResponse();			    
		    //removePermissionResponse.setRemovePermissionResult(removePermissionResult);
		    removePermissionResponse.setResponseMetadata(responseMetadata);
			
		    return jsonify(removePermissionResponse);
	
	}
	
    /**
     * <p>
     * Sets the attributes for an endpoint for a device on one of the
     * supported push notification services, such as GCM and APNS. For more
     * information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     */

	public static String  setSNSEndpointAttributes(String attributesJSON,String endpointArn) throws AmazonServiceException, AmazonClientException {
		    
		   
		    SetEndpointAttributesRequest setEndpointAttributesRequest = new SetEndpointAttributesRequest();
		    
	        Type token = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);

		    setEndpointAttributesRequest.setAttributes(attributes);
		    setEndpointAttributesRequest.setEndpointArn(endpointArn);
	        SetEndpointAttributesResult setEndpointAttributesResult=snsClient.setEndpointAttributes(setEndpointAttributesRequest);

	        if (debugflag==true) System.out.println("SetEndpointAttributesRequest - " + setEndpointAttributesRequest);
		    if (debugflag==true) System.out.println("SetEndpointAttributesResult - " +  setEndpointAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(setEndpointAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SetEndpointAttributesResponse setEndpointAttributesResponse=new SetEndpointAttributesResponse();			    
		    //setEndpointAttributesResponse.setSetEndpointAttributesResult(setEndpointAttributesResult);
		    setEndpointAttributesResponse.setResponseMetadata(responseMetadata);

	
	        return jsonify(setEndpointAttributesResponse);

	   }
 
	/**
     * <p>
     * Sets the attributes of the platform application object for the
     * supported push notification services, such as APNS and GCM. For more
     * information, see
     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
     * .
     * </p>
     *
     */
	   
	   public static String  setSNSPlatformApplicationAttributes(String attributesJSON,String platformApplicationArn) throws AmazonServiceException, AmazonClientException{
		    
		    SetPlatformApplicationAttributesRequest setPlatformApplicationAttributesRequest = new SetPlatformApplicationAttributesRequest();		   
		    
	        Type token = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);

		    
		    setPlatformApplicationAttributesRequest.setAttributes(attributes);	
		    setPlatformApplicationAttributesRequest.setPlatformApplicationArn(platformApplicationArn);
		    SetPlatformApplicationAttributesResult setPlatformApplicationAttributesResult=snsClient.setPlatformApplicationAttributes(setPlatformApplicationAttributesRequest);

		    if (debugflag==true) System.out.println("SetPlatformApplicationAttributesRequest - " + setPlatformApplicationAttributesRequest);
		    if (debugflag==true) System.out.println("SetPlatformApplicationAttributesResult - " +  setPlatformApplicationAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(setPlatformApplicationAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SetPlatformApplicationAttributesResponse setPlatformApplicationAttributesResponse=new SetPlatformApplicationAttributesResponse();			    
		   // setPlatformApplicationAttributesResponse.setSetPlatformApplicationAttributesResult(setPlatformApplicationAttributesResult);
		    setPlatformApplicationAttributesResponse.setResponseMetadata(responseMetadata);

		    return jsonify(setPlatformApplicationAttributesResponse);

	   }
	
	    /**
	     * <p>
	     * Sets the attributes of the SMS  object . For more
	     * information, see
	     * <a href="http://docs.aws.amazon.com/sns/latest/dg/SNSMobilePush.html"> Using Amazon SNS Mobile Push Notifications </a>
	     * .
	     * </p>
	     *
	     */
	   
	   public static String setSNSSMSAttributes(String attributesJSON) throws AmazonServiceException, AmazonClientException{
	
		   if (debugflag==true) System.out.println("attributesJSON - " + attributesJSON);
		   SetSMSAttributesRequest setSMSAttributesRequest = new SetSMSAttributesRequest();
		  
	        Type token = new TypeToken<Map<String, String>>(){}.getType();
	        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);

		   setSMSAttributesRequest.setAttributes(attributes);
		   SetSMSAttributesResult setSMSAttributesResult=snsClient.setSMSAttributes(setSMSAttributesRequest);
	
	       if (debugflag==true) System.out.println("SetSMSAttributesRequest - " + setSMSAttributesRequest);
		    if (debugflag==true) System.out.println("SetSMSAttributesResult - " +  setSMSAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(setSMSAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SetSMSAttributesResponse setSMSAttributesResponse=new SetSMSAttributesResponse();			    
		    setSMSAttributesResponse.setResponseMetadata(responseMetadata);
		  //  setSMSAttributesResponse.setResponseMetadata(responseMetadata);
	
		   return jsonify(setSMSAttributesResponse);
	    
	   }
	   
	   
	    /**
	     * <p>
	     * Allows a subscription owner to set an attribute of the topic to a new
	     * value.
	     * </p>
	     * 
	     * 
	     */

	public static String  setSNSSubscriptionAttributes( String attributeName, String attributeValue, String subscriptionArn) throws AmazonServiceException, AmazonClientException{

		
		

		    SetSubscriptionAttributesRequest setSubscriptionAttributesRequest = new SetSubscriptionAttributesRequest();
	        setSubscriptionAttributesRequest.setSubscriptionArn(subscriptionArn);
	        setSubscriptionAttributesRequest.setAttributeName(attributeName);
	        setSubscriptionAttributesRequest.setAttributeValue(attributeValue);
	        SetSubscriptionAttributesResult setSubscriptionAttributesResult=snsClient.setSubscriptionAttributes(setSubscriptionAttributesRequest);

	        if (debugflag==true) System.out.println("SetSubscriptionAttributesRequest - " + setSubscriptionAttributesRequest);
		    if (debugflag==true) System.out.println("SetSubscriptionAttributesResult - " +  setSubscriptionAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(setSubscriptionAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SetSubscriptionAttributesResponse setSubscriptionAttributesResponse=new SetSubscriptionAttributesResponse();			    
		   // setSubscriptionAttributesResponse.setSetSubscriptionAttributesResult(setSubscriptionAttributesResult);
		    setSubscriptionAttributesResponse.setResponseMetadata(responseMetadata);

	        
	        return jsonify(setSubscriptionAttributesResponse);
 
	   }
 
	/**
     * <p>
     * Allows a topic owner to set an attribute of the topic to a new value.
     * </p>
     * 
     * 
     */
	   
	   public static String  setSNSTopicAttributes(String attributeName, String attributeValue, String topicArn) throws AmazonServiceException, AmazonClientException{
	      
		    SetTopicAttributesRequest setTopicAttributesRequest = new SetTopicAttributesRequest();
	        setTopicAttributesRequest.setTopicArn(topicArn);
	        setTopicAttributesRequest.setAttributeName(attributeName);
	        setTopicAttributesRequest.setAttributeValue(attributeValue);
	        SetTopicAttributesResult setTopicAttributesResult=snsClient.setTopicAttributes(setTopicAttributesRequest);
	
	        if (debugflag==true) System.out.println("SetTopicAttributesRequest - " + setTopicAttributesRequest);
		    if (debugflag==true) System.out.println("SetTopicAttributesResult - " +  setTopicAttributesResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(setTopicAttributesRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SetTopicAttributesResponse setTopicAttributesResponse=new SetTopicAttributesResponse();			    
		    //setTopicAttributesResponse.setSetTopicAttributesResult(setTopicAttributesResult);
		    
		    setTopicAttributesResponse.setResponseMetadata(responseMetadata);
	
		    return jsonify(setTopicAttributesResponse);
	
	   }
	   
	    /**
	     * <p>
	     * Prepares to subscribe an endpoint by sending the endpoint a
	     * confirmation message. To actually create a subscription, the endpoint
	     * owner must call the <code>ConfirmSubscription</code> action with the
	     * token from the confirmation message. Confirmation tokens are valid for
	     * three days.
	     * </p>
	     * 
	     */

	public  static String subscribeSNSTopic(String endpoint, String protocol,String topicArn)throws AmazonServiceException, AmazonClientException
	{
			//String topicArn = createTopicResult.getTopicArn();
			//subscribe to an SNS topic
			SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, protocol, endpoint);
			SubscribeResult subscribeResult=snsClient.subscribe(subscribeRequest);
	
			if (debugflag==true) System.out.println("SubscribeRequest - " + subscribeRequest);
		    if (debugflag==true) System.out.println("SubscribeResult - " +  subscribeResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(subscribeRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    SubscribeResponse subscribeResponse=new SubscribeResponse();			    
		    subscribeResponse.setSubscribeResult(subscribeResult);
		    subscribeResponse.setResponseMetadata(responseMetadata);
		    
			
			return jsonify(subscribeResponse);
	}
	
	 /**
     * <p>
     * Deletes a subscription. If the subscription requires authentication
     * for deletion, only the owner of the subscription or the topic's owner
     * can unsubscribe, and an AWS signature is required. If the
     * <code>Unsubscribe</code> call does not require authentication and the
     * requester is not the subscription owner, a final cancellation message
     * is delivered to the endpoint, so that the endpoint owner can easily
     * resubscribe to the topic if the <code>Unsubscribe</code> request was
     * unintended.
     * </p>
     * 
     */

	public  static String unsubscribeSNSTopic(String subscriptionArn) throws AmazonServiceException, AmazonClientException {
	
			UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest();
	        unsubscribeRequest.setSubscriptionArn(subscriptionArn);
	        UnsubscribeResult unsubscribeResult=snsClient.unsubscribe(unsubscribeRequest);
	
			if (debugflag==true) System.out.println("UnsubscribeRequest - " + unsubscribeRequest);
		    if (debugflag==true) System.out.println("UnsubscribeResult - " +  unsubscribeResult);			   		
		    ResponseMetadata responseMetadata=snsClient.getCachedResponseMetadata(unsubscribeRequest);				
		    if (debugflag==true) System.out.println("ResponseMetadata - " +  responseMetadata);				   
		    
		    UnsubscribeResponse unsubscribeResponse=new UnsubscribeResponse();			    
		    //unsubscribeResponse.setUnsubscribeResult(unsubscribeResult);
		    unsubscribeResponse.setResponseMetadata(responseMetadata);
		    
	
	        return jsonify(unsubscribeResponse);
	}


	public static void main(String[] args) {
		
		/*
		BasicAWSCredentials credentials = null;
		
		//credentials = new BasicAWSCredentials("AKIAIQY62P5VNBZEGL4Q", "W+zjDGh+DwjJzOU6z4QtB5kqI+TV1DJPZi7idYbJ");
		credentials = new BasicAWSCredentials("AKIAI6XI6NCYZGB3TQWA", "asudh+5sfD7t8eJX47qKdKrs0WnP0wjdFWRdRTjQ");
		//credentials = new BasicAWSCredentials("AKIAICY3DWHP5BL3E6GQ", "yJX5Elv/iCvK1rwkvfo5UK615LaO7S+mnxy+ehsX");
		
		AmazonSNSClient snsClient = new AmazonSNSClient(credentials);		                           
		snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

		//create a new SNS topic
		CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyHackTopic1");
		CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
		//print TopicArn
		if (debugflag==true) System.out.println(createTopicResult);
		//get request id for CreateTopicRequest from SNS metadata		
		if (debugflag==true) System.out.println("CreateTopicRequest - " + snsClient.getCachedResponseMetadata(createTopicRequest));
	
	
		String topicArn = createTopicResult.getTopicArn();
			//subscribe to an SNS topic
		SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", "pvaidya@tibco.com");
		snsClient.subscribe(subRequest);
		//get request id for SubscribeRequest from SNS metadata
		if (debugflag==true) System.out.println("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
		if (debugflag==true) System.out.println("Check your email and confirm subscription.");
	
	try {
		Thread.sleep(30000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		//publish to an SNS topic
		String msg = "My text published to SNS topic with email endpoint";
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResult = snsClient.publish(publishRequest);
		//print MessageId of message published to SNS topic
		if (debugflag==true) System.out.println("MessageId - " + publishResult.getMessageId());
		
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		*/
		
		//SNSImpl ssnimp=new SNSImpl("AKIAI6XI6NCYZGB3TQWA", "asudh+5sfD7t8eJX47qKdKrs0WnP0wjdFWRdRTjQ");
		//String topicArn = ssnimp.createSNSTopic("Hack101");
		//String deletedtopicArn = deleteSNSTopic(topicArn);
		//String subscriptionArn=ssnimp.subscribeSNSTopic("pvaidya@tibco.com", "email", topicArn);
				
	
		
		
	//	String confirmsubscriptionArn=ssnimp.confirmSNSSubscription(topicArn, "2336412f37fb687f5d51e6e241d44a2cbcd89f39d220bcf939c0276cbc20a98ece4382976865a24019faf5f10e002c3f92ab90c36e9bbffc876932b54452c8a21d3f7de53fb1cac1efc87aecac249346b8d11cce4563e6c09178a23cc8f96ca25a8853ca41e4b06e14c0eac9d58e6841");
    //ssnimp.unsubscribeSNSTopic("arn:aws:sns:us-east-1:610816779484:Hack101:6a2580c8-f585-4c0d-a004-c024d3eac4ef");
	//	ssnimp.publishSNSTopic("arn:aws:sns:us-east-1:610816779484:Hack101", "hello", "say hello");
		
		
		//if (debugflag==true) System.out.println(ssnimp.listSNSTopics());
		//if (debugflag==true) System.out.println(ssnimp.listSNSSubscriptions());
		//if (debugflag==true) System.out.println(ssnimp.listSNSSubscriptionsByTopic("arn:aws:sns:us-east-1:610816779484:Hack100"));
		
		//if (debugflag==true) System.out.println(SNSImpl.createSNSTopic("hacking"));
	//	if (debugflag==true) System.out.println(SNSImpl.deleteSNSTopic("arn:aws:sns:us-east-1:610816779484:test"));
		
		String attributesJSON="{\"member\":[\"380615363300\",\"610816779484\"]}";
		//String attributesJSON="[\"380615363300\",\"610816779484\"]";
		String attributesJSON1=attributesJSON.substring(attributesJSON.indexOf("["), attributesJSON.indexOf("]")+1);
		if (debugflag==true) System.out.println(attributesJSON1);
		 Type token = new TypeToken<List<String>>(){}.getType();
		    if (debugflag==true) System.out.println(token);
		    List<String> attributes = new Gson().fromJson(attributesJSON1, token);
	        if (debugflag==true) System.out.println(attributes);
	//
			
	}

}
