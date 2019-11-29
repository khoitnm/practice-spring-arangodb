package org.tnmk.practicespringarangodb.pro03enumfield.sample.entity;

public enum ContentType {
    IMAGE("IMG"), TEXT("TXT"), VIDEO("VIDEO");
    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
