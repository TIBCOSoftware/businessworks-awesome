package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListPhoneNumbersOptedOutResult;;;

public class ListPhoneNumbersOptedOutResponse {
	  private ResponseMetadata responseMetadata;

	    private ListPhoneNumbersOptedOutResult listPhoneNumbersOptedOutResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListPhoneNumbersOptedOutResult getListPhoneNumbersOptedOutResult() {
			return listPhoneNumbersOptedOutResult;
		}

		public void setListPhoneNumbersOptedOutResult(
				ListPhoneNumbersOptedOutResult listPhoneNumbersOptedOutResult) {
			this.listPhoneNumbersOptedOutResult = listPhoneNumbersOptedOutResult;
		}


		
}
