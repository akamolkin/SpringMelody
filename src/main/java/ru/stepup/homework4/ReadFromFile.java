package ru.stepup.homework4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Component
@LogTransformation
public class ReadFromFile implements ImporterDataReader{
    private String folderPath;

    public ReadFromFile(@Value("${spring.application.pathfolder}")String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    @Override
    public Map<String, List<String>> read() {
        File folder = new File(getFolderPath());
        File[] listOfFiles = folder.listFiles();
        Map<String, List<String>> res = new HashMap<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                System.out.println(fileName);
                List<String> allStr = new ArrayList<>();
                try {
                    Scanner sc = new Scanner(file);
                //    int c = 0;

                    while (sc.hasNextLine()) {
                        allStr.add(sc.nextLine());
                //        c++;
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                res.put(fileName.substring(0, fileName.length() - 4), allStr);
            }
        }
        return  res;
    }
}
