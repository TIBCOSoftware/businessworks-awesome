package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;

public class ListDeadLetterSourceQueuesResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListDeadLetterSourceQueuesResult listDeadLetterSourceQueuesResult;
	private ResponseMetadata responseMetadata;
	
	public ListDeadLetterSourceQueuesResult getListDeadLetterSourceQueuesResult() {
		return listDeadLetterSourceQueuesResult;
	}

	public void setListDeadLetterSourceQueuesResult(ListDeadLetterSourceQueuesResult listDeadLetterSourceQueuesResult) {
		this.listDeadLetterSourceQueuesResult = listDeadLetterSourceQueuesResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}