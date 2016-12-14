package com.tibco.aws.sns;


import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.CreateTopicResult;


public class CreateTopicResponse  {

		private CreateTopicResult createTopicResult;
	private ResponseMetadata responseMetadata;


    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public CreateTopicResult getCreateTopicResult ()
    {
        return createTopicResult;
    }

    public void setCreateTopicResult (CreateTopicResult CreateTopicResult)
    {
        this.createTopicResult = CreateTopicResult;
    }
}
