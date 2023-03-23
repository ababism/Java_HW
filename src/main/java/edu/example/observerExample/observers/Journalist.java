package edu.example.observerExample.observers;

import edu.example.observerExample.subject.Release;

public class Journalist implements Observer {
    private String name;

    public Journalist(String name) {
        this.name = name;
    }

    @Override
    public void update(Release release) {
        System.out.println("Journalist " + name + ": New game available - " + release.title);
        System.out.println("Summary: " + release.releaseSummary);
    }
}
