package me.manhunt.singletons;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public final class ExceptionHandler {
    private static final ExceptionHandler SINGLETON = new ExceptionHandler();

    public static ExceptionHandler getInstance() {
        return SINGLETON;
    }

    private ExceptionHandler() {}

    public String[] createExceptionReport(String message, Exception e) {
        List<String> output = new ArrayList<>();

        output.add(message + ": " + e.getMessage());

        for (StackTraceElement element : e.getStackTrace()) {
            String className = element.getClassName();
            String methodName = element.getMethodName();

            int line = element.getLineNumber();

            output.add("Class: " + className + " - Method: " + methodName + " - Line: " + line);
        }

        return output.toArray(String[]::new);
    }

    public void reportException(String[] lines) {
        for (String line : lines)
            Bukkit.getLogger().severe(line);
    }
}
