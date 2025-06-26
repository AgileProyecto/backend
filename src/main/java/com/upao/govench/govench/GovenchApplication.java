package com.upao.govench.govench;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GovenchApplication {

    public static void main(String[] args) {
        System.setProperty("PAYPAL_CLIENT_ID", "AUiihcPL0NMt8XKbmxG3jeJh3-vOrU-X7uhCiOBcmhXK9zTmVi4DklCr772xFN9mkFvQ_88FYIVjmL3E");
        System.setProperty("PAYPAL_CLIENT_SECRET", "EBxXcdwdC2R7qFuB0XdmqmFb5dIpDr4vJZLXXGb6v18ZZfhUsGh5ioz7f0_kGJC4r_4AKtcUiMIQocDg");
        SpringApplication.run(GovenchApplication.class, args);
    }

}
