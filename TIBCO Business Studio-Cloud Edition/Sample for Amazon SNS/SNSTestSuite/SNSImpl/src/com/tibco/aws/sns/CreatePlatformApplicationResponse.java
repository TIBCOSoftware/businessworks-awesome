package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;

public class CreatePlatformApplicationResponse
{
    private ResponseMetadata responseMetadata;

    private CreatePlatformApplicationResult createPlatformApplicationResult;

 
    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public CreatePlatformApplicationResult getCreatePlatformApplicationResult ()
    {
        return createPlatformApplicationResult;
    }

    public void setCreatePlatformApplicationResult (CreatePlatformApplicationResult createPlatformApplicationResult)
    {
        this.createPlatformApplicationResult = createPlatformApplicationResult;
    }


}
