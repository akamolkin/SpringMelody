package ru.stepup.homework4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Importer {
    @Autowired
    ImporterDataReader dataReader;
    @Autowired
    ImporterDataLoader dataLoader;
    @Autowired
    ImporterDataWriter dataWriter;
    @Autowired
    public List<ImporterDataChecker> checkers;
    public void perform() {
        Map<String, List<String>> fileData = dataReader.read();
        Map<String, List<Object>> repoData = dataLoader.load(fileData);

        for (ImporterDataChecker chk : checkers) {
            repoData = chk.check(repoData);
        }
        dataWriter.write(repoData);
    }
}
