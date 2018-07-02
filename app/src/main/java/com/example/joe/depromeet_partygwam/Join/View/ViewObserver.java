package com.example.joe.depromeet_partygwam.Join.View;

import android.util.MutableBoolean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewObserver {
    private ObserverCallback callback;
    private List<AtomicBoolean> flags;

    public ViewObserver(ObserverCallback callback) {
        this.callback = callback;
        this.flags = new ArrayList<>();
    }

    public void add(AtomicBoolean flag) {
        flags.add(flag);
    }

    public void update() {
        for (AtomicBoolean b : flags) {
            if (b.get() == Boolean.FALSE) {
                callback.update(Boolean.FALSE);
                return;
            }
        }
        callback.update(Boolean.TRUE);
    }

    public void modifyValue(AtomicBoolean bool, boolean b) {
        bool.set(b);
        update();
    }
}
