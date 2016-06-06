package iipo.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.DbHelper;
import iipo.adapters.AdapterTasks;
import iipo.asynctask.GetMyTasksTask;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.models.TaskModel;
import iipo.models.UserModel;

/**
 * Created by user on 10.11.2015.
 */
public class FragmentTasks extends Fragment{
    private Context context;
    private ListView listViewTasks;
    private String hash;
    private String id;

    public static FragmentTasks newInstance(String name){
        FragmentTasks fragmentTasks = new FragmentTasks();
        return fragmentTasks;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        hash = sp.getString("hash", "");
        id = sp.getString("id", "");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tasks, container, false);
        GetMyTasksTask getMyTasksTask = new GetMyTasksTask();
        getMyTasksTask.execute(hash, id);

        ArrayList<String> arrayList = null;
        try {
            arrayList = getMyTasksTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<TaskModel> taskModelArrayList = new ArrayList<TaskModel>();
        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor c = database.query(DbHelper.TASK_TABLE, null, null, null, null, null, null);
        if(c != null){
            while (c.moveToNext()) {
                String title = c.getString(c.getColumnIndex(DbHelper.TASK_TITLE));
                int status = c.getInt(c.getColumnIndex(DbHelper.TASK_STATUS));
                String info = c.getString(c.getColumnIndex(DbHelper.TASK_INFO));
                String dateF = c.getString(c.getColumnIndex(DbHelper.TASK_DATE_FINISH));
                String dateS = c.getString(c.getColumnIndex(DbHelper.TASK_DATE_START));
                int groupId = c.getInt(c.getColumnIndex(DbHelper.TASK_ID_GROUP));
                taskModelArrayList.add(new TaskModel(1, title, status, info, dateF, dateS, groupId, new UserModel("Anton", "", ""
                ), new ArrayList<UserModel>()));
            }
        }


        listViewTasks = (ListView)view.findViewById(R.id.listViewTasks);
        AdapterTasks adapterTasks = new AdapterTasks(context, taskModelArrayList);
        listViewTasks.setAdapter(adapterTasks);
        return view;
    }
}
