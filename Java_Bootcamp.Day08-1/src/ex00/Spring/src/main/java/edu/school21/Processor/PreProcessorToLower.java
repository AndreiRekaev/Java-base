package edu.school21.Processor;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String process(String message) {
        return message.toLowerCase();
    }
}
