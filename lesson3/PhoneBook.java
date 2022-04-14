package lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
   public Map<String, List<String>> directory;

    public PhoneBook() {
        this.directory = new HashMap<>();
    }

    public void addSomething(String name, String number) {
        if (directory.containsKey(name)) {
            directory.get(name).add(number);
        } else {
            List<String> numbers = new ArrayList<>();
            numbers.add(number);
            directory.put(name, numbers);
        }
    }

    public List<String> get(String name){
        return directory.get(name);
    }

}
