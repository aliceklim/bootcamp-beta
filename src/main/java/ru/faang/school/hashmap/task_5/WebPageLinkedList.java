package ru.faang.school.hashmap.task_5;


public class WebPageLinkedList{
    private Node head;

    private class Node{
        private WebPage webPage;
        private Node next;
        private Node previous;

        Node(WebPage webPage){
            this.webPage = webPage;
            this.next = null;
            this.previous = null;
        }
    }

    public void add(WebPage webPage){
        Node newNode = new Node(webPage);
        if (head == null){
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.previous = currentNode;
        }
    }

    public boolean contains(WebPage webPage) {
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.webPage == webPage) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    public void delete(WebPage webPage){
        Node currentNode = head;
        if (head == null){
            return;
        }
        while (currentNode != null && currentNode.webPage != webPage) {
            currentNode = currentNode.next;
        }
        if (currentNode.previous != null) {
            currentNode.previous.next = currentNode.next;
        }
        head = currentNode.next;
        if (currentNode.next != null) {
            currentNode.next.previous = currentNode.previous;
        }
    }

    public void addAll(WebPageLinkedList list) {
        Node currentNode = list.head;
        while (currentNode != null) {
            add(currentNode.webPage);
            currentNode = currentNode.next;
        }
    }

    public int getSize() {
        int count = 0;
        Node currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        return count;
    }
}

