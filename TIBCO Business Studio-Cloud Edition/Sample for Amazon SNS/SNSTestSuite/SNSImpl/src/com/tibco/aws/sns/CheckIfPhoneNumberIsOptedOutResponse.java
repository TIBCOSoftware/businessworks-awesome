package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.CheckIfPhoneNumberIsOptedOutResult;;

public class CheckIfPhoneNumberIsOptedOutResponse {

    private CheckIfPhoneNumberIsOptedOutResult checkIfPhoneNumberIsOptedOutResult;
	private ResponseMetadata responseMetadata;
	
	public CheckIfPhoneNumberIsOptedOutResult getCheckIfPhoneNumberIsOptedOutResult() {
		return checkIfPhoneNumberIsOptedOutResult;
	}
	public void setCheckIfPhoneNumberIsOptedOutResult(
			CheckIfPhoneNumberIsOptedOutResult checkIfPhoneNumberIsOptedOutResult) {
		this.checkIfPhoneNumberIsOptedOutResult = checkIfPhoneNumberIsOptedOutResult;
	}
	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}
	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}

}
