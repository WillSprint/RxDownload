package zlc.season.rxdownloadproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;
    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private DownloadAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new DownloadAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);

        loadData();
    }

    private void loadData() {
        Resources res = getResources();
        final String[] images = res.getStringArray(R.array.image);
        final String[] urls = res.getStringArray(R.array.url);
        List<DownloadBean> list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            DownloadBean temp = new DownloadBean();
            temp.image = images[i];
            temp.url = urls[i];
            temp.state = DownloadBean.START;
            list.add(temp);
        }
        mAdapter.addAll(list);
    }
}
