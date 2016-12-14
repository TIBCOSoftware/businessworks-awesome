package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;

public class GetQueueUrlResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetQueueUrlResult getQueueUrlResult;
	private ResponseMetadata responseMetadata;
	
	public GetQueueUrlResult getGetQueueUrlResult() {
		return getQueueUrlResult;
	}

	public void setGetQueueUrlResult(GetQueueUrlResult getQueueUrlResult) {
		this.getQueueUrlResult = getQueueUrlResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}