package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SetEndpointAttributesResult;

public class SetEndpointAttributesResponse {

	 private ResponseMetadata responseMetadata;

	    private SetEndpointAttributesResult setEndpointAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SetEndpointAttributesResult getSetEndpointAttributesResult() {
			return setEndpointAttributesResult;
		}

		public void setSetEndpointAttributesResult(
				SetEndpointAttributesResult setEndpointAttributesResult) {
			this.setEndpointAttributesResult = setEndpointAttributesResult;
		}


}
