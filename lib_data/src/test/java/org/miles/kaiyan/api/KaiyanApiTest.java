package org.miles.kaiyan.api;

import org.junit.Test;
import org.miles.lib.data.DataManager;
import org.miles.lib.data.RetrofitManager;
import org.miles.kaiyan.data.entity.KaiyanCategory;
import org.miles.kaiyan.data.entity.KaiyanVideoItem;
import org.miles.kaiyan.data.entity.KaiyanVideoList;

import java.util.List;

import io.reactivex.functions.Consumer;

public class KaiyanApiTest {

    public static final String TAG = "KaiyanApiTest";

    @Test
    public void getCategories() {
        RetrofitManager.get().getKaiyanApi().getCategories()
                .subscribe(new Consumer<List<KaiyanCategory>>() {
                    @Override
                    public void accept(List<KaiyanCategory> kaiyanCategories) throws Exception {
                        for (KaiyanCategory category : kaiyanCategories) {
                            System.out.println(category);
                        }
                    }
                });
    }

    @Test
    public void getVideoList() {
        RetrofitManager.get().getKaiyanApi().getVideoList(14)
                .subscribe(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) throws Exception {
                        List<KaiyanVideoItem> items = kaiyanVideoList.itemList;
                        for (KaiyanVideoItem item : items) {
                            System.out.println(item);
                        }
                    }
                });
    }

    @Test
    public void getVideoListPage(){
        RetrofitManager.get().getKaiyanApi().getVideoList(14, 0, 1)
                .subscribe(new Consumer<KaiyanVideoList>() {
                    @Override
                    public void accept(KaiyanVideoList kaiyanVideoList) throws Exception {
                        List<KaiyanVideoItem> items = kaiyanVideoList.itemList;
                        for (KaiyanVideoItem item : items) {
                            System.out.println(item);
                        }
                    }
                });
    }
}
