package ru.stepup.homework4;

import java.util.List;
import java.util.Map;

public interface ImporterDataChecker {
    public Map<String, List<Object>> check(Map<String, List<Object>> map);
}
