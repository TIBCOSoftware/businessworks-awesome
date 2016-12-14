package com.tibco.aws.sns;



import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.OptInPhoneNumberResult;

public class OptInPhoneNumberResponse {

	 private ResponseMetadata responseMetadata;

	    private OptInPhoneNumberResult optInPhoneNumberResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public OptInPhoneNumberResult getOptInPhoneNumberResult() {
			return optInPhoneNumberResult;
		}

		public void setOptInPhoneNumberResult(
				OptInPhoneNumberResult optInPhoneNumberResult) {
			this.optInPhoneNumberResult = optInPhoneNumberResult;
		}


}
