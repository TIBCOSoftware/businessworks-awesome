package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;

public class DeletePlatformApplicationResponse
{
    private ResponseMetadata responseMetadata;

    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }
}
