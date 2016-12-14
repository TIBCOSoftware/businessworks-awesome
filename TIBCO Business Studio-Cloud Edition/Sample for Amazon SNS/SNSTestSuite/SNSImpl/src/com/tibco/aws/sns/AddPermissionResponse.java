package com.tibco.aws.sns;

import com.amazonaws.ResponseMetadata;
//import com.amazonaws.services.sns.model.AddPermissionResult;;

public class AddPermissionResponse
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
