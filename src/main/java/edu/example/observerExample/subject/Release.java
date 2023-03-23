package edu.example.observerExample.subject;

import edu.example.observerExample.observers.Observer;

import java.util.ArrayList;
import java.util.List;

// Subject
public class Release {
    List<Observer> observers = new ArrayList<>();
    public String title;
    public String releaseSummary;
    public String techDetails;
    public List<String> achievements = new ArrayList<>();

    public Release(String title, String releaseSummary, String techDetails) {
        this.title = title;
        this.releaseSummary = releaseSummary;
        this.techDetails = techDetails;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void makeAchievement(String achievement) {
        achievements.add(achievement);
        notifyObservers();
    }
}
