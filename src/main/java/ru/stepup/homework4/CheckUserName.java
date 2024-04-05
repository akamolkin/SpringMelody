package ru.stepup.homework4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@LogTransformation
public class CheckUserName implements ImporterDataChecker{
    @Override
    public Map<String, List<Object>> check(Map<String, List<Object>> map) {
   //     Map<String, List<Object>> res= new HashMap<>();

        List<Object> resList = new ArrayList<>();
        List<Object> listObj = map.get("users");
        for (Object obj: listObj) {
            Users user = (Users) obj;
            String fio = user.getFio();
          //  if (fio == null || fio.isEmpty()) continue;
            String[] words = fio.split(" ");
            fio = "";
            for (String word : words) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
                fio += " " + word;
            }
            user.setFio(fio.trim());
            resList.add(user);
        }
        map.put("users", resList);

        return map;
    }
}
