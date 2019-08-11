package org.miles.lib.room;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.miles.lib.room.test.AppDatabase;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BaseDaoTest {

    private static final String NAME = "name";
    private static final int ROW_NUM = 10;

    private BaseEntityDao mBaseEntityDao;

    @Before
    public void init() {
        Context context = ApplicationProvider.getApplicationContext();
        mBaseEntityDao = AppDatabase.get(context).baseEntityDao();

        for (int i = 1; i <= ROW_NUM; i++) {
            BaseEntity entity = new BaseEntity();
            entity.id = i;
            entity.name = NAME + i;
            mBaseEntityDao.insert(entity);
        }
    }

    @After
    public void clear(){
        AppDatabase.get().clear();
    }

    @Test
    public void insert() {
        BaseEntity entity = new BaseEntity();
        int newid = 11;
        entity.id = newid;
        entity.name = NAME + newid;
        mBaseEntityDao.insert(entity);

        List<BaseEntity> entities = mBaseEntityDao.selectAll();
        Assert.assertEquals(entities.size(), ROW_NUM + 1);
        BaseEntity newEntity = mBaseEntityDao.selectById(newid);
        Assert.assertEquals(newEntity.name, NAME + newid);
    }

    @Test
    public void insertList() {
        List<BaseEntity> entities = new ArrayList<>();
        for (int i = ROW_NUM + 1; i <= ROW_NUM + ROW_NUM; i++) {
            BaseEntity baseEntity = new BaseEntity();
            baseEntity.id = i;
            baseEntity.name = NAME + i;
            entities.add(baseEntity);
        }
        mBaseEntityDao.insert(entities);

        entities = mBaseEntityDao.selectAll();
        Assert.assertEquals(entities.size(), ROW_NUM + ROW_NUM);
    }

    @Test
    public void delete() {
        int deleteId = 1;
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.id = deleteId;
        mBaseEntityDao.delete(baseEntity);

        baseEntity = mBaseEntityDao.selectById(deleteId);
        Assert.assertNull(baseEntity);
    }

    @Test
    public void deleteById() {
        int deleteId = 1;
        mBaseEntityDao.delete(deleteId);

        BaseEntity baseEntity = mBaseEntityDao.selectById(deleteId);
        Assert.assertNull(baseEntity);
    }

    @Test
    public void deleteList() {
        List<BaseEntity> deleteEntities = new ArrayList<>();
        for (int i = 1; i <= ROW_NUM / 2; i++) {
            BaseEntity baseEntity = new BaseEntity();
            baseEntity.id = i;
            deleteEntities.add(baseEntity);
        }
        mBaseEntityDao.delete(deleteEntities);

        List<BaseEntity> entities = mBaseEntityDao.selectAll();
        Assert.assertEquals(entities.size(), ROW_NUM / 2);
    }

    @Test
    public void update() {
        int updateId = 1;
        String updateName = "updatename";
        BaseEntity baseEntity = mBaseEntityDao.selectById(updateId);
        baseEntity.name = updateName;
        mBaseEntityDao.update(baseEntity);

        BaseEntity updateEntity = mBaseEntityDao.selectById(updateId);
        Assert.assertEquals(updateName, updateEntity.name);
    }

    @Test
    public void selectById() {
        int selectId = 1;
        BaseEntity baseEntity = mBaseEntityDao.selectById(selectId);
        Assert.assertNotNull(baseEntity);
        Assert.assertEquals(baseEntity.id, selectId);
    }

    @Test
    public void selectAll() {
        List<BaseEntity> entities = mBaseEntityDao.selectAll();
        Assert.assertEquals(entities.size(), ROW_NUM);
    }
}