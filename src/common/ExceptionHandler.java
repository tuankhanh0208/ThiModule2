package common;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ExceptionHandler {
    private final static Scanner sc = new Scanner(System.in);

    public static int checkParseInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Nhap lai");
            }
        }
    }

    public static boolean isVaildPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,11}");
    }

    public static boolean isVaildEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }
}
