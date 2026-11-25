package laba2;

import java.util.regex.Pattern;

/**
 * Класс Phone — вся логика проверки телефонных номеров.
 * Содержит коллекцию проверенных номеров.
 */
public class Phone {
    private static final Pattern PLUS7_SIMPLE = 
            Pattern.compile("^\\+7\\d{10}$");
    
    private static final Pattern PLUS7_BRACKETS = 
            Pattern.compile("^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$");
    
    private static final Pattern EIGHT_SIMPLE = 
            Pattern.compile("^8\\d{10}$");
    
    private static final Pattern EIGHT_BRACKETS = 
            Pattern.compile("^8\\(\\d{3}\\)\\d(7)$");
    
    private static final Pattern SIX_DIGITS = 
            Pattern.compile("^\\d{6}$");
    
    private static final Pattern DASHED_PAIRS = 
            Pattern.compile("^\\d{2}-\\d{2}-\\d{2}$");
    
    private final String number;
    
    private final boolean valid;
    
    private final String format;
    
    public Phone(){
        this(null);
    }
    
    public Phone(String number) {
        this.number = (number == null) ? "": number.trim();
        
        String detected = detectFormat(this.number);
        if (detected != null){
            this.valid = true;
            this.format = detected;
        }else{
                this.valid = false;
                this.format = null;
                    }
    }   
    
    /**
    * Проверяет номер по всем шаблонам.
    * @return имя формата или null, если номер невалиден
    */
    private static String detectFormat(String number) {
        if( number == null || number.isEmpty()){
            return null;
        }
        if (PLUS7_SIMPLE.matcher(number).matches())     return "+7xxxxxxxxxx";
        if (PLUS7_BRACKETS.matcher(number).matches())   return "+7(xxx)xxx-xx-xx";
        if (EIGHT_SIMPLE.matcher(number).matches())     return "8xxxxxxxxxx";
        if (EIGHT_BRACKETS.matcher(number).matches())   return "8(xxx)xxxxxxx";
        if (SIX_DIGITS.matcher(number).matches())      return "xxxxxx";
        if (DASHED_PAIRS.matcher(number).matches())     return "xx-xx-xx";
        return null;
    }
    
    public String getNumber(){
        return number;
    }
    
    public boolean isValid(){
        return valid;
    }
    
    public String getFormat(){
        return format;
    }
    
    /**
     * Возвращает строку вида:
     *   +79161234567 - valid
     *@return number
     * 787878 - invalid
     */
    @Override
    public String toString() {
        return number + " - " + (valid ? "valid" : "invalid");
    }

    // ---------- equals/hashCode: чтобы объекты корректно сравнивались ----------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return number.equals(phone.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
