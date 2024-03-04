package org.example.ciphers.affine;

import java.util.Scanner;

public class AffineMy {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите 1 для шифрования, 2 для дешифрования и 3 для брутфорса: ");
            int choice = integerCommand();
            switch (choice) {
                case 1:
                    encryption();
                    break;
                case 2:
                    decryption();
                    break;
                case 3:
                    bruteForce();
                    break;
                default:
                    System.out.println("Нет выбранной команды");
                    break;
            }
        }
    }
    public static void encryption() {
        System.out.print("Введите текст для шифрования: ");
        String plainText = SCANNER.next();
        System.out.print("Введите мультипликативный ключ: ");
        int multiplicativeKey = integerCommand();
        System.out.print("Введите аддитивный ключ: ");
        int additiveKey = integerCommand();

        StringBuilder cypherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            cypherText.append((char)((additiveKey + multiplicativeKey * (plainText.charAt(i) - 'a')) % 26 + 'a'));
        }
        System.out.println(cypherText);
    }

    public static void decryption() {
        System.out.print("Введите текст для дешифрования: ");
        String plainText = SCANNER.next();
        System.out.print("Введите мультипликативный ключ: ");
        int multiplicativeKey = integerCommand();
        int inverseKey = modInverse(multiplicativeKey, 26);
        System.out.print("Введите аддитивный ключ: ");
        int additiveKey = integerCommand();

        StringBuilder cypherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            cypherText.append((char)((inverseKey * (plainText.charAt(i) - 'a' - additiveKey + 26)) % 26 + 'a'));
        }
        System.out.println(cypherText);
    }
    public static int integerCommand() {
        return SCANNER.nextInt();
    }
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }
    public static void bruteForce() {
        System.out.print("Введите текст для дешифрования грубой силой: ");
        String plainText = SCANNER.next();
        int count = 1;
        int[] primeNumbers = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
        for (int i = 1; i < 27; i++) {
            for (int prime : primeNumbers) {
                StringBuilder cypherText = new StringBuilder();
                int inverseKey = modInverse(prime, 26);
                for (int j = 0; j < plainText.length(); j++) {
                    cypherText.append((char)((inverseKey * (plainText.charAt(j) - 'a' - i + 26)) % 26 + 'a'));
                }
                System.out.println(String.format("%d)%s", count, cypherText));
                count++;
            }
        }
    }
}
