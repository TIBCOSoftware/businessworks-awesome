package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.UnsubscribeResult;

public class UnsubscribeResponse {

	 private ResponseMetadata responseMetadata;

	    private UnsubscribeResult unsubscribeResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public UnsubscribeResult getUnsubscribeResult() {
			return unsubscribeResult;
		}

		public void setUnsubscribeResult(UnsubscribeResult unsubscribeResult) {
			this.unsubscribeResult = unsubscribeResult;
		}




}
