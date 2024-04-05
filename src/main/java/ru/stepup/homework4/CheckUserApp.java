package ru.stepup.homework4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Order(2)
@LogTransformation
public class CheckUserApp implements ImporterDataChecker{
    @Override
    public Map<String, List<Object>> check(Map<String, List<Object>> map) {
        List<Object> resList = new ArrayList<>();
        List<Object> listObj = map.get("logins");
        for (Object obj: listObj) {
            Logins logins = (Logins) obj;
            String app = logins.getApplication();
            if (!Objects.equals(app, "web") && !Objects.equals(app, "mobile")) {
                System.out.println("Другое приложение: "+ app);
                app = "other:" + app;
                logins.setApplication(app);
            }
            resList.add(logins);
        }
        map.put("logins", resList);

        return map;


    }
}
