package org.miles.kaiyan.data.db;

import androidx.room.Dao;
import androidx.room.Query;

import org.miles.kaiyan.data.entity.KaiyanVideoItem;
import org.miles.lib.room.BaseDao;

import java.util.List;

@Dao
public abstract class KaiyanVideoItemDao extends BaseDao<KaiyanVideoItem> {
    // 这里需要使用 arg0，arg1这样的参数，是room的bug，参考：
    // https://stackoverflow.com/questions/48100247/room-query-error-cannot-find-method-parameters?rq=1
    @Query("SELECT * FROM KaiyanVideoItem WHERE category_id=:arg0 LIMIT :arg1 OFFSET :arg2")
    public abstract List<KaiyanVideoItem> selectByTabIdPage(long categoryId, int pageSize, int offset);

    @Query("DELETE FROM KaiyanVideoItem WHERE category_id=:arg0")
    public abstract void deleteByCategoryId(long categoryId);
}
