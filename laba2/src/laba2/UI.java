package laba2;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 * UI класс: приветствие, ввод/вывод, прощание
 * @author user
 */
public class UI {

    private final List<Phone> phones = new ArrayList<>();
    
    private final Scanner scanner = new Scanner(System.in);
    
    public void start(){
        printWelcome();
        
        boolean running = true;
        
        while (running){
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Error, empty string");
                continue;
            }
            if (isExit(line)) {
                running = false;
                continue;
            }

            processLine(line);            
        }
        
        printGoodbye();
        scanner.close();
    }
    private static final String[] FORMATS = {
            "+7xxxxxxxxxx",
            "+7(xxx)xxx-xx-xx",
            "8xxxxxxxxxx",
            "8(xxx)xxxxxxx",
            "xxxxxx",
            "xx-xx-xx"
    };
    
        /** Печатает приветствие и список форматов. */
    private void printWelcome() {
        System.out.println("  Laba2, Phone validator");
        System.out.println("Valid Formats:");
        for (String format : FORMATS) {
            System.out.println("  • " + format);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Type phone numbers, for example:");
        System.out.println("  +79161234567, 8(495)1234567, 12-34-56");
        System.out.println("For exit type: exit / quit ");
        System.out.println("=================================================");
    }
    
        /**
     * Разбирает строку пользователя по запятой,
     * каждый номер проверяет через Phone и печатает результат.
     */
    private void processLine(String line) {
        String[] parts = line.split(",");

        List<Phone> batch = new ArrayList<>();
        for (String raw : parts){
            String number = raw.trim();
            if (number.isEmpty()){
                continue;
            }
            Phone phone = new Phone(number);
            batch.add(phone);
            phones.add(phone);
        }
        for (Phone phone : batch){
           System.out.println(phone);
        }
        System.out.println("-------------------------------------------------");
    }

    /** Проверка команды выхода. */
    private boolean isExit(String line) {
        String s = line.toLowerCase();
        return s.equals("exit") || s.equals("quit");
    }

    /** Прощальное сообщение. */
    private void printGoodbye() {
        System.out.println("Good Bye!");
    }
    
    public List<Phone> getPhones(){
        return phones;
    }
}
