package edu.example.observerExample.observers;

import edu.example.observerExample.subject.Release;

public interface Observer {
    void update(Release release);
}
