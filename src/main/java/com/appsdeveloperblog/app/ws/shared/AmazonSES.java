package com.appsdeveloperblog.app.ws.shared;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

public class AmazonSES {
	// This address must be verified with Amazon SES.
	final String FROM = "sergey.kargopolov@swiftdeveloperblog.com";

	// The subject line for the email.
	final String SUBJECT = "One last step to complete your registration with PhotoApp";

	// The HTML body for the email.
	final String HTMLBODY = "<h1>Please verify your email address</h1>"
			+ "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " click on the following link: "
			+ "<a href='http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue'>"
			+ "Final step to complete your registration" + "</a><br/><br/>"
			+ "Thank you! And we are waiting for you inside!";

	// The email body for recipients with non-HTML email clients.
	final String TEXTBODY = "Please verify your email address. "
			+ "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
			+ " open then the following URL in your browser window: "
			+ " http://ec2-35-173-238-100.compute-1.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue"
			+ " Thank you! And we are waiting for you inside!";

	public void verifyEmail(UserDto userDto) {

		// You can also set your keys this way. And it will work!
		//System.setProperty("aws.accessKeyId", "<YOUR KEY ID HERE>"); 
		//System.setProperty("aws.secretKey", "<SECRET KEY HERE>"); 
		
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.build();
 
		String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());

		SendEmailRequest request = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(userDto.getEmail()))
				.withMessage(new Message()
						.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
								.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
						.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
				.withSource(FROM);

		client.sendEmail(request);

		System.out.println("Email sent!");

	}
}
