package com.example.app;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.app.common.Constant;
import com.example.app.ui.fragment.ContentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar getToolbar() {
        return toolbar;
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.Android:
                bundle.putString(Constant.CONSTANT_TITLE, "Android");
                break;
            case R.id.iOS:
                bundle.putString(Constant.CONSTANT_TITLE, "iOS");
                break;
            case R.id.Web:
                bundle.putString(Constant.CONSTANT_TITLE, "前端");
                break;
            case R.id.suggestion:
                bundle.putString(Constant.CONSTANT_TITLE, "瞎推荐");
                break;
            case R.id.video:
                bundle.putString(Constant.CONSTANT_TITLE, "休息视频");
                break;
            case R.id.girls:
                bundle.putString(Constant.CONSTANT_TITLE, "福利");
                break;
            case R.id.extend:
                bundle.putString(Constant.CONSTANT_TITLE, "拓展资源");
                break;
            case R.id.app:
                bundle.putString(Constant.CONSTANT_TITLE, "App");
                break;
            case R.id.collection:
                bundle.putString(Constant.CONSTANT_TITLE, "收藏");
                break;
            case R.id.clear_cache:
                break;

        }

        Fragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.toolbar)
    public void onViewClicked() {
    }
}
