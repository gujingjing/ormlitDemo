package gjj_unit_test.gjj_ormlite_demo.test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import gjj_unit_test.gjj_ormlite_demo.bean.User;

/**
 * 作者：gjj on 2016/3/15 14:08
 * 邮箱：Gujj512@163.com
 * 创建数据库的帮助类
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String TABLE_NAME = "sqlite-test.db";
    private Map<String, Dao> daos = new HashMap<String, Dao>();//用来存放所有的表

    private static final int databaseVersion=1;
    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, databaseVersion);
    }

    /**
     * 创建数据库中的---表
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 版本更新时候使用
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int i, int i1) {
        try
        {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        context = context.getApplicationContext();
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
