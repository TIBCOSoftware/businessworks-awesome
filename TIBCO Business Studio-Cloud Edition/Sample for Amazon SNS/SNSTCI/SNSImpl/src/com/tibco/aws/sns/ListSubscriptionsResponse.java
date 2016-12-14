package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;;

public class ListSubscriptionsResponse {
	 private ResponseMetadata responseMetadata;

	    private ListSubscriptionsResult listSubscriptionsResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListSubscriptionsResult getListSubscriptionsResult() {
			return listSubscriptionsResult;
		}

		public void setListSubscriptionsResult(
				ListSubscriptionsResult listSubscriptionsResult) {
			this.listSubscriptionsResult = listSubscriptionsResult;
		}



}
