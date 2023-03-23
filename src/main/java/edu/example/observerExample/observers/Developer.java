package edu.example.observerExample.observers;

import edu.example.observerExample.subject.Release;

public class Developer implements Observer {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    @Override
    public void update(Release release) {
        System.out.println("Developer " + name + ": New game released - " + release.title);
        System.out.println("Technical details: " + release.techDetails);
    }
}
