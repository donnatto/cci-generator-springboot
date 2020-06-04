package com.donnatto.ccigenerator;

import com.donnatto.ccigenerator.model.AccountType;
import com.donnatto.ccigenerator.model.Bank;
import com.donnatto.ccigenerator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

@SpringBootApplication
public class CciGeneratorApplication implements CommandLineRunner {

	@Autowired
	private GeneratorService service;

	public static void main(String[] args) {
		SpringApplication.run(CciGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			File input = new ClassPathResource("data/accounts.txt").getFile();
			File output = new File("./src/main/resources/data/output.txt");

			Scanner sc = new Scanner(input);
			FileWriter writer = new FileWriter(output);
			PrintWriter printWriter = new PrintWriter(writer);
			String line;

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String cci = service.generateCCI(Bank.BCP2, AccountType.AHORROS, line);
				printWriter.println(line + " - CCI -> " + cci);
			};

			printWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
