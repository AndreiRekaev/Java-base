package edu.school21.Program;

import edu.school21.Printer.Printer;
import edu.school21.Printer.PrinterWithPrefixImpl;
import edu.school21.Processor.PreProcessor;
import edu.school21.Processor.PreProcessorToUpperImpl;
import edu.school21.Renderer.Renderer;
import edu.school21.Renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        System.out.println("Common method:");
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println("Spring beans methods:");
        Printer prefixPrinter = context.getBean("printerPref1", Printer.class);
        prefixPrinter.print("Hello!");

        prefixPrinter = context.getBean("printerPref2", Printer.class);
        prefixPrinter.print("Hello!");

        prefixPrinter = context.getBean("printerPref3", Printer.class);
        prefixPrinter.print("Hello!");

        prefixPrinter = context.getBean("printerPref4", Printer.class);
        prefixPrinter.print("Hello!");

        Printer datePrinter = context.getBean("printerDate1", Printer.class);
        datePrinter.print("Hello!");

        datePrinter = context.getBean("printerDate2", Printer.class);
        datePrinter.print("Hello!");

        datePrinter = context.getBean("printerDate3", Printer.class);
        datePrinter.print("Hello!");

        datePrinter = context.getBean("printerDate4", Printer.class);
        datePrinter.print("Hello!");
    }
}