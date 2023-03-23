package edu.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;
// Аннотация названа в честь самой интересной сорировки - Bogosort

/**
 * BogoField аннотация позволяет случайными целыми числами
 * заполнять поля классов в границах указанных при объявлении аннотации
 * Класс BogoHelper автоматически реализует заполнение полей класса
 * с помощью статиечского метода initializeBogoFields(obj)
 * Важно заметить, что заполняются только публичные поля
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BogoField {

    int leftBound() default 0;

    int rightBound() default 0;
}

class BogoHelper {
    static public void initializeBogoFields(Object obj) {
        Random rnd = new Random();
        for (var field : obj.getClass().getFields()) {
            BogoField bogoFieldAnnotation = field.getAnnotation(BogoField.class);
            if (field.isAnnotationPresent(BogoField.class)) {
                try {
                    field.set(obj, (rnd.nextInt(bogoFieldAnnotation.leftBound(), bogoFieldAnnotation.rightBound())));
                } catch (IllegalAccessException e) {
                    //                    throw new RuntimeException(e);
                    System.out.println("Could not access" + field + "of clown");
                }
            }
        }
    }
}
