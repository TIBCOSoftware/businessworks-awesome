package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;

public class ConfirmSubscriptionResponse {

	 private ResponseMetadata responseMetadata;

	    private ConfirmSubscriptionResult confirmSubscriptionResult;

	    public ResponseMetadata getResponseMetadata ()
	    {
	        return responseMetadata;
	    }

	    public void setResponseMetadata (ResponseMetadata responseMetadata)
	    {
	        this.responseMetadata = responseMetadata;
	    }

	    public ConfirmSubscriptionResult getConfirmSubscriptionResult ()
	    {
	        return confirmSubscriptionResult;
	    }

	    public void setConfirmSubscriptionResult (ConfirmSubscriptionResult confirmSubscriptionResult)
	    {
	        this.confirmSubscriptionResult = confirmSubscriptionResult;
	    }	
	
}
