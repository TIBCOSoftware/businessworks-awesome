## Defining a Microflow on TIBCO Cloud Integration
Not all APIs are created equal and sometimes all you need is to change the behavior of your API a little to cover something very specific. Should you go back to TIBCO Business Studio - Cloud Edition and add that logic (implementing some complex If-Then-Else structures) or is there another way? With the Microflow technology in TIBCO Cloud Integration you can use `stages` defined in to change the behavior of APIs without changing the back-end service implementation.

Let's take a simple example where you use the Mock application that you created earlier and add a Microflow to change the behavior of the API. In this case we'll safeguard against overflowing the backend (`Throttling`) and change the response (`Transformation`).

1. In the `Application List` on **[TIBCO Cloud Integration](https://cloud.tibco.com)** select the `Hamburger Menu` of your application and choose to `Add Microflow`
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/1_0.png)

1. Applications can have more than one endpoint, so you'll also need to choose which endpoint to add a Microflow to. In this case your application only has one endpoint available so you can pick that one
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/2.png)

1. In the pop-up dialog you'll need to specify a name for your Microflow Application. Something like `MyMicroflowApp` will do nicely.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/3.png)

1. You'll be directed to the Microflow modeler in which each operation in the API you're adding a Microflow to will have a `Request / Response pipeline diagram`. You can add stages that alter the behavior of the API by clicking on the `+` symbols of either the Request or Response side.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/4_1.png)

1. To safeguard against overflowing the backend click on the `+` symbol on the request side of the diagram and select the `Throttling` stage.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/5_0.png)

1. You can configure the amount of transactions per second to any value you like and click on `Add` to add the stage and to return to the diagram.

1. To change the response click on the `+` symbol on the response side of the diagram and select the `Transform Response` stage.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/7_0.png)

1. You can add a name and a description for the transformation so you can easily identify it later. The blocks below show you the input and output JSON objects based on the JSON schema the API exposes. In the earlier sample we didn't create a JSON schema so there is no data visible. In the big blue code block you can add JavaScript code to transform the response into whatever you want. For our purpose add the below code above `done(res)`. This will add `from TIBCO Software Inc.` at the end of the response it receives from the Mock application.

  ```
res = res + " from TIBCO Software Inc.";
```
  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/8_0.png)

  _Note: as you start typing you'll see a blue text **Update** appear next to the Output JSON. Clicking this button will execute your code and you can see what your transformation does to the input_

  Click `Add` to save the code and return back to the diagram

1. Back on the Microflow modeling page the `Push updates` button is now enabled. You can click it to send the updates you made to the Microflow application. Just like with the Mock application you'll see a progress bar that indicates we're doing the work for you.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/9_1.png)
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/9-2.png)

1. Once the process finished and the application is built you will see it on the main screen.
The Green Circle next to the App Name means that the app is running and ready to answer requests.

1. Now that our application is ready and running on the Cloud is time to test it! To test the API you can easily send a message to it by clicking on `Endpoint`. With TIBCO® Cloud Integration we even made that easier and added the API Tester which you can get to by clicking `VIEW API`.
  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/11.png)
  _Note: if you do not see your methods click `Show/Hide` on the right top menu_

1. Just click on `GET` and enter any value in your parameter i.e. `TIBCO`.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/12_1.png)

1. Then click on `Try it Now!` and you will be able to see your API working returning a response.
![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/13_0.png)
