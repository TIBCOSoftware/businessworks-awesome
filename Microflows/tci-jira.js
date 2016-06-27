/**
 * Copyright (c) 2016, TIBCO Software Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are 
met:
--Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer. 
--Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution. 
--The name of TIBCO Software Inc. may not be used to endorse or promote 
products derived from this software without specific prior written 
permission of TIBCO Software Inc.   

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS 
IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
* This function is used to auto-create subtasks for JIRA issues. Additionally, add a comment on each subtask that was created.
* A webhook must be configured in JIRA to call this endpoint when a new issue is created.
*/

var request = require('request'),
	async = require('async'),
	issueKey = req.payload.issue.key, //we need the key aka id of the issue that was created
	jiraHost = 'INSERT YOUR JIRA HOST NAME',
	jiraAuth = 'INSERT YOUR BASIC AUTH TOKEN';

//define the configuration for what types of subtasks should be created based on the type of issue created.
var config = {
	"Email Only": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001"
	}, {
		summary: "Create HTML Content",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Program Automation",
		issuetype: "10001"
	}, {
		summary: "Schedule email send",
		issuetype: "10001"
	}],
	"Graphic": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001",
		username: "ascheuer"
	}],
	"Marketo Landing Page Only": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001"
	}, {
		summary: "Create landing page & form",
		issuetype: "10001"
	}, {
		summary: "Assign to SFDC campaign",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}],
	"Email & Landing Page": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001"
	}, {
		summary: "Create Email",
		issuetype: "10001"
	}, {
		summary: "Create landing page",
		issuetype: "10001"
	}, {
		summary: "Program Automation",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Schedule email send",
		issuetype: "10001"
	}],
	"Standard Webinar": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001"
	}, {
		summary: "Setup Webinar in Webex",
		issuetype: "10001"
	}, {
		summary: "Setup Webinar in Marketo",
		issuetype: "10001"
	}, {
		summary: "Create invite email",
		issuetype: "10001"
	}, {
		summary: "Create confirmation email",
		issuetype: "10001"
	}, {
		summary: "Create landing page",
		issuetype: "10001"
	}, {
		summary: "Program automation",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Schedule email sends",
		issuetype: "10001"
	}, {
		summary: "Edit webinar",
		issuetype: "10001"
	}, {
		summary: "Create follow-up",
		issuetype: "10001"
	}, {
		summary: "Schedule follow-up",
		issuetype: "10001"
	}],
	"Global Webinar": [{
		summary: "Proofread and Approve Content",
		issuetype: "10001"
	}, {
		summary: "Setup Webinar in Webex",
		issuetype: "10001"
	}, {
		summary: "Setup Webinar in Marketo",
		issuetype: "10001"
	}, {
		summary: "Create invite email",
		issuetype: "10001"
	}, {
		summary: "Create confirmation email",
		issuetype: "10001"
	}, {
		summary: "Create landing page",
		issuetype: "10001"
	}, {
		summary: "Program automation",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Schedule email sends",
		issuetype: "10001"
	}, {
		summary: "Edit webinar",
		issuetype: "10001"
	}, {
		summary: "Create follow-up",
		issuetype: "10001"
	}, {
		summary: "Schedule follow-up",
		issuetype: "10001"
	}],
	"Product Demo": [{
		summary: "Setup webinars",
		issuetype: "10001"
	}, {
		summary: "Create landing pages",
		issuetype: "10001"
	}, {
		summary: "Create confirmation emails",
		issuetype: "10001"
	}, {
		summary: "Create follow-up emails",
		issuetype: "10001"
	}, {
		summary: "Program automation",
		issuetype: "10001"
	}, {
		summary: "Post to site",
		issuetype: "10001"
	}, {
		summary: "Schedule email sends",
		issuetype: "10001"
	}],
	"Live Event": [{
		summary: "Proofread and approve content",
		issuetype: "10001"
	}, {
		summary: "Create invite emails",
		issuetype: "10001"
	}, {
		summary: "Create landing page",
		issuetype: "10001"
	}, {
		summary: "Create follow-up email",
		issuetype: "10001"
	}, {
		summary: "Program automation",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Schedule email send",
		issuetype: "10001"
	}],
	"Small Event": [{
		summary: "Create form",
		issuetype: "10001"
	}, {
		summary: "Assign to SFDC",
		issuetype: "10001"
	}, {
		summary: "Create alerts",
		issuetype: "10001"
	}, {
		summary: "Test",
		issuetype: "10001"
	}, {
		summary: "Program automation",
		issuetype: "10001"
	}, {
		summary: "Schedule email send",
		issuetype: "10001"
	}],
	"Customer Update": [{
		summary: "Write or edit content",
		issuetype: "10001"
	}, {
		summary: "Receive + stage approved content from Editorial",
		issuetype: "10001"
	}, {
		summary: "Receive + stage new head banner from Creative",
		issuetype: "10001"
	}, {
		summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
		issuetype: "10001"
	}, {
		summary: "Stage it + send staging link to requester for approval",
		issuetype: "10001"
	}, {
		summary: "Requester approves the page",
		issuetype: "10001"
	}, {
		summary: "Publish the page",
		issuetype: "10001"
	}, {
		summary: "Notify requester that page is published on the website",
		issuetype: "10001"
	}],
	"Event": [{
		summary: "Write or edit content",
		issuetype: "10001"
	}, {
		summary: "Receive + stage approved content from Editorial",
		issuetype: "10001"
	}, {
		summary: "Receive + stage new head banner from Creative",
		issuetype: "10001"
	}, {
		summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
		issuetype: "10001"
	}, {
		summary: "Stage it + send staging link to requester for approval",
		issuetype: "10001"
	}, {
		summary: "Requester approves the page",
		issuetype: "10001"
	}, {
		summary: "Publish the page",
		issuetype: "10001"
	}, {
		summary: "Notify requester that page is published on the website",
		issuetype: "10001"
	}],
	"General Request": [{
		summary: "Write or edit content",
		issuetype: "10001"
	}, {
		summary: "Receive + stage approved content from Editorial",
		issuetype: "10001"
	}, {
		summary: "Receive + stage new head banner from Creative",
		issuetype: "10001"
	}, {
		summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
		issuetype: "10001"
	}, {
		summary: "Stage it + send staging link to requester for approval",
		issuetype: "10001"
	}, {
		summary: "Requester approves the page",
		issuetype: "10001"
	}, {
		summary: "Publish the page",
		issuetype: "10001"
	}, {
		summary: "Notify requester that page is published on the website",
		issuetype: "10001"
	}],
	"Update Web Page": [{
		summary: "Write or edit content",
		issuetype: "10001"
	}, {
		summary: "Receive + stage approved content from Editorial",
		issuetype: "10001"
	}, {
		summary: "Receive + stage new head banner from Creative",
		issuetype: "10001"
	}, {
		summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
		issuetype: "10001"
	}, {
		summary: "Stage it + send staging link to requester for approval",
		issuetype: "10001"
	}, {
		summary: "Requester approves the page",
		issuetype: "10001"
	}, {
		summary: "Publish the page",
		issuetype: "10001"
	}, {
		summary: "Notify requester that page is published on the website",
		issuetype: "10001"
	}],
	"Partner Page Update": [{
		summary: "Write or edit content",
		issuetype: "10001"
	}, {
		summary: "Receive + stage approved content from Editorial",
		issuetype: "10001"
	}, {
		summary: "Receive + stage new head banner from Creative",
		issuetype: "10001"
	}, {
		summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
		issuetype: "10001"
	}, {
		summary: "Stage it + send staging link to requester for approval",
		issuetype: "10001"
	}, {
		summary: "Requester approves the page",
		issuetype: "10001"
	}, {
		summary: "Publish the page",
		issuetype: "10001"
	}, {
		summary: "Notify requester that page is published on the website",
		issuetype: "10001"
	}],
	"New Web Page (Not Marketo)": [{
			summary: "Assign draft content to Editorial for review",
			issuetype: "10001"
		}, {
			summary: "Assign the web team (Angie) to create a new mock up for this page",
			issuetype: "10001"
		}, {
			summary: "Assign built.io (Sunil) to stage new page using Angies mock up",
			issuetype: "10001"
		}, {
			summary: "Receive + stage approved content from Editorial",
			issuetype: "10001"
		}, {
			summary: "Receive + stage new head banner from Creative",
			issuetype: "10001"
		}, {
			summary: "Receive + stage any updated assets (video, datasheet, etc.) from Creative",
			issuetype: "10001"
		}, {
			summary: "Stage it + send staging link to requester for approval",
			issuetype: "10001"
		}, {
			summary: "Requester approves the page",
			issuetype: "10001"
		}, {
			summary: "Publish the page",
			issuetype: "10001"
		}, {
			summary: "Notify requester that page is published on the website",
			issuetype: "10001"
		}

	],
	"New Written Document": [{
		summary: "Write/edit text",
		issuetype: "10001"
	}, {
		summary: "Create layout",
		issuetype: "10001"
	}, {
		summary: "Post to web",
		issuetype: "10001"
	}],
	"Customer Story": [{
		summary: "Write/edit story",
		issuetype: "10001"
	}, {
		summary: "Create layout",
		issuetype: "10001"
	}, {
		summary: "Write web profile",
		issuetype: "10001"
	}, {
		summary: "Post to web",
		issuetype: "10001"
	}, {
		summary: "Write blog",
		issuetype: "10001"
	}, {
		summary: "Post to blog",
		issuetype: "10001"
	}]


};

