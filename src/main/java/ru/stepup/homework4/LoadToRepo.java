package ru.stepup.homework4;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@NoArgsConstructor
public class LoadToRepo implements ImporterDataLoader{
    @Override
    public Map<String, List<Object>> load(Map<String, List<String>> map) {
        Map<String, List<Object>> res= new HashMap<>();
        List<String> allStr = map.get("users");
        List<Object> list1 = new ArrayList<>();
        for (String str : allStr) {
            Users user = new Users();
            String[] words = str.split(";");
       //    for (String word : words) {     }
            user.setId(Integer.valueOf(words[0]));
            user.setUserName(words[1]);
            user.setFio(words[2]);
            list1.add(user);
        }
        res.put("users", list1);

        allStr = map.get("logins");
        List<Object> list2 = new ArrayList<>();
        for (String str : allStr) {
            Logins logins = new Logins();
            String[] words = str.split(";");
            //    for (String word : words) {     }
            logins.setId(Integer.valueOf(words[0]));
            logins.setAccessDate(words[1]);
            logins.setUserId(Integer.valueOf(words[2]));
            logins.setApplication(words[3]);
            list2.add(logins);
        }
        res.put("logins", list2);

        return res;
    }
}
