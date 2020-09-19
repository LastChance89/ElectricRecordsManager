package com.power.front.spring;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationListener implements ApplicationListener<ApplicationPreparedEvent> {
	private final Logger logger = LogManager.getLogger(ConfigurationListener.class);
	private String decodedBinarySecret = "";

	// set in application properties?
	private final String secretName = "Testing_My_work";
	private final String region = "us-east-1";
    private Map<String, String> result;
	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {

		// Only look for the AWS credentials if we are not usiling application
		// properites.
		// This is in so I can do my local testing.
		if (!Stream.of(event.getArgs()).anyMatch(element -> element.equals("useAppProps"))) {

			getSecret();
			ConfigurableEnvironment env = event.getApplicationContext().getEnvironment();

			Properties props = new Properties();
			Properties secondProp = new Properties();
			String dbConnectionString = "jdbc:"+result.get("engine") +"://" + result.get("host")+":"+String.valueOf(result.get("port"))
			+"/web1";
			secondProp.put("spring.datasource.url",dbConnectionString);
			props.put("spring.datasource.username", result.get("username"));
			props.put("spring.datasource.password", result.get("password"));
			props.put("spring.datasource.url",dbConnectionString);
			env.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));
			
		}

	}

	private void getSecret() {
		// Create a Secrets Manager client
		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion(region).build();

		// In this sample we only handle the specific exceptions for the
		// 'GetSecretValue' API.
		// See
		// https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
		// We rethrow the exception by default.

		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;

		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		}
		// @TODO: Figure out what I should do here. Generic exception?.
		catch (DecryptionFailureException e) {
			// Secrets Manager can't decrypt the protected secret text using the provided
			// KMS key.
			// Deal with the exception here, and/or rethrow at your discretion.
			e.printStackTrace();
		} catch (InternalServiceErrorException e) {
			// An error occurred on the server side.
			// Deal with the exception here, and/or rethrow at your discretion.
			e.printStackTrace();
		} catch (InvalidParameterException e) {
			// You provided an invalid value for a parameter.
			// Deal with the exception here, and/or rethrow at your discretion.
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// You provided a parameter value that is not valid for the current state of the
			// resource.
			// Deal with the exception here, and/or rethrow at your discretion.
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// We can't find the resource that you asked for.
			// Deal with the exception here, and/or rethrow at your discretion.
			e.printStackTrace();
		}

		if (getSecretValueResult.getSecretString() != null) {
			try {
				result =  new ObjectMapper().readValue(getSecretValueResult.getSecretString(), HashMap.class);
			} catch (JsonMappingException e) {
				logger.error("ERROR :", e);
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// AWS defualt else, unsure why it is in the default. Need to check.
		//Maybe encryption not setup right?
		else {
			decodedBinarySecret = new String(
					Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
		}
	}


}
