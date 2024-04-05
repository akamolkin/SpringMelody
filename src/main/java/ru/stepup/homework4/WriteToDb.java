package ru.stepup.homework4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@LogTransformation
public class WriteToDb implements ImporterDataWriter{
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    LoginsRepo loginsRepo;
    @Override
    public void write(Map<String, List<Object>> map) {
        List<Object> listObj = map.get("users");
       // UsersRepo usersRepo = null;
        for (Object object: listObj) {
            Users users = (Users) object;
       //     UsersRepo usersRepo = (UsersRepo) users;
            usersRepo.save(users);
        }

        listObj = map.get("logins");
       // LoginsRepo loginsRepo = null;
        for (Object object: listObj) {
            Logins logins = (Logins) object;
       //     LoginsRepo loginsRepo = (LoginsRepo) logins;
            loginsRepo.save(logins);
        }

//        List<Users> usersList = List.of((Users) listObj);
//
//        UsersRepo usersRepo = null;
//        usersRepo.saveAll(usersList);
//
//        listObj = map.get("logins");
//        List<Logins> loginsList = List.of((Logins) listObj);
//
//        LoginsRepo loginsRepo = null;
//        loginsRepo.saveAll(loginsList);


    }
}
