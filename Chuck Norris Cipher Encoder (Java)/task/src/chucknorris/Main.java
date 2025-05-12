package chucknorris;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true){
            System.out.println("Please input operation (encode/decode/exit):");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (Objects.equals(choice, "exit")){
                System.out.println("Bye!");
                break;
            }
            if(Objects.equals(choice, "encode") || Objects.equals(choice, "decode")) {
                processInput(scanner, choice);
            }else{
                System.out.println("There is no '"+ choice + "' operation");
            }

        }

    }

    public static void processInput(Scanner scanner, String choice){
        if(Objects.equals(choice, "encode")){
            encode(scanner);
        }else {
            decode(scanner);
        }
        System.out.println();
    }

    private static void decode(Scanner scanner){
        System.out.println("Input encoded string: ");
        String encoded = scanner.nextLine(); // Read input
        if(!isValidEncoded(encoded)){
            System.out.println("Encoded string is not valid.");
        }else {
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
            System.out.println("Decoded string:");
            System.out.println(decodedMessage);
        }
    }

    private static void encode(Scanner scanner){
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
        System.out.println("Encoded string:");
        System.out.println(encoded.toString().trim());
    }


    private static void appendUnaryBlock(StringBuilder sb, char bit, int count){
        if (!sb.isEmpty()) sb.append(" ");
        sb.append(bit == '1' ? "0 " : "00 ");
        sb.append("0".repeat(count));
    }

    private static boolean isValidEncoded(String input){
        if (!input.matches("[0 ]+")) return false; // Only 0 and space allowed

        String[] parts = input.split(" ");
        if (parts.length % 2 != 0) return false; // Must have even number of blocks

        for (int i = 0; i < parts.length; i += 2) {
            if (!parts[i].equals("0") && !parts[i].equals("00")) return false; // Valid prefix only
        }

        int bitCount = 0;
        for (int i = 1; i < parts.length; i += 2) {
            bitCount += parts[i].length();
        }

        return bitCount % 7 == 0; // Full 7-bit chunks only
    }
}