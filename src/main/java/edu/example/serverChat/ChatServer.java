package edu.example.serverChat;

import java.util.*;
import java.net.*;
import java.io.*;

public class ChatServer {
    // Потокобезопасный динамический массив для работы с клиентами
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5002);
        System.out.println("Chat server started on port 5002.");

        while (true) {
            Socket client = server.accept();
            System.out.println("Подключился новый клиент из " + client.getInetAddress().getHostAddress());

            DataInputStream userInput = new DataInputStream(client.getInputStream());
            DataOutputStream userOutput = new DataOutputStream(client.getOutputStream());

            ClientHandler handler = new ClientHandler(client, userInput, userOutput);
            clients.add(handler);
            // передаем потоку Runnable
            Thread thread = new Thread(handler);
            // Запускаем поток (функцию run() в клиенте)
            thread.start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private String username;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientHandler(Socket clientSocket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.clientSocket = clientSocket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    // Функция которая потокобезопасно пишет всем клиентам сообщение входящее  в чат
    private void broadcast(String message, Socket clientToExclude) throws IOException {
        synchronized (ChatServer.clients) {
            for (ClientHandler client : ChatServer.clients) {
                // Убеждаемся, что мы не отправляем тому, кому не надо (себе же)
                if (client.clientSocket != clientToExclude) {
                    client.outputStream.writeUTF(message);
                }
            }
        }
    }

    public void run() {
        try {
            // Получаем имя пользователя
            this.username = inputStream.readUTF().trim();
            System.out.println("New member logged in as: " + this.username);

            // Привествуем пользователя, чтобы он видел, что вошел в чат
            outputStream.writeUTF("---Welcome to the chat, " + this.username + "!---");

            // Сообщаем, что пользователь вошел в чат
            String message = '[' + this.username + " has joined the chat]";
            broadcast(message, null);

            // читаем что присылает клиент (из текущего потока)
            while (true) {
                String received = inputStream.readUTF();

                // Критерий выхода как в примере на семинаре
                if (received.equalsIgnoreCase("exit")) {
                    message = '[' + this.username + " has left the chat]";
                    broadcast(message, this.clientSocket);
                    this.clientSocket.close();
                    break;
                }

                // Расслылаем сообщение
                message = "[" + this.username + "]: " + received;
                broadcast(message, this.clientSocket);
            }
        } catch (IOException ioException) {
            System.out.println("Ошибка ввода-вывода в серверном потоке клиента: " + username + ioException.getMessage());
        }
    }
}
