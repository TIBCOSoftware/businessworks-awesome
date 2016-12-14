package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;;

public class GetSubscriptionAttributesResponse {

	   private ResponseMetadata responseMetadata;

	    private GetSubscriptionAttributesResult getSubscriptionAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public GetSubscriptionAttributesResult getGetSubscriptionAttributesResult() {
			return getSubscriptionAttributesResult;
		}

		public void setGetSubscriptionAttributesResult(
				GetSubscriptionAttributesResult getSubscriptionAttributesResult) {
			this.getSubscriptionAttributesResult = getSubscriptionAttributesResult;
		}

}
