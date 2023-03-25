package edu.example.serverChat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient {
    private String username;
    private Socket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Подключаемся с сервера и просим пользователя ввести имя в консоль, для передачи
            serverSocket = new Socket("localhost", 5002);
            inputStream = new DataInputStream(serverSocket.getInputStream());
            outputStream = new DataOutputStream(serverSocket.getOutputStream());

            System.out.print("Enter your name to join: ");
            String name = scanner.nextLine();
            username = name;
            outputStream.writeUTF(name);

            String welcomeMsg = inputStream.readUTF();
            System.out.println(welcomeMsg);

            // Запускаем поток для вывода сообщений с сервера
            Thread listenerThread = new Thread(new MessagePrinter(inputStream));
            listenerThread.start();
            // и сразу читаем сообщения пользователя, чтобы отправлять другим
            // если пользователь отправит exit, то сервер его выключит
            while (true) {
                String message = scanner.nextLine();
                outputStream.writeUTF(message);
            }
        } catch (IOException ioException) {
            System.out.println("Ошибка ввода-вывода в клиентском потоке пользователя: " + username + ioException.getMessage());

        } finally {
            // Закрываем подлючение к серверу
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Функция выводящие сообщения с сервера
    private static class MessagePrinter implements Runnable {
        private DataInputStream dataInputStream;

        public MessagePrinter(DataInputStream dataInputStream) {
            this.dataInputStream = dataInputStream;
        }

        // выводит сообщения с сервера
        public void run() {
            try {
                while (true) {
                    String message = dataInputStream.readUTF();
                    System.out.println(message);
                }
            } catch (IOException ioException) {
                System.out.println("Ошибка ввода-вывода в клиентском потоке при чтении с сервера: " + ioException.getMessage());
            }
        }
    }
}
