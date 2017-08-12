package svinbass.theinventory.aws;

/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 * This sample demonstrates how to make basic requests to Amazon SQS using the
 * AWS SDK for Java.
 *
 * Prerequisites: You must have a valid Amazon Web Services developer account,
 * and be signed up to use Amazon SQS. For more information about Amazon SQS,
 * see http://aws.amazon.com/sqs
 * 
 * Fill in your AWS access credentials in the provided credentials file
 * template, and be sure to move the file to the default location
 * (~/.aws/credentials) where the sample code loads the credentials from.
 * 
 * IMPORTANT: To avoid accidental leakage of your credentials, DO NOT keep the
 * credentials file in your source directory.
 */
public class AWSSQSHelper {

	private static final String QUEUE_NAME = "vikiQueue";

	private static final Logger logger_c = Logger.getLogger(AWSSQSHelper.class);

	public static void main(String[] args) throws Exception {

		/*
		 * The ProfileCredentialsProvider returns your [default] credential
		 * profile by reading from the credentials file located at
		 * (~/.aws/credentials).
		 */
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider().getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. "
							+ "Please make sure that your credentials file is at the correct "
							+ "location (~/.aws/credentials), and is in valid format.",
					e);
		}

		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
				.withRegion(Regions.US_EAST_1).build();

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon SQS");
		System.out.println("===========================================\n");

		try {
			// Create a queue
			System.out.println("Creating a new SQS queue called MyQueue.\n");
			CreateQueueRequest createQueueRequest = new CreateQueueRequest(
					"MyQueue");
			String myQueueUrl = sqs.createQueue(createQueueRequest)
					.getQueueUrl();

			// List queues
			System.out.println("Listing all queues in your account.\n");
			for (String queueUrl : sqs.listQueues().getQueueUrls()) {
				System.out.println("  QueueUrl: " + queueUrl);
			}
			System.out.println();

			// Send a message
			System.out.println("Sending a message to MyQueue.\n");
			sqs.sendMessage(new SendMessageRequest(myQueueUrl,
					"This is my message text."));

			// Receive messages
			System.out.println("Receiving messages from MyQueue.\n");
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(
					myQueueUrl);
			List<Message> messages = sqs.receiveMessage(receiveMessageRequest)
					.getMessages();
			for (Message message : messages) {
				System.out.println("  Message");
				System.out.println("    MessageId:     "
						+ message.getMessageId());
				System.out.println("    ReceiptHandle: "
						+ message.getReceiptHandle());
				System.out.println("    MD5OfBody:     "
						+ message.getMD5OfBody());
				System.out.println("    Body:          " + message.getBody());
				for (Entry<String, String> entry : message.getAttributes()
						.entrySet()) {
					System.out.println("  Attribute");
					System.out.println("    Name:  " + entry.getKey());
					System.out.println("    Value: " + entry.getValue());
				}
			}
			System.out.println();

			// Delete a message
			System.out.println("Deleting a message.\n");
			String messageReceiptHandle = messages.get(0).getReceiptHandle();
			sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl,
					messageReceiptHandle));

			// Delete a queue
			System.out.println("Deleting the test queue.\n");
			sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, which means your request made it "
							+ "to Amazon SQS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with SQS, such as not "
							+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

	public static void createQueueAndSendMessageToSQS(String message) {
		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		logger_c.info("createQueueAndSendMessageToSQS sqs fetched");
		try {
			CreateQueueResult create_result = sqs.createQueue(QUEUE_NAME);
			logger_c.info("createQueueAndSendMessageToSQS create_result");
		} catch (AmazonSQSException e) {
			if (!e.getErrorCode().equals("QueueAlreadyExists")) {
				throw e;
			}
		}

		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
		logger_c.info("createQueueAndSendMessageToSQS queueUrl" + queueUrl);

		SendMessageRequest send_msg_request = new SendMessageRequest()
				.withQueueUrl(queueUrl).withMessageBody(message)
				.withDelaySeconds(5);
		sqs.sendMessage(send_msg_request);

		logger_c.info("createQueueAndSendMessageToSQS send_msg_request "
				+ message);
	}

	public static void sendMessageToSQS(String message) {
		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		logger_c.info("sendMessageToSQS sqs");
		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
		logger_c.info("sendMessageToSQS queueUrl " + queueUrl);

		SendMessageRequest send_msg_request = new SendMessageRequest()
				.withQueueUrl(queueUrl).withMessageBody(message)
				.withDelaySeconds(5);
		sqs.sendMessage(send_msg_request);
		logger_c.info("sendMessageToSQS sendMessageToSQS " + message);
	}

	public static List<String> receiveMessagesFromSQS() {
		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		logger_c.info("receiveMessagesFromSQS sqs");
		String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
		logger_c.info("receiveMessagesFromSQS queueUrl " + queueUrl);
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl).withAttributeNames("All");
		
		receiveMessageRequest.setMaxNumberOfMessages(10);

		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		logger_c.info("receiveMessagesFromSQS messages size " + messages.size());
		// delete messages from the queue
		for (Message m : messages) {
			logger_c.info("receiveMessagesFromSQS before deletion  "
					+ m.getBody());
			sqs.deleteMessage(queueUrl, m.getReceiptHandle());
			logger_c.info("receiveMessagesFromSQS deleteMessage ");
		}

		List<String> messageStrList = new ArrayList<String>();

		for (Message m : messages) {
			messageStrList.add(m.getBody());
			logger_c.info("receiveMessagesFromSQS message body " + m.getBody());
		}

		logger_c.info("receiveMessagesFromSQS returning");
		return messageStrList;
	}
}