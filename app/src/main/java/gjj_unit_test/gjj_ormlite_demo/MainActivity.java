package gjj_unit_test.gjj_ormlite_demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj_unit_test.gjj_ormlite_demo.bean.User;
import gjj_unit_test.gjj_ormlite_demo.test2.UserDaoImp;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_test_save)
    Button btnTestSave;
    @Bind(R.id.btn_test_read)
    Button btnTestRead;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_test_save, R.id.btn_test_read})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_save:
                testAddArticle();
                break;
            case R.id.btn_test_read:
                testRead();
                break;
        }
    }

    public void testAddArticle() {
        User u = new User();
        u.setName("gjj");
        u.setPwd("123456");
        u.setId(1);
        UserDaoImp userDao = new UserDaoImp(MainActivity.this, User.class);
        try {
            if (userDao == null) {
                Toast.makeText(MainActivity.this, "user表创建失败", Toast.LENGTH_LONG).show();
                return;
            }
            int result = userDao.save(u);
            Log.e("save-result====", result + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testRead() {
        UserDaoImp userDao = new UserDaoImp(MainActivity.this, User.class);
        if (userDao == null) {
            Toast.makeText(MainActivity.this, "user表创建失败", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            List<User> list = userDao.queryAll();
            Log.e("list.size()=====", list.size() + "");
            for (User user : list) {
                Log.e("user=====", user.toString());
                Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_LONG).show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
