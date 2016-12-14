package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.SetSMSAttributesResult;

public class SetSMSAttributesResponse {

	 private ResponseMetadata responseMetadata;

	    private SetSMSAttributesResult setSMSAttributesResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public SetSMSAttributesResult getSetSMSAttributesResult() {
			return setSMSAttributesResult;
		}

		public void setSetSMSAttributesResult(
				SetSMSAttributesResult setSMSAttributesResult) {
			this.setSMSAttributesResult = setSMSAttributesResult;
		}



}
