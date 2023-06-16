package ru.faang.school.hashmap.task_5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node {
    WebPage webPage;
    Node next;
}
