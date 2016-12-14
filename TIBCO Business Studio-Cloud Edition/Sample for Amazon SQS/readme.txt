Description about uploaded project:

The uploaded zip (SQSRESTConnector.zip) file contains the following:

1. com.tibco.aws.sqs.Impl: This is the shared module that contains all sub-processes (each sub-process implements one specific AWS SQS API from Java SDK) and all the schemas needed for request and response for each API call. The shared module also contains all java sources implementing SQS APIs from the SDK and libraries needed to compile the java sources.

2. com.tibco.aws.sqs.RESTService: This is the application module for the actual application. It is dependent on the shared module com.tibco.aws.sqs.Impl. The application module will have two packages in the Processes directory.

a. com.tibco.aws.sqs.APITester contains a single process called APIUnitTester that can be run from Studio independently to test all APIs implemented via sub-process calls. 
b. com.tibco.aws.sqs.connector.RESTService is the actual REST service that exposes the Amazon SQS methods as REST APIs.

3. com.tibco.aws.sqs.RESTService.application: This is the actual application that can be pushed to cloud or you can create deployment artifacts out of it.

Deploy and Test:

1. Unzip the project zip file and import all projects in SQSRESTConnector (shared module, application module and application) to latest version of BusinessStudio Cloud Edition. Make sure that there is no validation errors reported in Studio.

2. Either create a new ear file out of the application and push to TCI from TCI web UI. Otherwise, you can simply right click on the application in studio and push the application to cloud or use TCI CLI from command line to upload the ear provided in AWSSQSService/DeploymentArtifacts directory. 

3. After “Push to Cloud” is successful, the application will start running in TCI and will expose eight different endpoints, each endpoint exposing the REST APIs for a specific SQS resource type. Access each endpoint exposed by the application using “API Tester” feature of TCI (swagger) and invoke each API from swagger by providing the required parameters. Constrains for each input parameters have been clearly documented.

PS: For specific APIs (i.e. ChangeMessageVisibility/DeleteMessage), you need to receive the message first and use the ReceiptHandle from your latest receive call’s response to invoke these operations.

Third Party Libraries involved:

The following third party libraries are involved.

aws-java-sdk-1.11.24.jar: AWS Java SDK (Open Source)

This jar has been stripped down (removed SDKs for all other services other than SQS Service) to make the size smaller, so that “Push to Cloud” does not fail.

commons-codec-1.9.jar
commons-logging-1.1.3.jar
httpclient-4.5.2.jar
httpcore-4.4.4.jar
jackson-annotations-2.6.0.jar
jackson-core-2.6.6.jar
jackson-databind-2.6.6.jar
jackson-dataformat-cbor-2.6.6.jar
joda-time-2.8.1.jar

All the above jars are Open Source and Amazon SDK java classes are dependent on these jars.

gson-2.7.1.jar: Open Source (Used to convert java objects to JSON and vice versa)

Unsupported APIs:

All Batch APIs (i.e. ChangeMessageVisibilityBatch, DeleteMessageBatch and SendMessageBatch) from SQS Java SDK are not supported at this point of time.

Unsupported Features:

Sending and receiving messages with Dynamic Message Attributes is not supported at this point of time. 