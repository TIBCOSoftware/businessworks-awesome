package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;

public class GetEndpointAttributesResponse
{
    private ResponseMetadata responseMetadata;

    private GetEndpointAttributesResult getEndpointAttributesResult;

    public ResponseMetadata getResponseMetadata ()
    {
        return responseMetadata;
    }

    public void setResponseMetadata (ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public GetEndpointAttributesResult getGetEndpointAttributesResult ()
    {
        return getEndpointAttributesResult;
    }

    public void setGetEndpointAttributesResult (GetEndpointAttributesResult getEndpointAttributesResult)
    {
        this.getEndpointAttributesResult = getEndpointAttributesResult;
    }

}
