package lesson2;

import java.util.Arrays;

public class DoArray {
    public void mass(String[][] arr) {
        if (arr.length != 4) {
            throw new MyArraySizeException("Размер не соответствует заданному");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("Размер не соответствует заданному");
            }
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    System.out.println(Arrays.deepToString(new String[]{arr[i][j]})); // IDEA подсказала с выводом нужного значения))
                    throw new MyArrayDataException("В массиве содержится не верный символ");
                }
            }
        }
        System.out.println("Общая сумма массива "+sum);
    }
}
