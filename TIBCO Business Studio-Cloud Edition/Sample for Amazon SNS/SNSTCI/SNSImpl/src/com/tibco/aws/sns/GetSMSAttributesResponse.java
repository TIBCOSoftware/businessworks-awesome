package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.GetSMSAttributesResult;

public class GetSMSAttributesResponse {

	   private ResponseMetadata responseMetadata;

	    private GetSMSAttributesResult getSMSAttributesResult;
		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}
	

		public GetSMSAttributesResult getGetSMSAttributesResult() {
			return getSMSAttributesResult;
		}

		public void setGetSMSAttributesResult(GetSMSAttributesResult getSMSAttributesResult) {
			this.getSMSAttributesResult = getSMSAttributesResult;
		}


	
}
