package com.example.gcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.gcp.bean.InfraConfig;

@RestController
@SpringBootApplication
public class GcppubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcppubApplication.class, args);
	}
	
	@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
    public interface PubsubOutboundGateway {

            void sendToPubsub(String text);
    }
	@Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
            return new PubSubMessageHandler(pubsubTemplate, "MyPubSub");
    }
	
	@Autowired
    private PubsubOutboundGateway messagingGateway;

    @PostMapping("/postMessage")
    public RedirectView postMessage(@RequestParam("message") String message) {
            this.messagingGateway.sendToPubsub(message);
            return new RedirectView("/");
    }
    
    @PostMapping("/infraconfig")
    public ResponseEntity<Object> postConfig(@RequestBody InfraConfig infraConfig)
    {
    	System.out.println("infraconfig message: "+infraConfig.toString());
    	this.messagingGateway.sendToPubsub(infraConfig.toString());
    	return new ResponseEntity<>(HttpStatus.OK);
    }

}
