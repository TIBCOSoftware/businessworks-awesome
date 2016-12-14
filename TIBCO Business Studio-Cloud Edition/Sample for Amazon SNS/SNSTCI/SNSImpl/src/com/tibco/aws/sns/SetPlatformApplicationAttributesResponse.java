package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesResult;

public class SetPlatformApplicationAttributesResponse {

	 private ResponseMetadata responseMetadata;

	    private SetPlatformApplicationAttributesResult setPlatformApplicationAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SetPlatformApplicationAttributesResult getSetPlatformApplicationAttributesResult() {
			return setPlatformApplicationAttributesResult;
		}

		public void setSetPlatformApplicationAttributesResult(
				SetPlatformApplicationAttributesResult setPlatformApplicationAttributesResult) {
			this.setPlatformApplicationAttributesResult = setPlatformApplicationAttributesResult;
		}

}
