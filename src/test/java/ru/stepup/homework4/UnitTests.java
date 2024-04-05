package ru.stepup.homework4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class UnitTests {

    @Test
    void testReadFromFile() {
        ReadFromFile readFromFile = new ReadFromFile("C:\\Java\\");
        Map<String, List<String>> mapRead = readFromFile.read();
        System.out.println(mapRead);
        Assertions.assertEquals(mapRead.containsKey("users"), true);
        Assertions.assertEquals(mapRead.containsKey("logins"), true);

        List<String> stringList = mapRead.get("users");
        Assertions.assertEquals(!stringList.isEmpty(), true);
    }

    @Test
    void testLoadToRepo() {
        LoadToRepo loadToRepo = new LoadToRepo();
        Map<String, List<String>> mapRead = new HashMap<>();
        Integer id = 1;
        String name = "ivanov";
        String fio = "Иванов петр Сергеевич";
        List<String> usersList = List.of(id + ";" + name + ";" + fio);
        List<String> loginsList = List.of("1;01.02.2024;1;web");
        mapRead.put("users", usersList);
        mapRead.put("logins", loginsList);

        Map<String, List<Object>> mapLoad = loadToRepo.load(mapRead);
        System.out.println(mapLoad);
        Assertions.assertEquals(mapLoad.containsKey("users"), true);
        Assertions.assertEquals(mapLoad.containsKey("logins"), true);

        List<Object> listObj = mapLoad.get("users");
        for (Object obj: listObj) {
            Users user = (Users) obj;
            Assertions.assertEquals(user.getId(), id);
            Assertions.assertEquals(user.getUserName(), name);
            Assertions.assertEquals(user.getFio(), fio);
        }
    }

    @Test
    void testCheckUserName() {
        Map<String, List<Object>> mapCheck = new HashMap<>();

        Users users = new Users();
        String nameUnchecked = "иванов иван иванович";
        String nameChecked = "Иванов Иван Иванович";
        users.setId(1);
        users.setUserName("ivanov");
        users.setFio(nameUnchecked);
        List<Object> list = List.of(users);
        mapCheck.put("users", list);
        CheckUserName checkUserName = new CheckUserName();
        mapCheck = checkUserName.check(mapCheck);
        System.out.println(mapCheck);

        list = mapCheck.get("users");
        for (Object obj: list) {
            Users user = (Users) obj;
            Assertions.assertEquals(user.getFio(), nameChecked);
        }
    }

    @Test
    void testCheckUserApp() {
        Map<String, List<Object>> mapCheck = new HashMap<>();

        Logins logins = new Logins();
        logins.setId(1);
        logins.setAccessDate("01.01.2024");
        logins.setUserId(1);
        String uncheckedApp = "wrongapp";
        String checkedApp = "other:" + uncheckedApp;
        logins.setApplication(uncheckedApp);

        List<Object> list = List.of(logins);
        mapCheck.put("logins", list);
        CheckUserApp checkUserApp = new CheckUserApp();
        mapCheck = checkUserApp.check(mapCheck);
        System.out.println(mapCheck);

        list = mapCheck.get("logins");
        for (Object obj: list) {
            Logins logins1 = (Logins) obj;
            Assertions.assertEquals(logins1.getApplication(), checkedApp);
        }
    }

    @Test
    void testCheckDateAndLog() {
        Map<String, List<Object>> mapCheck = new HashMap<>();

        Users users = new Users();
        users.setId(1);
        users.setUserName("ivanov");
        users.setFio("Иванов Иван Иванович");
        List<Object> list = List.of(users);
        mapCheck.put("users", list);

        Logins logins = new Logins();
        logins.setId(1);
        //logins.setAccessDate("01.01.2024");
        logins.setUserId(1);
        logins.setApplication("web");
        List<Object> list1 = List.of(logins);
        mapCheck.put("logins", list1);

        CheckDateAndLog checkDateAndLog = new CheckDateAndLog();
        mapCheck = checkDateAndLog.check(mapCheck);
        System.out.println(mapCheck);

        list = mapCheck.get("logins");
        Assertions.assertTrue(list.isEmpty());

        list = mapCheck.get("users");
        Assertions.assertTrue(list.isEmpty());
    }

}