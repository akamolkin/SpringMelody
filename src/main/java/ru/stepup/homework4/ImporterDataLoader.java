package ru.stepup.homework4;

import java.util.List;
import java.util.Map;

public interface ImporterDataLoader {
    public Map<String, List<Object>> load(Map<String, List<String>> map);
}
