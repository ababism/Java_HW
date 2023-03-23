package edu.example.observerExample.observers;

import edu.example.observerExample.subject.Release;

public class Player implements Observer {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void update(Release release) {
        System.out.println("Player " + name + ": Updated game - " + release.title);
        System.out.println("Achievements: " + release.achievements + '\n');
    }
}
