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

        //print out all characters
        for (char c: words){
            System.out.print(c + " ");
        }
    }
}