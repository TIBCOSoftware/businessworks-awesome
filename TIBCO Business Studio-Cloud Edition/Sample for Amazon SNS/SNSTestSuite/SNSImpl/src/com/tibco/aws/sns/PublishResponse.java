package com.tibco.aws.sns;



import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.PublishResult;

public class PublishResponse {

	 private ResponseMetadata responseMetadata;

	    private PublishResult publishResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public PublishResult getPublishResult() {
			return publishResult;
		}

		public void setPublishResult(PublishResult publishResult) {
			this.publishResult = publishResult;
		}




}
