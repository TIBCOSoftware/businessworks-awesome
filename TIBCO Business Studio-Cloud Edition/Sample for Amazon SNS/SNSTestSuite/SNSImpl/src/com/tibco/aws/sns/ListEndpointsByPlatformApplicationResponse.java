package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationResult;;


public class ListEndpointsByPlatformApplicationResponse {

	  private ResponseMetadata responseMetadata;

	    private ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplicationResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListEndpointsByPlatformApplicationResult getListEndpointsByPlatformApplicationResult() {
			return listEndpointsByPlatformApplicationResult;
		}

		public void setListEndpointsByPlatformApplicationResult(
				ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplicationResult) {
			this.listEndpointsByPlatformApplicationResult = listEndpointsByPlatformApplicationResult;
		}

	
}
