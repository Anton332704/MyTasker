package iipo.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.DbHelper;
import interfaces.OnTaskCompleteListener;

/**
 * Created by user on 05.06.2016.
 */
public class GetFriendsTasks extends AsyncTask<Void, Void, JSONObject> {

    private Context context;
    private OnTaskCompleteListener listener;

    public GetFriendsTasks(Context context, OnTaskCompleteListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        return new JSONObject();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

            listener.onComplete("");

    }
}
