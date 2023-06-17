package ru.faang.school.hashmap.task_5;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class WebPage {
    private String url;
    private String title;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPage = (WebPage) o;
        return url.equals(webPage.url) && title.equals(webPage.title) && content.equals(webPage.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title, content);
    }
}
