package lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PhoneBook pb=new PhoneBook();
        pb.addSomething("Ivanov","921345");
        pb.addSomething("Petrov","930312");
        pb.addSomething("Ivanov","9212315");
        pb.addSomething("Sidorov","9523324");
        pb.addSomething("Petrov","999675");
        System.out.println(pb.directory);

        String[]cars={"car","exhoust","engine","piston","suspension","wheel","nut","piston","gas","wheel","nut","engine"};

        Map<String,Integer>count=new HashMap<>();

        for (int i = 0; i < cars.length; i++) {
            if (count.containsKey(cars[i]))
                count.put(cars[i],count.get(cars[i])+1);
                else
                    count.put(cars[i],1);
        }
        System.out.println(count);
    }


    }
