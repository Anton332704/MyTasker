package iipo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.adapters.TasksPagerAdapter;

/**
 * Created by user on 15.05.2016.
 */
public class MainFragmentTask extends Fragment {

    private ViewPager viewPager;
    private TasksPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    public static MainFragmentTask newInstance(String name){
        MainFragmentTask fragmentTasks = new MainFragmentTask();
        return fragmentTasks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_task, container, false);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Мои задачи"));
        tabLayout.addTab(tabLayout.newTab().setText("Создать новую"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager = (ViewPager) view.findViewById(R.id.taskPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        pagerAdapter = new TasksPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        return view;
    }
}
