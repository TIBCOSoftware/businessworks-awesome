package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;;

public class ListSubscriptionsByTopicResponse {

	   private ResponseMetadata responseMetadata;

	    private ListSubscriptionsByTopicResult listSubscriptionsByTopicResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListSubscriptionsByTopicResult getListSubscriptionsByTopicResult() {
			return listSubscriptionsByTopicResult;
		}

		public void setListSubscriptionsByTopicResult(
				ListSubscriptionsByTopicResult listSubscriptionsByTopicResult) {
			this.listSubscriptionsByTopicResult = listSubscriptionsByTopicResult;
		}


}
