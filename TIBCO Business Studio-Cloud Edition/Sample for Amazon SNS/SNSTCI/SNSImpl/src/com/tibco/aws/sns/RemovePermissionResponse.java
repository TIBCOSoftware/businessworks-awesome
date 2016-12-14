package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.RemovePermissionResult;

public class RemovePermissionResponse {

	 private ResponseMetadata responseMetadata;

	    private RemovePermissionResult removePermissionResult;

		public ResponseMetadata getResponseMetadata() {
			return responseMetadata;
		}

		public void setResponseMetadata(ResponseMetadata responseMetadata) {
			this.responseMetadata = responseMetadata;
		}

		public RemovePermissionResult getRemovePermissionResult() {
			return removePermissionResult;
		}

		public void setRemovePermissionResult(
				RemovePermissionResult removePermissionResult) {
			this.removePermissionResult = removePermissionResult;
		}



}
