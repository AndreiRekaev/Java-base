package edu.school21.Printer;

import edu.school21.Renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.renderer(LocalDateTime.now().format(FORMATTER) + " " + message);
    }
}
