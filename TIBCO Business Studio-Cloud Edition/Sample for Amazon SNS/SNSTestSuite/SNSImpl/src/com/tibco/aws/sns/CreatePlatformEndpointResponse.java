package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;

public class CreatePlatformEndpointResponse
{
    private ResponseMetadata responseMetadata;

    private CreatePlatformEndpointResult createPlatformEndpointResult;


    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public CreatePlatformEndpointResult getCreatePlatformEndpointResult ()
    {
        return createPlatformEndpointResult;
    }

    public void setCreatePlatformEndpointResult (CreatePlatformEndpointResult createPlatformEndpointResult)
    {
        this.createPlatformEndpointResult = createPlatformEndpointResult;
    }


}
