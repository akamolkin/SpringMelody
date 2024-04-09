package ru.stepup.homework4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringMelodyApplicationTests {

	@LocalServerPort
	private Integer port;

	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
	//		.withPassword("inmemory")
	//		.withUsername("inmemory")
		.withInitScript("script.sql");

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.datasource.username", postgres::getUsername);
	}

	@Autowired
	UsersRepo usersRepo;
	@Autowired
	LoginsRepo loginsRepo;
	@Autowired
	WriteToDb writeToDb;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		usersRepo.deleteAll();
		loginsRepo.deleteAll();
	}

	@Test
	void contextLoads() {
		Map<String, List<Object>> mapCheck = new HashMap<>();

		Users users = new Users();
		users.setId(1);
		users.setUserName("ivanov");
		users.setFio("Иванов Иван Иванович");
		List<Object> list = List.of(users);
		mapCheck.put("users", list);

		Logins logins = new Logins();
		logins.setId(1);
		logins.setAccessDate("01.01.2024");
		logins.setUserId(1);
		logins.setApplication("web");
		List<Object> list1 = List.of(logins);
		mapCheck.put("logins", list1);

		writeToDb.write(mapCheck);

		RestAssured
				.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/api/users")
				.then()
				.statusCode(200)
				.body(".", hasSize(3))
		;
	}
}
