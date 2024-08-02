/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author hbtth
 */
public class Validate {

    // check valid username(length >=6)
    public static boolean checkUsername(String username) {
        return username.length() >= 6 && regexUsername(username);
    }

    public static boolean checkAge(String age) {
        Date now = new Date();
        int year = Integer.parseInt(age);
        // user's age must be greater than 13
        return now.getYear() - year + 1900 > 13;
    }

    public static boolean checkPassword(String password) {
        return password.length() >= 8 && regexPassword(password);
    }

    // email matches one or more alphanumeric characters before the @ symbol. and have at least 1 domain
    public static boolean checkEmail(String email) {
        return email.matches("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*$");
    }

    // username must be alphanumeric and doesn't contain a speacial character
    private static boolean regexUsername(String username) {
        return username.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }

    // to check password must be between 8 and 31 character and must be alphanumeric and contain at least 1 special character
    private static boolean regexPassword(String password) {
        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{8,31}$");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            if (checkEmail(username)) {

                if (checkAge(username)) {

                    System.out.println("ok");
                } else {
                    System.out.println("not ok");
                }
            }
        }
    }
}
