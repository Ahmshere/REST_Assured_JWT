package org.example.transport;

import java.util.ArrayList;
import java.util.Arrays;

public class Run {

    public static void main(String[] args){

         Transport tr0 = new Transport("TR97489397397", "BMW", 2000, 15000);
         Transport tr1 = new Transport("TR97828247397", "BMW", 2000, 15000);

         System.out.println("Static COUNT : "+ Transport.count);

        // evenOdd(121);



      /*  for (int i = 0 ; i <30; i++){
            if(i==10){continue;}
            System.out.println("NUMBER : "+i);
        }
*/

        // VARARGS
     //   summa(123,4,67,54,5,7,78);
       // StringBuilder sb = new StringBuilder();
     //   int[] array = {123,5,245,5673,13,5,3,5,43,4,67,78,8};

     //   sortArrayInt(array);

    }
public static int summa(int ... x){
        int summa = 0;
        for(int i:x){
            summa += i;
        }
    System.out.println("SUMMA : " + summa);
        return summa;
}
    public static int[] sortArrayInt(int[] array) {
        for (int e: array) {
            System.out.println("BEFORE_REESULT : "+e);
        }
        int a;
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    index = j;
                }
            }
            if (i != index) {
                a = array[i];
                array[i] = min;
                array[index] = a;
            }
        }
        for (int e: array) {
            System.out.println("REESULT : "+e);
        }

        return array;
    }


    public static int searchElementInString(String str , String element){
       int res =  str.indexOf(element);
        System.out.println("***** INDEX - " + res);
        System.out.println("SUB_STRING _ "+str.substring(res));
       return res;

    }
    public static void evenOdd(int number){

        int res = number%2;
        switch (res) {

            case 0:
                System.out.println("***** EVEN !");
                break;
            case 1:
                System.out.println("***** ODD !");
                break;
            default:
                System.out.println("SMTHINGS WRONG !!!!");
        }
    }

}
