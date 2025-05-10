package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        processInput();
    }

    public static void processInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string: ");
        String input = scanner.nextLine(); // Read input

        // Convert string to 7-bit binary representation
        StringBuilder binaryBuilder = new StringBuilder();
        for (char ch : input.toCharArray()) {
            binaryBuilder.append(String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0'));
        }

        String binary = binaryBuilder.toString();

        // Apply Chuck Norris encoding
        StringBuilder encoded = new StringBuilder();

        char currentBit = binary.charAt(0);
        int count = 1;

        for (int i = 1; i < binary.length(); i++) {
            if (binary.charAt(i) == currentBit) {
                count++;
            } else {
                appendUnaryBlock(encoded, currentBit, count);
                currentBit = binary.charAt(i);
                count = 1;
            }
        }

        // Append the final block
        appendUnaryBlock(encoded, currentBit, count);

        // Print the result
        System.out.println("The result: ");
        System.out.println(encoded.toString().trim());

    }





    private static void appendUnaryBlock(StringBuilder sb, char bit, int count){
        if (!sb.isEmpty()) sb.append(" ");
        sb.append(bit == '1' ? "0 " : "00 ");
        sb.append("0".repeat(count));
    }
}