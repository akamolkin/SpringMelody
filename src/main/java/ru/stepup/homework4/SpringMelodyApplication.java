package ru.stepup.homework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringMelodyApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringMelodyApplication.class, args);

		Importer importer = ctx.getBean(Importer.class);
		importer.perform();
	}

}
