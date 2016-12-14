package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class SendMessageResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SendMessageResult sendMessageResult;
	private ResponseMetadata responseMetadata;
	
	public SendMessageResult getSendMessageResult() {
		return sendMessageResult;
	}

	public void setSendMessageResult(SendMessageResult sendMessageResult) {
		this.sendMessageResult = sendMessageResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}