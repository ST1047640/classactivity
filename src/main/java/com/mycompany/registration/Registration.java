/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Registration {
    private String Username;
    private String Cellphone;
    private String Password;
    //Creating checkusername method
    public void CheckUserName() {
        boolean isValid = false;
        while (!isValid) {
            Username = JOptionPane.showInputDialog("Enter user name");
            boolean Underscore = Username.contains("_");
            if (Underscore == true && Username.length()<= 5) {
                JOptionPane.showInternalMessageDialog(null, "Username succesfully captured");
                isValid = true;
            }else{
                JOptionPane.showInternalMessageDialog(null, "Username is Incorrectly formated" 
                                    + "Please ensure that Username" 
                                    + "_ contains underscore and no" 
                                    + "_more than five characters in lenght");
            }
        }
    }
    //Method to check password
    public boolean checkpassword( String password) {
        boolean hasUppercase = false ,hasDigit= false, hasSpecialChar=false;
        //define special character
        String SpecialChar = ("~!@$%^&*()-_=+[]}{><");
        //Check each character in password
        for(char ch:password.toCharArray()) {
            if(Character.isUpperCase(ch)) hasUppercase=true;
            if(Character.isDigit(ch))hasDigit=true;
            if(SpecialChar.contains(String.valueOf(ch))) hasSpecialChar = true;
        }
        //Validate all conditiond
        return password.length()>=8 && hasUppercase && hasDigit && hasSpecialChar;
    }
    //Creating a method called Registeruser
    public void RegisterUser() {
        CheckUserName();
        boolean validPassword = false;
        while (!validPassword) {
            Password = JOptionPane.showInputDialog("Enter user password");
            if (checkpassword(Password)) {
                JOptionPane.showInternalMessageDialog(null, "Password successfully captured");
                validPassword = true;
            } else {
                JOptionPane.showInternalMessageDialog(null, 
                    "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character");
            }
        }
        Cellphone = JOptionPane.showInputDialog("Enter cellphone number");
    }

    public static void main(String[] args) {
        Registration userRegistration = new Registration();
        userRegistration.RegisterUser();
    }
}