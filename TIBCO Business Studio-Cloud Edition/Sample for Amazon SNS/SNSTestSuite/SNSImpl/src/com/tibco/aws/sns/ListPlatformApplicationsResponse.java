package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ListPlatformApplicationsResult;;

public class ListPlatformApplicationsResponse {

	 private ResponseMetadata responseMetadata;

	    private ListPlatformApplicationsResult listPlatformApplicationsResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public ListPlatformApplicationsResult getListPlatformApplicationsResult() {
			return listPlatformApplicationsResult;
		}

		public void setListPlatformApplicationsResult(
				ListPlatformApplicationsResult listPlatformApplicationsResult) {
			this.listPlatformApplicationsResult = listPlatformApplicationsResult;
		}



}
