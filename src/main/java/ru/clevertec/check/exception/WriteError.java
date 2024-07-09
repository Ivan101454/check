package ru.clevertec.check.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteError {
    private static Path output = Path.of("result.csv");
    private Exception error;
    private String errorOut;
    public WriteError(Exception error) {
        this.error = error;
    }
    public void setError() {
        this.errorOut = """
                ERROR
                %s
                """.formatted(error.getMessage());
    }

    public void writeFile() throws IOException {
        setError();
        Files.writeString(output, errorOut);
    }

    public static void setOutput(String output) {
        WriteError.output = Path.of(output);
    }
}