/* Start the flow */

//first, get the details of the issue
getIssueDetails(function(err, results) {
	if (err) {
		console.info('Error getting issue details from JIRA' + issueKey);
		console.info(err);
		return done(req);
	}

	//then, determine if subtasks should be created based on business logic
	var validationFailureReason = validate(results);
	if (validationFailureReason) {
		console.info(validationFailureReason + ' for ' + issueKey);
		return done(req);
	}

	//create the subtasks based on the predefined configuration rules
	createSubTasks(results, function(err, issuesCreated) {
		if (err) {
			console.info('error creating subtasks for ' + issueKey);
			console.info(err);
			return done(req);
		}

		//add a default comment of each subtask that was created
		createComments(issuesCreated, function(err) {
			if (err) {
				console.info('error creating comments');
				console.info(err);
				return done(req);
			}
			console.info('All tasks completed for ' + issueKey);
			return done(req);
		});
	});

});

/* End of the flow */


function getIssueDetails(callback) {

/* NOTE: This is horrible, but we need to set a timeout before running. If an issue is
*  cloned, JIRA fires the API call to this app as soon as the issue is created.
* However, at this stage subtasks and issue links have not been created / attached,
* so we have no way to run the validation without waiting. This is our last resort.
*/


	/* Because setTimeout is not exposed in the run-time, re-create the
	*  functionality using the async module. To do this, we use retry and
	*  auto fail the first attempt so we can use the interval to wait
	*/

	var asyncCounter = 0;
	async.retry({
		times: 2,
		interval: 10000
	}, function(cb, results) {
		if (asyncCounter === 0) {
			asyncCounter++;
			cb({
				"error": "return a dummy error so we wait for the defined interval"
			}, null);
		} else {

			makeJIRARequest();
			cb(null, {});
		}
	}, function(err, result) {
		// Do nothing
	});

	function makeJIRARequest() {
		var options = {
			url: jiraHost + '/rest/api/2/issue/' + issueKey,
			method: 'GET',
			headers: {
				'Authorization': jiraAuth,
				'Content-Type': 'application/json'
			}
		};
		request(options, function(err, response, body) {
			//exit if the call fails
			if (err || response.statusCode !== 200) {
				callback(err);
			}

			var issueDetails = {
				"issue": JSON.parse(body)
			};
			callback(null, issueDetails);
		});
	}
}

