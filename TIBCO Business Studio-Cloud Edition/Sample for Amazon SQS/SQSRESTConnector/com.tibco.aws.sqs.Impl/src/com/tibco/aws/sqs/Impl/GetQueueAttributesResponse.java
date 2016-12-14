package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;

public class GetQueueAttributesResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetQueueAttributesResult getQueueAttributesResult;
	private ResponseMetadata responseMetadata;
	
	public GetQueueAttributesResult getGetQueueAttributesResult() {
		return getQueueAttributesResult;
	}

	public void setGetQueueAttributesResult(GetQueueAttributesResult getQueueAttributesResult) {
		this.getQueueAttributesResult = getQueueAttributesResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}