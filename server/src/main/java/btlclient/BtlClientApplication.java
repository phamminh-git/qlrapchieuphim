package btlclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import btlclient.controller.HomeController;

@SpringBootApplication
public class BtlClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlClientApplication.class, args);
	}

}
