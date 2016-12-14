package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

public class ReceiveMessageResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReceiveMessageResult receiveMessageResult;
	private ResponseMetadata responseMetadata;
	
	public ReceiveMessageResult getReceiveMessageResult() {
		return receiveMessageResult;
	}

	public void setReceiveMessageResult(ReceiveMessageResult receiveMessageResult) {
		this.receiveMessageResult = receiveMessageResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}