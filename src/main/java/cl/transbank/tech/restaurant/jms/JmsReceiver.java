package cl.transbank.tech.restaurant.jms;

import java.util.Date;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

	  @JmsListener(destination = "DateQueue", containerFactory = "myFactory")
	  public void receiveMessage(Date date) {
	    System.out.println("Received <" + date + ">");
	  }
}
