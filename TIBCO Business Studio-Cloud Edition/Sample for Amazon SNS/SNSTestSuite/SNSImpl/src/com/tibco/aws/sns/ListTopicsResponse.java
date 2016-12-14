package com.tibco.aws.sns;



import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListTopicsResult;

public class ListTopicsResponse {
	 private ResponseMetadata responseMetadata;

	    private ListTopicsResult listTopicsResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListTopicsResult getListTopicsResult() {
			return listTopicsResult;
		}

		public void setListTopicsResult(ListTopicsResult listTopicsResult) {
			this.listTopicsResult = listTopicsResult;
		}



}
