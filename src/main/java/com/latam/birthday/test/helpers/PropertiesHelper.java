package com.latam.birthday.test.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public final class PropertiesHelper {

    @Value("${birthday.service.poem.host}")
    private String poemHost;

    @Value("${birthday.service.poem.port}")
    private String poemPort;

    @Value("${birthday.service.poem.path}")
    private String poemPath;

    @Value("${rest.retries}")
    private int retries;

    public String getPoemHost() {
        return poemHost;
    }

    public void setPoemHost(String poemHost) {
        this.poemHost = poemHost;
    }

    public String getPoemPort() {
        return poemPort;
    }

    public void setPoemPort(String poemPort) {
        this.poemPort = poemPort;
    }

    public String getPoemPath() {
        return poemPath;
    }

    public void setPoemPath(String poemPath) {
        this.poemPath = poemPath;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
