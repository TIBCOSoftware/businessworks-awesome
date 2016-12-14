package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.CreateQueueResult;

public class CreateQueueResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreateQueueResult createQueueResult;
	private ResponseMetadata responseMetadata;
	
    public CreateQueueResult getCreateQueueResult() {
		return createQueueResult;
	}

	public void setCreateQueueResult(CreateQueueResult createQueueResult) {
		this.createQueueResult = createQueueResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}