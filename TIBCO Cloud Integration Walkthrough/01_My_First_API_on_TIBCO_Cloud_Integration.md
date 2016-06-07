## My First API on TIBCO® Cloud Integration
With TIBCO® Cloud Integration you can model, run and test your API in minutes without a single line of code. Understanding your API and how it's structured is easy when you use our visual API Modeler tool. It's time to get your hands dirty and model and test a REST API in minutes.

1. Once you are logged in, from the TIBCO® Cloud Integration main page click the API Specs option from the top menu. This will open the API Modeling Tool
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/image15.png)

1. To create a new API specification (the contract on which the API will be built) click the large orange button that says `CREATE`

1. Every new API specification needs two basic elements, a name and a version, before the modeling can start. For this tutorial let's use the following:
  * API Name: `HelloWorld`
  * Version: `1.0`

  _please note that the name and the version number must be unique_

1. After you fill in the details click on the blue `Create` button which will take you to the API Modeler
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/4_0.png)

1. At the top part of the modeler you can add a description that explains to others what your API will do by clicking on the `Add/edit an API description`. It is always a good idea to have a description of the API so let's add it and click `Save`
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/5.png)

1. Now it’s time to add our first resource. The API Modeler let's you create all sorts of resources and supports almost all HTTP operations but for this tutorial let's focus on the `GET` operation which will have a parameter as well.
  * Click on the `Add Resource` button
  * On the path enter `/greeting/{name}` _this will create a new resource called greeting and it has a parameter called name which we'll use later on_
  * Click on `GET` so the block turns blue and click `Save` to save the changes
  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/6.png)

  _Note: Based on this model the `/greeting` endpoint supports GET requests and accepts a parameter `name`, so when calling this API you can replace the parameter for an actual value. i.e.: GET: /greeting/MyName and `MyName` will be passed as a value to the API._

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/6-2.png)

1. Now let’s modify the response for our GET operation. We'll return a simple string for now, and later on show you how a more advanced response can be added. To modify the response click on the `Response` tab to switch; and then hover over the left corner of the `200/OK` and click `Edit`. This will allow you to edit the response that is sent back for the HTTP Status Code 200 (OK)
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/7.png)

1. In the dialog that opens set the Schema to `String` and click on `Add example`
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/8.png)

1. In the example dialog add `"Hello World"` and click `Save` to close the example dialog and click `Save` again to close the response dialog. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/9.png)

1. Now while this is a simple API, the modeler allowed us to model a functional and well defined specification. Click `Exit` at the top right of your screen so we save our API Specification and return to the API Specification list.

1. With our API Specification ready is time to build a real application so we can test our API! Sounds daunting? No worries, still no need to write one line of code. TIBCO® Cloud Integration allows you to generate a Mock Application based on the specification you just created. This Mock Application is an actual application that implements the specification and returns what you've defined as the response. All that without leaving your browser! So let’s create one now.

1. To create your Mock Application hover over your API, click on the context menu and select `Create Mock app` ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/12.png)

1. Give your application a name, and click `Create`. The default name will be fine for this example ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/13.png)

1. Clicking the create button will return you to the `Application List` page and you'll see a progress bar that indicates we're doing the work for you. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/14.png)

1. Once the process finished and the application is built you will see it on the main screen.
The Green Circle next to the App Name means that the app is running and ready to answer requests. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/15.png)

1. Now that our application is ready and running on the Cloud is time to test it! To test the API you can easily send a message to it by clicking on `Endpoint`. With TIBCO® Cloud Integration we even made that easier and added the API Tester which you can get to by clicking `VIEW API`. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/16.png)

  _Note: if you do not see your methods click `Show/Hide` on the right top menu_

1. Just click on `GET` and enter any value in your parameter i.e. `TIBCO`. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/17.png)

1. Then click on `Try it Now!` and you will be able to see your API working returning a response. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/18.png)

1. But wait, that is not the response we expected to get? We've entered `TIBCO` as the name and it responds back `Hello World`! Remember the example we added as part of the model? That is what the Mock application returns. We can add a bit more logic to it to let it return the actual name that was passed.

1. Back on the main `Application List` page click on the application to see the `Application Details` page. You'll see that the operation we created is listed and the response are 'simple'. In order to add a bit more logic click on `Switch to Advanced Responses`. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/20.png)

1. Hover over the response and click `EDIT` in the blue box. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/21.png)

1. The following code will set the HTTP response code to `200` and will send your expected response back to the API called
```
var pathparams = req.params;
res.status = 200;
res.body = "Hello " + pathparams.name;
```
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/22.png)
1. Click on the `Save` button to save the data and click `Update Mock App` to push your updates to the Cloud! Again you'll see a progress bar that indicates we're doing the work of updating your Mock application for you.

1. Now when you test the application using the exact same steps you'll see that it responds with the expected result `Hello TIBCO`. ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/24.png)
