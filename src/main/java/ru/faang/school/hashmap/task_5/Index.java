package ru.faang.school.hashmap.task_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Index {
    private static Map<String, WebPageLinkedList> searchMap = new HashMap<>();
    private static Set<WebPage> existingPages = new HashSet<>();
    private static Map<String, WebPagePosition> mapIndex = new HashMap<>();

    public void indexPage(WebPage webPage){
        if (existingPages.contains(webPage)){
            throw new RuntimeException("The website already indexed");
        }
        String[] keywords = webPage.getContent().toLowerCase().split("\\W+");
        for (int i = 0; i < keywords.length; i++){
            existingPages.add(webPage);
            if (searchMap.containsKey(keywords[i])){
                searchMap.get(keywords[i]).add(webPage);
                putToIndex(webPage, i);
            }
            WebPageLinkedList correspondingPages = new WebPageLinkedList();
            correspondingPages.add(webPage);
            searchMap.put(keywords[i],correspondingPages);
            putToIndex(webPage, i);
        }
    }

    public WebPageLinkedList search(String keyword){
        WebPageLinkedList searchResult = new WebPageLinkedList();
        for (Map.Entry<String, WebPageLinkedList> entry : searchMap.entrySet()){
            if (!searchMap.containsKey(keyword.toLowerCase())){
                throw new RuntimeException("The keyword doesn't exist in the map");
            }
            searchResult.addAll(searchMap.get(keyword));
        }

        return searchResult;
    }

    public void delete(String url){
        if (!mapIndex.containsKey(url)){
            throw new RuntimeException("The url doesn't exist in the map");
        }

        WebPagePosition webPagePosition = mapIndex.get(url);
        WebPage webPage = webPagePosition.getWebPage();
        String[] keywords = webPage.getContent().toLowerCase().split("\\W+");
        List<String> keywordsForWebpage = new ArrayList<>();
        for (int i = 0; i < keywords.length; i++){
            if(searchMap.get(keywords[i]).contains(webPage)){
                searchMap.get(keywords[i]).delete(webPage);
            }
        }
    }

    public int getSearchMapSize(){
        return searchMap.size();
    }
    public int getIndexMapSize(){
        return mapIndex.size();
    }

    private void putToIndex(WebPage webPage, int keywordPosition){
        List<Integer> list = new LinkedList<>();
        if (mapIndex.containsKey(webPage.getUrl())){
            list.addAll(mapIndex.get(webPage.getUrl()).getKeywordPosition());
        }
        WebPagePosition webPagePosition = new WebPagePosition(webPage, list);
        list.add(keywordPosition);
        mapIndex.put(webPage.getUrl(), webPagePosition);
    }
}
