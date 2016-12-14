package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SetTopicAttributesResult;

public class SetTopicAttributesResponse {

	 private ResponseMetadata responseMetadata;

	    private SetTopicAttributesResult setTopicAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SetTopicAttributesResult getSetTopicAttributesResult() {
			return setTopicAttributesResult;
		}

		public void setSetTopicAttributesResult(
				SetTopicAttributesResult setTopicAttributesResult) {
			this.setTopicAttributesResult = setTopicAttributesResult;
		}




}
