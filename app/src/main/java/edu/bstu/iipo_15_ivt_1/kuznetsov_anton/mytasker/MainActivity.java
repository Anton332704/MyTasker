package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import iipo.adapters.DrawerAdapter;
import iipo.asynctask.ServerTask;
import iipo.dialogs.ExitProfileDialog;
import iipo.fragments.FragmentTasks;
import iipo.fragments.FriendsFragment;
import iipo.fragments.FriendsTaskFragment;
import iipo.fragments.MainFragmentTask;
import iipo.fragments.WeatherFragment;
import iipo.models.ItemMenu;

public class MainActivity extends AppCompatActivity {

    ListView leftListView;
    ArrayList<ItemMenu> itemsDraws;
    Context base;
    DrawerLayout drawerLayout;
    TextView textLog;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base = this;
        //textLog = (TextView)findViewById(R.id.textViewWeb);
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(base);
       // textLog.setText(preferences.getString("login", "w")+" "+preferences.getString("password", "w"));

        toolbar = (Toolbar) findViewById(R.id.toolBarWidget);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Application");
        toolbar.setTitleTextColor(R.color.white);
        toolbar.setNavigationIcon(R.drawable.arrow_right);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        itemsDraws = new ArrayList<ItemMenu>();
        leftListView = (ListView)findViewById(R.id.ListDLeft);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        itemsDraws.add(new ItemMenu("header"));
        itemsDraws.add(new ItemMenu("Мои задачи"));
        itemsDraws.add(new ItemMenu("Задачи друзей"));
        itemsDraws.add(new ItemMenu("Мои друзья"));
        itemsDraws.add(new ItemMenu("Мои группы"));
        itemsDraws.add(new ItemMenu("Погода"));
        itemsDraws.add(new ItemMenu("Выйти"));
        DrawerAdapter drawerAdapter = new DrawerAdapter(base, itemsDraws);
        leftListView.setAdapter(drawerAdapter);
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment myFragment = null;
                switch (position) {
                    case 1:
                        toolbar.setTitle("Мои задачи");
                        myFragment = new MainFragmentTask();
                        break;
                    case 2:
                        myFragment = new FriendsTaskFragment();
                        toolbar.setTitle("Задачи друзей");
                        break;
                    case 3:
                        myFragment = new FriendsFragment();
                        toolbar.setTitle("Мои друзья");
                        break;
                    case 4:
                        myFragment = new FriendsFragment();
                        toolbar.setTitle("Мои группы");
                        break;
                    case 5:
                        myFragment = new WeatherFragment();
                        toolbar.setTitle("Погода");
                        break;
                    case 6:
                        ExitProfileDialog exitProfileDialog = new ExitProfileDialog();
                        exitProfileDialog.show(getSupportFragmentManager(), "exit");
                        break;
                }
                if (myFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFrame, myFragment).commit();
                }
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void StartWeb(View v) {
        ServerTask serverTask = new ServerTask(this);
        serverTask.execute();
    }
}