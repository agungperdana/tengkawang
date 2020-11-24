package com.kratonsolution.belian.tengkawang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Vector;

@SpringBootApplication
public class EntryPoint {
	
	public static Vector<String> memory = new Vector<>();
	
	public static void main(String[] args) {
		SpringApplication.run(EntryPoint.class, args);
	}
}
