package iipo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.models.UserModel;

/**
 * Created by user on 05.06.2016.
 */
public class FriendsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> listUsers;

    public FriendsAdapter(Context context, ArrayList<UserModel> listUsers) {
        this.context = context;
        this.listUsers = listUsers;
    }

    private static class ViewHolder{
        ImageView icon;
        TextView name;
        TextView secName;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.friends_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameFriend);
            viewHolder.secName = (TextView) convertView.findViewById(R.id.secNameFriend);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String nameStr = ((UserModel)getItem(position)).getName();
        String secNameStr = ((UserModel)getItem(position)).getSecName();

        viewHolder.name.setText(nameStr);
        viewHolder.secName.setText(secNameStr);
        return convertView;
    }
}
