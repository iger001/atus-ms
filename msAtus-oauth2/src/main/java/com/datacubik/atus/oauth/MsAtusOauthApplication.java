package com.datacubik.atus.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@SpringBootApplication
public class MsAtusOauthApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MsAtusOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i=1; i<=5; i++) {
			//String password = "Pa$$word0"+i;
			String password = "algun_codigo_secreto_qwerty_pa_validar_token";
			String bcr = passwordEncoder.encode(password);
			System.out.println(password +" : " + bcr);
		}
	}

}
