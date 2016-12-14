package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SubscribeResponse {

	 private ResponseMetadata responseMetadata;

	    private SubscribeResult subscribeResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SubscribeResult getSubscribeResult() {
			return subscribeResult;
		}

		public void setSubscribeResult(SubscribeResult subscribeResult) {
			this.subscribeResult = subscribeResult;
		}


}
