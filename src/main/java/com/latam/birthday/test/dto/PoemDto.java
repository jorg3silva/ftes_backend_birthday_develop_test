package com.latam.birthday.test.dto;

public class PoemDto {

    private String title;
    private String content;
    private String url;
    private PoetDto poet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PoetDto getPoet() {
        return poet;
    }

    public void setPoet(PoetDto poet) {
        this.poet = poet;
    }
}
