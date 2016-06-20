package myapp.com.hday06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initView();

        toolBar.setTitle("这里是Toolbar");
//        setSupportActionBar(toolBar);
    }

    private void initView() {
        toolBar = (Toolbar)findViewById(R.id.toolbar);
    }
}
