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

        chuckNorrisBinaryConvert(input);

    }

    private static void chuckNorrisBinaryConvert(String encoded){
        String[] blocks = encoded.split(" ");
        StringBuilder binaryBuilder = new StringBuilder();

        // Step 1: Convert Chuck Norris blocks back to binary
        for (int i = 0; i < blocks.length; i += 2) {
            String prefix = blocks[i];         // "0" or "00"
            String zeros = blocks[i + 1];      // zero block

            char bit = prefix.equals("0") ? '1' : '0';
            binaryBuilder.append(String.valueOf(bit).repeat(zeros.length()));
        }

        // Step 2: Split binary into 7-bit chunks and convert to characters
        StringBuilder decodedMessage = new StringBuilder();
        String binaryString = binaryBuilder.toString();

        for (int i = 0; i < binaryString.length(); i += 7) {
            String chunk = binaryString.substring(i, i + 7);
            int ascii = Integer.parseInt(chunk, 2);
            decodedMessage.append((char) ascii);
        }

        // Print result
        System.out.println("The result: ");
        System.out.println(decodedMessage);
    }

    private static void chuckNorrisBinary(String input){
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