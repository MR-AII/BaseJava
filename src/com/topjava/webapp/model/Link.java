package com.topjava.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String title;
    private final String url;

    public Link(String title, String url) {
        Objects.requireNonNull(title, "title must be not null");
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return title.equals(link.title) && Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }
}