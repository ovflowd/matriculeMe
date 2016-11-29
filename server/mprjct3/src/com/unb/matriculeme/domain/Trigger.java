package com.unb.matriculeme.domain;

import com.unb.matriculeme.helpers.RestClient;

public class Trigger extends Thread {
    private String data;
    private String path;

    public Trigger(String data, String path) {
        this.data = data;
        this.path = path;
    }

    @Override
    public void run() {
        RestClient.sendData(this.data, this.path);
    }
}
