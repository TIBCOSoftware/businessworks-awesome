package com.tibco.aws.sqs.Impl;

import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesRequest;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
//import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.RemovePermissionRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SQSImpl implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AmazonSQS sqs = null;
	private static final Log logger = LogFactory.getLog(SQSImpl.class);
	
	public SQSImpl(String accessKey, String secretKey, String endPoint)
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Instantiating AmazonSQSClient with AccessKey: " + accessKey + " SecretKey: " + secretKey + " SQSEndPoint: " + endPoint);
		}
		BasicAWSCredentials credentials = null;
		credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSQS sqsClient = new AmazonSQSClient(credentials);
        sqsClient.setEndpoint(endPoint);
        sqs = sqsClient;
	}
	
	public static String addSQSPermission(String queueName, String label, String accountIdsJSON, String actionNamesJSON) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Adding Permissions for given AWS Accounts to Amazon SQS Queue: " + queueName);
		}
        String retVal = null;        
        Type accountIdsToken = new TypeToken<List<String>>(){}.getType();
        List<String> aWSAccountIds = new Gson().fromJson(accountIdsJSON, accountIdsToken);        
        Type actionNamesToken = new TypeToken<List<String>>(){}.getType();
        List<String> actions = new Gson().fromJson(actionNamesJSON, actionNamesToken);
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        AddPermissionRequest addPermissionRequest = new AddPermissionRequest().withQueueUrl(queueUrl).withLabel(label).withAWSAccountIds(aWSAccountIds).withActions(actions);        
        if (logger.isDebugEnabled()) {
			logger.debug("AddPermissionRequest: " + addPermissionRequest.toString());
		}
        SQSImpl.getSqs().addPermission(addPermissionRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(addPermissionRequest).getRequestId();        
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}
	
	public static String changeSQSMsgVisibility(String queueName, String receiptHandle, int visibilityTimeout) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Changing Visibility of Amazon SQS Queue: " + queueName);
		}        
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        ChangeMessageVisibilityRequest changeMessageVisibilityRequest = new ChangeMessageVisibilityRequest().withQueueUrl(queueUrl).withReceiptHandle(receiptHandle).withVisibilityTimeout(visibilityTimeout);        
        if (logger.isDebugEnabled()) {
			logger.debug("ChangeMessageVisibilityRequest: " + changeMessageVisibilityRequest.toString());
		}        
        SQSImpl.getSqs().changeMessageVisibility(changeMessageVisibilityRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(changeMessageVisibilityRequest).getRequestId();        
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}        
        return retVal;
	}
	
	public static String createSQSQueue(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Creating Amazon SQS Queue: " + queueName);
		}               
        String retVal = null;        
        CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(queueName);       
        if (logger.isDebugEnabled()) {
			logger.debug("CreateQueueRequest: " + createQueueRequest.toString());
		}        
        CreateQueueResult createQueueResult = SQSImpl.getSqs().createQueue(createQueueRequest);
        CreateQueueResponse createQueueResponse = new CreateQueueResponse();
        createQueueResponse.setCreateQueueResult(createQueueResult);
        createQueueResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(createQueueRequest));        
        try {
			retVal = new ObjectMapper().writeValueAsString(createQueueResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}        
        if (logger.isDebugEnabled()) {
			logger.debug("CreateQueueResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String createSQSQueue(String queueName, String attributesJSON) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Creating Amazon SQS Queue: " + queueName + " with Attributes: " + attributesJSON);
		}        
        String retVal = null;        
        Type token = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> attributes = new Gson().fromJson(attributesJSON, token);        
        CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(queueName).withAttributes(attributes);        
        if (logger.isDebugEnabled()) {
			logger.debug("CreateQueueRequest: " + createQueueRequest.toString());
		}                    
        CreateQueueResult createQueueResult = SQSImpl.getSqs().createQueue(createQueueRequest);
        CreateQueueResponse createQueueResponse = new CreateQueueResponse();
        createQueueResponse.setCreateQueueResult(createQueueResult);
        createQueueResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(createQueueRequest));        
        try {
			retVal = new ObjectMapper().writeValueAsString(createQueueResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}        
        if (logger.isDebugEnabled()) {
			logger.debug("CreateQueueResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String deleteSQSMsg(String queueName, String receiptHandle) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting Message from Amazon SQS Queue: " + queueName);
		}        
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest().withQueueUrl(queueUrl).withReceiptHandle(receiptHandle);
        if (logger.isDebugEnabled()) {
			logger.debug("DeleteMessageRequest: " + deleteMessageRequest.toString());
		}
        SQSImpl.getSqs().deleteMessage(deleteMessageRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(deleteMessageRequest).getRequestId();
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}
	
	public static String deleteSQSQueue(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting Amazon SQS Queue: " + queueName);
		}        
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        DeleteQueueRequest deleteQueueRequest = new DeleteQueueRequest().withQueueUrl(queueUrl);
        if (logger.isDebugEnabled()) {
			logger.debug("DeleteQueueRequest: " + deleteQueueRequest.toString());
		}
        SQSImpl.getSqs().deleteQueue(deleteQueueRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(deleteQueueRequest).getRequestId();
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}
	
	public static String getSQSQueueAttributes(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Getting All Attributes from Amazon SQS Queue: " + queueName);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest().withAttributeNames("All").withQueueUrl(queueUrl);
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueAttributesRequest: " + getQueueAttributesRequest.toString());
		}
        GetQueueAttributesResult getQueueAttributesResult = SQSImpl.getSqs().getQueueAttributes(getQueueAttributesRequest);
        GetQueueAttributesResponse getQueueAttributesResponse = new GetQueueAttributesResponse();
        getQueueAttributesResponse.setGetQueueAttributesResult(getQueueAttributesResult);
        getQueueAttributesResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(getQueueAttributesRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(getQueueAttributesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueAttributesResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String getSQSQueueAttributes(String queueName, String attributeNamesJSON) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Getting Attributes: " + attributeNamesJSON + " from Amazon SQS Queue: " + queueName);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        Type token = new TypeToken<List<String>>(){}.getType();
        List<String> attributeNames = new Gson().fromJson(attributeNamesJSON, token);
        
        GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest().withAttributeNames(attributeNames).withQueueUrl(queueUrl);
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueAttributesRequest: " + getQueueAttributesRequest.toString());
		}
        GetQueueAttributesResult getQueueAttributesResult = SQSImpl.getSqs().getQueueAttributes(getQueueAttributesRequest);
        GetQueueAttributesResponse getQueueAttributesResponse = new GetQueueAttributesResponse();
        getQueueAttributesResponse.setGetQueueAttributesResult(getQueueAttributesResult);
        getQueueAttributesResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(getQueueAttributesRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(getQueueAttributesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueAttributesResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String getSQSQueueUrl(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Getting URL of Amazon SQS Queue: " + queueName);
		}
        String retVal = null;
        GetQueueUrlRequest getQueueUrlRequest = new GetQueueUrlRequest().withQueueName(queueName);
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueUrlRequest: " + getQueueUrlRequest.toString());
		}
        GetQueueUrlResult getQueueUrlResult = SQSImpl.getSqs().getQueueUrl(getQueueUrlRequest);
        GetQueueUrlResponse getQueueUrlResponse = new GetQueueUrlResponse();
        getQueueUrlResponse.setGetQueueUrlResult(getQueueUrlResult);
        getQueueUrlResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(getQueueUrlRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(getQueueUrlResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueUrlResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String getSQSQueueUrl(String queueName, String queueOwnerAWSAccountId) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Getting URL of Amazon SQS Queue: " + queueName + " with QueueOwnerAccountId: " + queueOwnerAWSAccountId);
		}
        String retVal = null;
        GetQueueUrlRequest getQueueUrlRequest = new GetQueueUrlRequest().withQueueName(queueName).withQueueOwnerAWSAccountId(queueOwnerAWSAccountId);
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueUrlRequest: " + getQueueUrlRequest.toString());
		}
        GetQueueUrlResult getQueueUrlResult = SQSImpl.getSqs().getQueueUrl(getQueueUrlRequest);
        GetQueueUrlResponse getQueueUrlResponse = new GetQueueUrlResponse();
        getQueueUrlResponse.setGetQueueUrlResult(getQueueUrlResult);
        getQueueUrlResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(getQueueUrlRequest));
        
        try {
			retVal = new ObjectMapper().writeValueAsString(getQueueUrlResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("GetQueueUrlResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String listSQSDeadLetterSourceQueues(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Listing All Amazon SQS Queues with Dead Letter Queue: " + queueName);
		}
		String retVal = null;
		String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        ListDeadLetterSourceQueuesRequest listDeadLetterSourceQueuesRequest = new ListDeadLetterSourceQueuesRequest().withQueueUrl(queueUrl);
        if (logger.isDebugEnabled()) {
			logger.debug("ListDeadLetterSourceQueuesRequest: " + listDeadLetterSourceQueuesRequest.toString());
		}
        ListDeadLetterSourceQueuesResult listDeadLetterSourceQueuesResult = SQSImpl.getSqs().listDeadLetterSourceQueues(listDeadLetterSourceQueuesRequest);
        ListDeadLetterSourceQueuesResponse listDeadLetterSourceQueuesResponse = new ListDeadLetterSourceQueuesResponse();
        listDeadLetterSourceQueuesResponse.setListDeadLetterSourceQueuesResult(listDeadLetterSourceQueuesResult);
        listDeadLetterSourceQueuesResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(listDeadLetterSourceQueuesRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(listDeadLetterSourceQueuesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("ListDeadLetterSourceQueuesResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String listSQSQueues() throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Listing All Amazon SQS Queues: ");
		}
		String retVal = null;        
        ListQueuesRequest listQueuesRequest = new ListQueuesRequest();
        if (logger.isDebugEnabled()) {
			logger.debug("ListQueuesRequest: " + listQueuesRequest.toString());
		}
        ListQueuesResult listQueuesResult = SQSImpl.getSqs().listQueues(listQueuesRequest);
        ListQueuesResponse listQueuesResponse = new ListQueuesResponse();
        listQueuesResponse.setListQueuesResult(listQueuesResult);
        listQueuesResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(listQueuesRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(listQueuesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("ListQueuesResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String listSQSQueues(String queueNamePrefix) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Listing All Amazon SQS Queues with Prefix: " + queueNamePrefix);
		}
        String retVal = null;
        ListQueuesRequest listQueuesRequest = new ListQueuesRequest().withQueueNamePrefix(queueNamePrefix);
        if (logger.isDebugEnabled()) {
			logger.debug("ListQueuesRequest: " + listQueuesRequest.toString());
		}
        ListQueuesResult listQueuesResult = SQSImpl.getSqs().listQueues(listQueuesRequest);
        ListQueuesResponse listQueuesResponse = new ListQueuesResponse();
        listQueuesResponse.setListQueuesResult(listQueuesResult);
        listQueuesResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(listQueuesRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(listQueuesResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("ListQueuesResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String purgeSQSQueue(String queueName) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Purging Amazon SQS Queue: " + queueName);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        PurgeQueueRequest purgeQueueRequest = new PurgeQueueRequest().withQueueUrl(queueUrl);
        if (logger.isDebugEnabled()) {
			logger.debug("PurgeQueueRequest: " + purgeQueueRequest.toString());
		}
        SQSImpl.getSqs().purgeQueue(purgeQueueRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(purgeQueueRequest).getRequestId();
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}
		
	public static String receiveSQSMessage(String queueName, int maxNumberOfMessages, int visibilityTimeout, int waitTimeSeconds) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Receiving " + maxNumberOfMessages + " messages from Amazon SQS Queue:" + queueName + " with VisibilityTimeout: " + visibilityTimeout + " and WaitTime: " + waitTimeSeconds);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withQueueUrl(queueUrl).withMaxNumberOfMessages(maxNumberOfMessages).withVisibilityTimeout(visibilityTimeout).withWaitTimeSeconds(waitTimeSeconds);
        if (logger.isDebugEnabled()) {
			logger.debug("ReceiveMessageRequest: " + receiveMessageRequest.toString());
		}
        ReceiveMessageResult receiveMessageResult = SQSImpl.getSqs().receiveMessage(receiveMessageRequest);
        ReceiveMessageResponse receiveMessageResponse = new ReceiveMessageResponse();
        receiveMessageResponse.setReceiveMessageResult(receiveMessageResult);
        receiveMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(receiveMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(receiveMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("ReceiveMessageResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String receiveSQSMessage(String queueName, int maxNumberOfMessages, int visibilityTimeout, int waitTimeSeconds, String attributesJSON) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Receiving " + maxNumberOfMessages + " messages from Amazon SQS Queue:" + queueName + " with VisibilityTimeout: " + visibilityTimeout + " WaitTime: " + waitTimeSeconds + " and Attributes: " + attributesJSON);
		}
        String retVal = null;
        Type token = new TypeToken<List<String>>(){}.getType();
        List<String> attributeNames = new Gson().fromJson(attributesJSON, token);
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withQueueUrl(queueUrl).withMaxNumberOfMessages(maxNumberOfMessages).withVisibilityTimeout(visibilityTimeout).withWaitTimeSeconds(waitTimeSeconds).withAttributeNames(attributeNames).withMessageAttributeNames("All");
        if (logger.isDebugEnabled()) {
			logger.debug("ReceiveMessageRequest: " + receiveMessageRequest.toString());
		}
        ReceiveMessageResult receiveMessageResult = SQSImpl.getSqs().receiveMessage(receiveMessageRequest);
        ReceiveMessageResponse receiveMessageResponse = new ReceiveMessageResponse();
        receiveMessageResponse.setReceiveMessageResult(receiveMessageResult);
        receiveMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(receiveMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(receiveMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("ReceiveMessageResponse: " + retVal);
		}
        return retVal;
	}
	
	/*public static String receiveSQSMessage(String queueUrl, int maxNumberOfMessages, int visibilityTimeout, int waitTimeSeconds, String attributesJSON, String messageAttributesJSON)
	{
		System.out.println("==========================================================================================================");
        System.out.println("Receiving Messages from Amazon SQS Queue with URL:" + queueUrl);
        System.out.println("==========================================================================================================\n");
        
        Type attributeNamesToken = new TypeToken<List<String>>(){}.getType();
        List<String> attributeNames = new Gson().fromJson(attributesJSON, attributeNamesToken);
        
        String retVal = null;
        
        Type messageAttributeNamesToken = new TypeToken<List<String>>(){}.getType();
        List<String> messageAttributeNames = new Gson().fromJson(messageAttributesJSON, messageAttributeNamesToken);
        
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        ReceiveMessageResult receiveMessageResult = SQSImpl.getSqs().receiveMessage(receiveMessageRequest.withQueueUrl(queueUrl).withMaxNumberOfMessages(maxNumberOfMessages).withVisibilityTimeout(visibilityTimeout).withWaitTimeSeconds(waitTimeSeconds).withAttributeNames(attributeNames).withMessageAttributeNames(messageAttributeNames));
        ReceiveMessageResponse receiveMessageResponse = new ReceiveMessageResponse();
        receiveMessageResponse.setReceiveMessageResult(receiveMessageResult);
        receiveMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(receiveMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(receiveMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        System.out.println(retVal);
        return retVal;
	}*/
	
	public static String removeSQSPermission(String queueName, String label) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Removing All Permissions for given AWS Label from Amazon SQS Queue: " + queueName);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        RemovePermissionRequest removePermissionRequest = new RemovePermissionRequest().withQueueUrl(queueUrl).withLabel(label);
        if (logger.isDebugEnabled()) {
			logger.debug("RemovePermissionRequest: " + removePermissionRequest.toString());
		}
        SQSImpl.getSqs().removePermission(removePermissionRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(removePermissionRequest).getRequestId();
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}
	
	public static String sendSQSMessage(String queueName, String messageBody) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Sending Message to Amazon SQS Queue: " + queueName + " Body: " + messageBody);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(messageBody);
        if (logger.isDebugEnabled()) {
			logger.debug("SendMessageRequest: " + sendMessageRequest.toString());
		}
        SendMessageResult sendMessageResult = SQSImpl.getSqs().sendMessage(sendMessageRequest);
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendMessageResult(sendMessageResult);
        sendMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(sendMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(sendMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("SendMessageResponse: " + retVal);
		}
        return retVal;
	}
	
	public static String sendSQSMessage(String queueName, String messageBody, int delaySeconds) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Sending Message to Amazon SQS Queue: " + queueName + " Delay: " + delaySeconds + " Body: " + messageBody);
		}
        String retVal = null;
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(messageBody).withDelaySeconds(delaySeconds);
        if (logger.isDebugEnabled()) {
			logger.debug("SendMessageRequest: " + sendMessageRequest.toString());
		}
        SendMessageResult sendMessageResult = SQSImpl.getSqs().sendMessage(sendMessageRequest);
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendMessageResult(sendMessageResult);
        sendMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(sendMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(sendMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
			logger.debug("SendMessageResponse: " + retVal);
		}
        return retVal;
	}
	
	/*public static String sendSQSMessage(String queueUrl, String messageBody, String messageAttributesJSON)
	{
		System.out.println("==========================================================================================================");
        System.out.println("Sending Message to Amazon SQS Queue with URL:" + queueUrl);
        System.out.println("==========================================================================================================\n");
        
        String retVal = null;
        
        Type mapType = new TypeToken<Map<String, MessageAttributeValue>>(){}.getType();  
        Map<String, MessageAttributeValue> messageAttributes = new Gson().fromJson(messageAttributesJSON, mapType);
        
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        SendMessageResult sendMessageResult = SQSImpl.getSqs().sendMessage(sendMessageRequest.withQueueUrl(queueUrl).withMessageBody(messageBody).withMessageAttributes(messageAttributes));
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendMessageResult(sendMessageResult);
        sendMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(sendMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(sendMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        System.out.println(retVal);
        return retVal;
	}
	
	public static String sendSQSMessage(String queueUrl, String messageBody, int delaySeconds, String messageAttributesJSON)
	{
		System.out.println("==========================================================================================================");
        System.out.println("Sending Message to Amazon SQS Queue with URL:" + queueUrl);
        System.out.println("==========================================================================================================\n");
        
        String retVal = null;
        
        Type mapType = new TypeToken<Map<String, MessageAttributeValue>>(){}.getType();  
        Map<String, MessageAttributeValue> messageAttributes = new Gson().fromJson(messageAttributesJSON, mapType);
        
        SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(messageBody).withDelaySeconds(delaySeconds);
        sendMessageRequest.setMessageAttributes(messageAttributes);
        SendMessageResult sendMessageResult = SQSImpl.getSqs().sendMessage(sendMessageRequest);
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendMessageResult(sendMessageResult);
        sendMessageResponse.setResponseMetadata(SQSImpl.getSqs().getCachedResponseMetadata(sendMessageRequest));
        try {
			retVal = new ObjectMapper().writeValueAsString(sendMessageResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        System.out.println(retVal);
        return retVal;
	}*/
	
	public static String setQueueAttributes(String queueName, String queueAttributesJSON) throws AmazonClientException, AmazonServiceException
	{
		if (logger.isDebugEnabled()) {
			logger.debug("Setting Attributes to Amazon SQS Queue: " + queueName + " Attributes: " + queueAttributesJSON);
		}
        String retVal = null;     
        Type mapType = new TypeToken<Map<String, String>>(){}.getType();  
        Map<String, String> attributes = new Gson().fromJson(queueAttributesJSON, mapType);
        String queueUrl = SQSImpl.getSqs().getQueueUrl(queueName).getQueueUrl();
        SetQueueAttributesRequest setQueueAttributesRequest = new SetQueueAttributesRequest().withQueueUrl(queueUrl).withAttributes(attributes);
        if (logger.isDebugEnabled()) {
			logger.debug("SetQueueAttributesRequest: " + setQueueAttributesRequest.toString());
		}
        SQSImpl.getSqs().setQueueAttributes(setQueueAttributesRequest);
        retVal = SQSImpl.getSqs().getCachedResponseMetadata(setQueueAttributesRequest).getRequestId();
        if (logger.isDebugEnabled()) {
			logger.debug("RequestID: " + retVal);
		}
        return retVal;
	}

	public static AmazonSQS getSqs() 
	{
		return sqs;
	}
	public static void setSqs(AmazonSQS sqs) 
	{
		SQSImpl.sqs = sqs;
	}
	
	/*public static void main(String[] args) throws Exception 
	{
			
		SQSImpl sqs = new SQSImpl ("AKIAI6NDGWHWOTHELRVA", "Vo8506VYFXK5EaxyZI11GnBDx2PJ+ozruJHwLNDg","sqs.us-east-1.amazonaws.com");
		String queueUrl = SQSImpl.getSqs().getQueueUrl("biswajit").getQueueUrl();
		System.out.println(queueUrl);
		//purgeSQSQueue("https://sqs.us-east-1.amazonaws.com/380615363300/queue1");
		//sendSQSMessage("https://sqs.us-east-1.amazonaws.com/380615363300/queue1", "testmessage1", 0);
		//sendSQSMessage("https://sqs.us-east-1.amazonaws.com/380615363300/queue1", "testmessage2", 0);
		//receiveSQSMessage("https://sqs.us-east-1.amazonaws.com/380615363300/queue1", 1, 0, 0);
		//getSQSQueueAttributes("https://sqs.us-east-1.amazonaws.com/380615363300/queue1");
		//print result
		//System.out.println(output);
		//String[] actionName;
		//actionName[0] = "Error, why?";
		//listSQSDeadLetterSourceQueues("https://sqs.us-east-1.amazonaws.com/380615363300/deadletter1");
	}*/
}
