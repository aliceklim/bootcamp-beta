package ru.faang.school.hashmap.task_5;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WebPagePosition {
    WebPage webPage;
    List<Integer> keywordPosition;
}
