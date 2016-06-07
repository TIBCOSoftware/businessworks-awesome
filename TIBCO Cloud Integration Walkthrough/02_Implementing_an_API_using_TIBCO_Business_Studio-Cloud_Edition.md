## Implementing an API using TIBCO Business Studio - Cloud Edition
Now that you've modelled the API using the API Modeler and created a Mock application ,which allows other developers to create their API calls to your application, it is time for you to focus on implementing the actual business logic of the API using TIBCO Business Studio - Cloud Edition

1. Once logged in [download TIBCO Business Studio™ Cloud Edition](https://community.tibco.com/wiki/tibcor-cloud-integration-frequently-asked-questions#toc-10) for your operating system and install it in your workstation. TIBCO Business Studio Cloud Edition is an Eclipse based platform that will enable you to design and create your API workflows and is fully integrated with TIBCO Cloud Integration. Allowing you to publish and work with the all the applications you have deployed in the cloud.

1. The first thing we need to do once we have TIBCO Business Studio running in our workstation is to connect it to out TIBCO Cloud Integration account. To do that we will focus on the API Explorer view. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image11_0.png)
  * To do this in the API Explorer View go to the down arrow icon  on the far right side of the windows and select **Settings**

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image23_0.png)
  * In this Settings window you will see the  TIBCO Cloud Integration Connection already set up for you. We just need to add our login credentials. To do this select the Cloud Configuration in API Registry Configurations and click `Edit...`

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image26.png)
  * Under Authentication fill in with the same the username and password you use to login to your **[TIBCO Cloud Integration](http://cloud.tibco.com/)** (https://cloud.tibco.com) account and click `Finish`.

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image03_2.png)
  * Now the API Explorer will refresh and you should see the list of API Specifications you have modeled on your TIBCO Cloud Integration account. At this point you should see the Hello World specification you modeled in the previous Tutorial.

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image27.png)

1. To implement the API we have defined we need to create a new Application in Business Studio. Just right-click on the Project Explorer window and select `New -> BusinessWorks Application`
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image04_1.png)

1. To create the new application we just need a name. In our case lets choose “hello_world_app”. Just put the name in the `new Project` dialog and leave all the other options with their default setting, and click `Finish`
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image01_1.png)

1. **TIBCO Business Studio Cloud Edition** will create all the assets needed to start implementing our API in our application.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image18_0.png)

1. To start implementing our API we now need to Drag and Drop the API Specification, we already created in our account to our new project. Now click and Drag the Hello World (1.0) API from the API Explorer Window to the Service Descriptors folder in our project.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image05_1.png)

1. This will import your Specification in your projects and create all the assets needed to start implementing.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image07_0.png)

1. As you remember from our previous tutorial our HelloWorld API has a GET method called `greeting` that will receive a `name` as a parameter.  And you can see all that in your project now. To implement the response of this method we just need to Drag the resource `/greeting` into the canvas on the right.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image02_1.png)
_Note: Make sure you drop it outside the Process flow, as marked in the Screenshot_

1. Now you can see the implementation of our GET request. In this example implementation we want our API to respond to the `/greeting` **GET** request with a string that contains `Hello` concatenated to the `{name}` passed as a parameter.
This way when we call the method with a parameter i.e. `GET ./greeting/World` our API will respond with the String `Hello World`.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image12_0.png)

1. To modify our return response to the get method you simply add a statement to the activity called `getOut`.
  * Select the `getOut` Activity by clicking on it

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image06_0.png)
  * Now go to the Properties window below and click the `Input Tab`
  * Once in the Input Tab Click on XPath Expression next to the returned Item to add our response.

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image09_0.png)
  * Let’s build our expression! No need to write any code here, just click on Functions and search for `concat` under String Functions and drag the function to the expression box.

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image17_0.png)
  * The concat function is pretty simple just accepts two strings, based on our response the first string should be `“Hello ”`. Now go ahead and replace `<< string1 >>` with `“Hello ”` (_don’t forget the quotes in your string_)

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image24_0.png)
  * Now for the second part you need to use the GET request parameter which you can find it in the `Data Source` Tab by expanding the tree on `$get -> parameters -> greetingGetParameters -> name` Just drag and drop the parameter name over `<< string2,... >>`

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image25_0.png)
  * Your expression is now done! It should look like this

    ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image21_0.png)
  * And we are done!! So go ahead and save your Process by going to the menu and selecting `File -> Save`

1. The only thing left to do is to deploy your brand new application to the cloud. How do you do this?
Very Simple. Just go to the Project Explorer and right click on your Application in our case “hello_world_app” and select Push to Cloud…
(Note: Business Studio creates 2 objects in the Project Explorer for you one is the  _Application Module_ which uses this icon ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image22_0.png) and ends.
And the other object is the actual Application itself which uses this icon ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image16_0.png))

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image10_0.png)

  That’s it! No call to IT, no meeting with Cloud Ops, nothing. All is taken care by TIBCO Cloud Integration for you.

1. Now again is time to test our Application. Go to your **[TIBCO Cloud Integration](https://cloud.tibco.com)** account and in the Apps page you will see the Application we just have deployed (`hello_world_app`) from Business Studio ready to go.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image00_1.png)

1. Similar to what we did with our Mock application, clicking on `Endpoint` and then on `VIEW API` will launch the API tester.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image15_0.png)

1. Now to test our Response, let’s enter some value in the parameter, like `Cloud`
![](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image13_0.png)

1. Then click on **Try it Now!** and you will be able to see your API implementation working! Returning `Hello Cloud` in the Response Body
![](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image29.png)

## More samples for TIBCO Business Studio - Cloud Edition
You can find samples for TIBCO Business Studio - Cloud Edition which cover the Plug-ins here
* tibco.tci.samples.dcrm
* tibco.tci.samples.marketo
* tibco.tci.samples.netsuite
* tibco.tci.samples.salesforce
* tibco.tci.samples.salesforcemarketo
* tibco.tci.samples.soapcurrencyconverter
