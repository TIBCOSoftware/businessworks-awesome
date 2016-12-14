package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesResult;

public class SetSubscriptionAttributesResponse {

	 private ResponseMetadata responseMetadata;

	    private SetSubscriptionAttributesResult setSubscriptionAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SetSubscriptionAttributesResult getSetSubscriptionAttributesResult() {
			return setSubscriptionAttributesResult;
		}

		public void setSetSubscriptionAttributesResult(
				SetSubscriptionAttributesResult setSubscriptionAttributesResult) {
			this.setSubscriptionAttributesResult = setSubscriptionAttributesResult;
		}



}