function validate(issueDetails) {

	//check if there is no issue data
	if (typeof issueDetails.issue.fields === "undefined") {
		return 'Missing issue details';
	}

	//if it's a subtask exit since we don't want to create nested subtasks (and technically cannot)
	if (issueDetails.issue.fields.issuetype.subtask) {
		return 'Issue is of type subtask';
	}

	//don't create subtasks if there are already subtasks associated with the issue
	if (issueDetails.issue.fields['subtasks'].length > 0) {
		return 'Issue already has subtasks';
	}

	//don't create subtasks for cloned issues
	if (issueDetails.issue.fields.issuelinks.length > 0) {
		var isCloned = false,
			links = issueDetails.issue.fields.issuelinks;
		for (var i = 0, l = links.length; i < l; i++) {
			if (links[i].type.outward == "clones") {
				isCloned = true;
			}
		}
		if (isCloned) {
			return 'Issue is a cloned issue';
		}
	}

	//no validation issues found
	return null;

}

//create the subtasks
function createSubTasks(issueDetails, callback) {
	var project = issueDetails.issue.fields.project.key,
		issueType = issueDetails.issue.fields.issuetype.name;

	var requestTemplate = {
		"issueUpdates": []
	};

	function addTask(project, summary, description, parent, issuetype, username) {
		return {
			"update": {},
			"fields": {
				"project": {
					"key": project
				},
				"summary": summary,
				"description": description,
				"parent": {
					"key": parent
				},
				"issuetype": {
					"id": issuetype
				},
				"assignee": {
					"name": username || ""
				}
			}

		};
	}
	//get the configuration for the issue type
	var tasksToAdd = config[issueType] || [];

	//queue all the tasks to be created
	for (var i = 0, l = tasksToAdd.length; i < l; i++) {
		requestTemplate.issueUpdates.push(addTask(project, tasksToAdd[i].summary, "", issueKey, tasksToAdd[i].issuetype, tasksToAdd[i].username));
	}

	//define the API call
	var options = {
		url: jiraHost + '/rest/api/2/issue/bulk',
		method: 'POST',
		json: requestTemplate,
		headers: {
			'Authorization': jiraAuth,
			'Content-Type': 'application/json'
		}
	};

	//call the JIRA API
	request(options, function(err, response, body) {
		if (err) {
			callback(err);
		}
		if (typeof body.issues === 'undefined') {
			callback('No subtasks were created');
		}
		callback(null, body.issues || []);
	});
}

//Add a comment to all issues that are passed in as subTaskKeys
function createComments(subTaskKeys, callback) {

	var commentOptions, counter = 0;

	for (var i = 0, l = subTaskKeys.length; i < l; i++) {
		commentOptions = {
			"url": jiraHost + '/rest/api/2/issue/' + subTaskKeys[i].key + '/comment',
			"method": "POST",
			"json": {
				"body": "Please place comments on the parent ticket to increase visibility. If you do place a comment on this subtask be sure to @ tag users."
			},
			"headers": {
				'Authorization': jiraAuth,
				'Content-Type': 'application/json'
			}
		};
		request(commentOptions, function(err, response, body) {
			//log an error, but don't exit since there are other parallel calls
			if (err) {
				console.info('Error creating comment on subtask');
			}
			//increment the counter and check to see if all of the calls have returned
			counter++;
			if (counter === subTaskKeys.length) {
				callback(null, {});
			}
		});
	}
}
