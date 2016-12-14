package com.unb.matriculeme.domain;

import com.unb.matriculeme.helpers.RestClient;

public class Trigger extends Thread {
    private String data;
    private String path;
    private boolean isDavid;

    public Trigger(String data, String path, boolean isDavid) {
        this.data = data; 
        this.path = path;
        this.isDavid = isDavid; 
    }

    @Override
    public void run() {
        RestClient.sendData(this.data, this.path, this.isDavid);
    }
}