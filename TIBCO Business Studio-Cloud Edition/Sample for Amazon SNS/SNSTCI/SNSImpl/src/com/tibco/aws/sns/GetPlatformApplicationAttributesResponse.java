package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesResult;

public class GetPlatformApplicationAttributesResponse
{
    private ResponseMetadata responseMetadata;

    private GetPlatformApplicationAttributesResult getPlatformApplicationAttributesResult;



    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public GetPlatformApplicationAttributesResult getGetPlatformApplicationAttributesResult ()
    {
        return getPlatformApplicationAttributesResult;
    }

    public void setGetPlatformApplicationAttributesResult (GetPlatformApplicationAttributesResult getPlatformApplicationAttributesResult)
    {
        this.getPlatformApplicationAttributesResult = getPlatformApplicationAttributesResult;
    }
}
