package org.miles.kaiyan.data.entity;

import java.util.List;

public class KaiyanVideoList {

    public List<KaiyanVideoItem> itemList;
    public String nextPageUrl;

    @Override
    public String toString() {
        return "KaiyanVideoList{" +
                "itemList=" + itemList +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                '}';
    }
}
