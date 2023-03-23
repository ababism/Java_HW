package edu.example.annotations;

import java.util.List;
import java.util.Random;

class Clown {
    // С помощью аннотации задаем границы случайного заполнения
    @BogoField(leftBound = 0, rightBound = 10)
    public int clownNumber;
    @BogoField(leftBound = 160, rightBound = 175)
    public int clownHeight = 0;
    // Так как это поле приватное, то оно не заполнится случано
    @BogoField(leftBound = 100, rightBound = 300)
    private int clownBalance;

    public Clown() {
        var rnd = new Random();
        // Инициализируем поля используя аннотацию
        BogoHelper.initializeBogoFields(this);
    }

    public Clown(int clownNumber, int clownHeight, int clownBalance) {
        this.clownNumber = clownNumber;
        this.clownHeight = clownHeight;
        this.clownBalance = clownBalance;
    }

    @Override
    public String toString() {
        return "Clown " + clownNumber +
                ", clownHeight: " + clownHeight +
                ", clownBalance: " + clownBalance +
                '\n';
    }
}

public class AnnotationsMain {
    public static void main(String[] args) {
        Clown clown = new Clown(1, 2, 3);
        List<Clown> clownList = new java.util.ArrayList<>(List.of(clown));
        for (int i = 0; i < 10; ++i) {
            clownList.add(new Clown());
        }
        for (var someOne : clownList) {
            System.out.print(someOne);
        }
    }
}
