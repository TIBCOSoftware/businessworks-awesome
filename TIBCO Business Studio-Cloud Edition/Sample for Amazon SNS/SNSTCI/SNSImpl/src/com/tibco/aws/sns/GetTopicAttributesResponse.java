package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;


public class GetTopicAttributesResponse {

	   private ResponseMetadata responseMetadata;

	    private GetTopicAttributesResult getTopicAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public GetTopicAttributesResult getGetTopicAttributesResult() {
			return getTopicAttributesResult;
		}

		public void setGetTopicAttributesResult(
				GetTopicAttributesResult getTopicAttributesResult) {
			this.getTopicAttributesResult = getTopicAttributesResult;
		}


}
