package iipo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.adapters.AdapterTasks;
import iipo.asynctask.GetFriendsTasks;
import iipo.models.TaskModel;
import iipo.models.UserModel;
import interfaces.OnTaskCompleteListener;

/**
 * Created by user on 05.06.2016.
 */
public class FriendsTaskFragment extends android.support.v4.app.Fragment {

    private ListView listView;
    private AdapterTasks adapterTasks;
    private ArrayList<TaskModel> tasksList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_of_friends, container, false);
        listView = (ListView) view.findViewById(R.id.lvTasksOfFriends);
        tasksList = new ArrayList<TaskModel>();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetFriendsTasks friendsTasks = new GetFriendsTasks(getActivity(), new OnTaskCompleteListener() {
            @Override
            public void onComplete(Object data) {
                tasksList = new ArrayList<TaskModel>();
                tasksList.add(new TaskModel(12, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(13, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(14, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(15, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(16, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(17, "123", 1,"", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                tasksList.add(new TaskModel(18, "123", 1, "", "", "", 2, new UserModel("Anton", "Anton", ""), new ArrayList<UserModel>()));
                adapterTasks = new AdapterTasks(getActivity(), tasksList, false);
                listView.setAdapter(adapterTasks);
                adapterTasks.notifyDataSetChanged();

            }
        });
        friendsTasks.execute();
    }
}
