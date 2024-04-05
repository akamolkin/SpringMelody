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

//		ReadFromFile readFromFile = new ReadFromFile("C:\\Temp\\Java\\");
//		Map<String, List<String>> mapRead = readFromFile.read();
//		System.out.println(mapRead);
//
//		LoadToRepo loadToRepo = new LoadToRepo();
//		Map<String, List<Object>> mapLoad = loadToRepo.load(mapRead);
//		System.out.println(mapLoad);
//
//		CheckUserName checkUserName = new CheckUserName();
//		Map<String, List<Object>> mapCheck = checkUserName.check(mapLoad);
//		System.out.println(mapCheck);
//
//		CheckDateAndLog checkDateAndLog = new CheckDateAndLog();
//		mapCheck = checkUserName.check(mapCheck);
//		System.out.println(mapCheck);
//
//		CheckUserApp checkUserApp = new CheckUserApp();
//		mapCheck = checkUserApp.check(mapCheck);
//		System.out.println(mapCheck);
//
//		WriteToDb writeToDb = new WriteToDb();
//		writeToDb.write(mapCheck);
	}

}
