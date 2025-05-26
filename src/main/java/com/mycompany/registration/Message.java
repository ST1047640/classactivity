/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Message {
    private static int totalMessagesSent = 0;
    private static int messageCounter = 0;
    private static final JSONArray storedMessages = new JSONArray();
    public static boolean checkMessageID(String id) {
        return id.length() == 10;
    }

    public static boolean checkRecipientCell(String number) {
        return number.length() <= 10 && number.startsWith("+");
    }

    public static String createMessageHash(String messageId, int msgNum, String message) {
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : "";
        return (messageId.substring(0, 2) + ":" + msgNum + ":" + firstWord + lastWord).toUpperCase();
    }
public static String sendMessage(String message, String recipient, String messageId) {
        int choice = JOptionPane.showOptionDialog(null,
                "Choose an option", "Send Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Send", "Disregard", "Store"}, "Send");

        switch (choice) {
            case 0 -> {
                totalMessagesSent++;
                return "Message successfully sent.";
            }
            case 1 -> {
                return "Press 0 to delete message.";
            }
            case 2 -> {
                storeMessage(messageId, recipient, message);
                return "Message successfully stored.";
            }
            default -> {
                return "No action taken.";
            }
        }
    }
public static void storeMessage(String messageId, String recipient, String message) {
        JSONObject msgObj = new JSONObject();
        msgObj.put("MessageID", messageId);
        msgObj.put("Recipient", recipient);
        msgObj.put("Message", message);
        storedMessages.add(msgObj);

        try (FileWriter file = new FileWriter("stored_messages.json")) {
            file.write(storedMessages.toJSONString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to store message: " + e.getMessage());
        }
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    public static void startMessaging() {
        String username = JOptionPane.showInputDialog("Please enter your username to login:");
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Login failed. Username is required.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        int numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));
        for (int i = 0; i < numMessages; i++) {
            messageCounter++;

String messageId = String.valueOf(1000000000L + new Random().nextInt(900000000));
            if (!checkMessageID(messageId)) {
                JOptionPane.showMessageDialog(null, "Message ID invalid.");
                continue;
            }

            String recipient = JOptionPane.showInputDialog("Enter recipient number (e.g., +27718693002):");
            if (!checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null,
                        "Cell phone number is incorrectly formatted or does not contain an international code.");
                continue;
            }

            String message = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null,
                        "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce size.");
                continue;
            }
String hash = createMessageHash(messageId, messageCounter, message);

            String result = sendMessage(message, recipient, messageId);

            JOptionPane.showMessageDialog(null, """
                                                Message Details:
                                                Message ID: """ + messageId + "\n"
                    + "Message Hash: " + hash + "\n"
                    + "Recipient: " + recipient + "\n"
                    + "Message: " + message + "\n"
                    + "Result: " + result);
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
    }
}
    
