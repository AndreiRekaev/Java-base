package edu.school21.Processor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String message) {
        return message.toUpperCase();
    }
}