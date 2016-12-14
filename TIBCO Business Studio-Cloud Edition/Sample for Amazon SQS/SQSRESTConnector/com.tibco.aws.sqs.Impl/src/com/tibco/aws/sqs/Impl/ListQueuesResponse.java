package com.tibco.aws.sqs.Impl;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.ListQueuesResult;

public class ListQueuesResponse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListQueuesResult listQueuesResult;
	private ResponseMetadata responseMetadata;
	
	public ListQueuesResult getListQueuesResult() {
		return listQueuesResult;
	}

	public void setListQueuesResult(ListQueuesResult listQueuesResult) {
		this.listQueuesResult = listQueuesResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}
}