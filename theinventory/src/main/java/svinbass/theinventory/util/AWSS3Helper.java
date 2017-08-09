package svinbass.theinventory.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import svinbass.theinventory.ws.WebServiceHelper;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWSS3Helper {
	
	private static final String SUFFIX = "/";
	private static final Logger logger_c = Logger.getLogger(WebServiceHelper.class);
	
	
	public static void main(String[] args) {
		// credentials object identifying user for authentication
		// user must have AWSConnector and AmazonS3FullAccess for 
		// this example to work
		AWSCredentials credentials = new BasicAWSCredentials(System.getProperty("AWS_ACCESS_KEY_ID"), System.getProperty("AWS_SECRET_ACCESS_KEY"));
		
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		// create bucket - name must be unique for all S3 users
		String bucketName = "vinzone-example-bucket";
		s3client.createBucket(bucketName);
		
		// list buckets
		for (Bucket bucket : s3client.listBuckets()) {
			logger_c.info(" - " + bucket.getName());
		}
		
		// create folder into bucket
		String folderName = "testfolder";
		createFolder(bucketName, folderName, s3client);
		
		// upload file to folder and set it to public
		String fileName = folderName + SUFFIX + "VINOD.jpg";
		s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				new File("E:\\Goodies\\images\\VIN.jpg"))
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		//deleteFolder(bucketName, folderName, s3client);
		
		// deletes bucket
		//s3client.deleteBucket(bucketName);
	}
	
	public static void putFileInS3(File file)	{
		
		logger_c.info("Inside putFileInS3");
		// credentials object identifying user for authentication
				// user must have AWSConnector and AmazonS3FullAccess for 
				// this example to work
				final Properties props = new Properties();
				try {
					props.load(new FileInputStream("/var/www/html/app.properties"));
				} catch (FileNotFoundException e) {
					logger_c.info("FileNotFoundException putFileInS3", e);
					
				} catch (IOException e) {
					logger_c.info("IOException putFileInS3", e);
				}
				
				String id = props.getProperty("aws.id");
				String key = props.getProperty("aws.key");
		
				AWSCredentials credentials = new BasicAWSCredentials(id, key);
				
				logger_c.info(" id " + id);
				logger_c.info(" ky " + key);
				// create a client connection based on credentials
				AmazonS3 s3client = new AmazonS3Client(credentials);
				
				// create bucket - name must be unique for all S3 users
				String bucketName = "vinzone-example-bucket";
				s3client.createBucket(bucketName);
				
				// list buckets
				for (Bucket bucket : s3client.listBuckets()) {
					logger_c.info(" - " + bucket.getName());
				}
				
				// create folder into bucket
				String folderName = "testfolder1";
				createFolder(bucketName, folderName, s3client);
				
				// upload file to folder and set it to public
				String fileName = folderName + SUFFIX + file.getName();
				s3client.putObject(new PutObjectRequest(bucketName, fileName, 
						file)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				
				logger_c.info("exiting putFileInS3");
		
	}
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) {
		List<S3ObjectSummary> fileList = 
				client.listObjects(bucketName, folderName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			client.deleteObject(bucketName, file.getKey());
		}
		client.deleteObject(bucketName, folderName);
	}

}
