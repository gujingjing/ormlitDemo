package gjj_unit_test.gjj_ormlite_demo.test2;

import android.content.Context;

import gjj_unit_test.gjj_ormlite_demo.bean.User;

/**
 * 作者：gjj on 2016/3/15 15:10
 * 邮箱：Gujj512@163.com
 */
public class UserDaoImp extends BaseDaoImpl<User,Integer> {

    public UserDaoImp(Context context, Class<User> clazz) {
        super(context, clazz);
    }
}
