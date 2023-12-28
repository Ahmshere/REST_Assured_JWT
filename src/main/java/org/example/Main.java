package org.example;

import org.example.transport.Transport;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

      /*  int [][] a = {
                {1,26,7,67,2,1,8},
                {2,5,67,8,23,54},
                {3,6,7}};
        System.out.println("SIZE : " + a.length);
        for(int[] el:a){
            System.out.println("The line length: "+el.length);
            for(int i : el){
                System.out.println("ELEMENT : "+i);
            }

        }*/
        int numRows = 5; // Задайте желаемое количество строк
        String[][] twoDimensionalArray = createRandomStringArray(numRows, 10);

        // Вывести созданный массив
        for (String[] row : twoDimensionalArray) {
            for (String element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


//*************************************************************************

    private static String generateRandomString(int length) {
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = allowedCharacters.charAt(random.nextInt(allowedCharacters.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
    private static String[][] createRandomStringArray(int numRows, int numCols) {
        String[][] array = new String[numRows][];

        for (int i = 0; i < numRows; i++) {
              // Задайте желаемое количество столбцов
            array[i] = new String[numCols];
            for (int j = 0; j < numCols; j++) {
                array[i][j] = generateRandomString(10); // Здесь 10 - длина случайной строки
            }
        }

        return array;
    }


}