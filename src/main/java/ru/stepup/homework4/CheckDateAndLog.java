package ru.stepup.homework4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(3)
@LogTransformation
public class CheckDateAndLog implements ImporterDataChecker{
    @Override
    public Map<String, List<Object>> check(Map<String, List<Object>> map) {
        List<Object> loginsList = new ArrayList<>();
        List<Object> listObj = map.get("logins");
        Set<Integer> idToDel = new HashSet<>();
        for (Object obj: listObj) {
            Logins logins = (Logins) obj;
            String accDate = logins.getAccessDate();
            if (accDate == null || accDate.isEmpty()) {
                idToDel.add(logins.getUserId());
                continue;
            }
            loginsList.add(logins);
        }
        map.put("logins", loginsList);

        if (!idToDel.isEmpty()) {
            List<Object> usersList = new ArrayList<>();
            listObj = map.get("users");
            for (Object obj: listObj) {
                Users users = (Users) obj;
                Integer id = users.getId();
                if (idToDel.contains(id)) {
                    System.out.println("Пустая дата, объект с ID "+ users.getId() +" удален!");
                    continue;
                }
                usersList.add(users);
            }
            map.put("users", usersList);
        }

        return map;
    }
}