package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        processInput();
    }

    public static void processInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String word = sc.nextLine();
        char[] words = word.toCharArray();

        System.out.println();
        //convert to binary
        processToBinary(words);
    }

    private static void processToBinary(char[] chars){
        System.out.println("The result:");
        for (char aChar : chars) {
            String binary = String.format("%7s", Integer.toBinaryString(aChar)).replace(' ', '0');
            System.out.println(aChar + " = " + binary);
        }
    }
}