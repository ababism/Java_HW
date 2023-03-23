package edu.example.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadExample {
    public static void main(String[] args) {
        // Реализуем потокобезопасную работу программы с помощью известной нам с семинаров BlockingQueue
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(10); // создаем общий буфер размером 10

        Thread producerThread = new Thread(() -> {
            try {
                int i = 0;
                while (true) {
                    buffer.put(i++); // помещаем в буфер число
                    System.out.println("Производитель создал " + (i - 1));
                    Thread.sleep(1000); // ждем для генерации следующего числа
                }
            } catch (InterruptedException exception) {
                System.out.println("Thread_1 interrupted" + exception.getMessage());
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    int num = buffer.take(); // забираем число из буфера
                    System.out.println("Потребитель получил " + num + ": " + num * num);
                    Thread.sleep(1500); // ждем для взятия следующего числа
                }
            } catch (InterruptedException exception) {
                System.out.println("Thread_2 interrupted" + exception.getMessage());
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
