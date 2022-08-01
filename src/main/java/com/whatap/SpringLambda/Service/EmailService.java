package com.whatap.SpringLambda.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.whatap.SpringLambda.Domain.Email;
import com.whatap.SpringLambda.Domain.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class EmailService {

    @Value("${aws_access_key_id}")
    private String aws_accesskey;

    @Value("${aws_secret_access_key_id}")
    private String aws_secretkey;

    private AmazonSimpleEmailService client;

    public void EmailService() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(aws_accesskey, aws_secretkey);
        client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_WEST_2).build();
    }
    public void SendEmail(Email email, Member member) throws Exception{
            EmailService();
            System.out.println(email.getTitle());
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(member.getEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(email.getBODY_TEXT())))
                    .withSubject(new Content()
                            .withCharset("UTF-8").withData(email.getTitle())))
                    .withSource(email.getSENDER())
                    .withConfigurationSetName(email.getCarbon_copy());
            client.sendEmail(request);
            System.out.println("Email sent!");
    }


}
