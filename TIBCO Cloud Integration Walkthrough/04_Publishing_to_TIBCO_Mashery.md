## Publish to TIBCO Mashery
With the vast majority of integration programs being done with APIs the first step to a successful API program is the creation of APIs (which was covered by the previous tutorials). The next step, which is as important as creating the APIs, is being able to manage the API and make sure it can be found and you can engage your developers. With TIBCO Cloud Integration and TIBCO Mashery we've got you covered on that front as well, so in this tutorial we'll walk you through the required steps of publishing an API to TIBCO Mashery.

_Note: This tutorial also requires a valid login to TIBCO Mashery_

## Requesting access to the TIBCO Mashery API
In order to get access to the TIBCO Mashery API you'll need an account which you can create on the Mashery Support site.
1. Go to http://support.mashery.com and click the `Register` button at the top right of the screen to create a new account for TIBCO Mashery

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/1-register_for_account_0.png)
1. Your newly created account needs an application to connect to the API so you'll need to register for a new application by clicking on 'My Account' (top bar) and after that clicking on the 'Get API Keys' button

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/2-create_new_api_key.png)
1. When you register a new application to get a key it is important to check the 'Issue a new Key' since you'll need that later on in the process

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/3-register_new_application.png)
1. Your application will now be registered and you should receive an email with the key as well. In order to get access to the TIBCO Mashery API you need to work with your TIBCO Mashery Customer Success Team to be able to access the `V3 API`

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/4-application_registered-2.png)
1. Once that is done, you're good to go for the second part of the tutorial

## Sending the API endpoint to Mashery
1. Starting from the main application page of TIBCO Cloud Integration you can click on any of the listed apps and click the shortcut menu (the little hamburger menu).

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/1_1.png)
1. From there you can select the `Publish to Mashery` option and you'll be presented with a list of endpoints that the application exposes and that you might want to manage in TIBCO Mashery. 

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/2_0.png)
1. The `Publish to Mashery` dialog box is shown. In order to publish and make sure the right information is added to the API this screen needs a few details. You'll need to enter your `Key` (referred to as ClientId in Mashery), `Secret` (referred to as ClientSecret in Mashery), `Username`, `Password`, `Area ID` and `Traffic Manager Domain` to connect to Mashery. 

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/3_0.png)

  _Note: if you don't have these values please see the above section on `Requesting access to the TIBCO Mashery API` or ask your TIBCO Mashery Customer Success Team_
1. From this screen you can either add the endpoints to an existing API definition in Mashery or create a completely new one. For now we'll create a new one using the `+ New definition`.
1. After specifying a name for the new API, you'll be asked to pick the operations you want to expose in Mashery. By default all of them are selected but you may want to choose different options.

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/5_1.png)
1. Click `Publish` to add all the items to your Mashery instance and on the `Confirmation` screen you'll see a link to your Mashery Control Center

  ![alt text](https://d2wh20haedxe3f.cloudfront.net/sites/default/files/6_0.png)
