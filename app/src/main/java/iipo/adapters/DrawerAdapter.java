package iipo.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import iipo.models.ItemMenu;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;

/**
 * Created by user on 19.10.2015.
 */
public class DrawerAdapter extends BaseAdapter {
    Context base;
    ArrayList<ItemMenu> itemMenus;
    LayoutInflater inflater;

    public DrawerAdapter(Context context, ArrayList<ItemMenu> menus) {
        base = context;
        itemMenus = menus;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return itemMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(getItemId(position) == 0)
        {
            if(v == null)
            {
                v = inflater.inflate(R.layout.header, parent, false);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(base);
                String strLogin = sp.getString("login", "SuperUser");
                ((TextView) v.findViewById(R.id.textViewProfile)).setText(strLogin);
            }
        }
        else if(getItemId(position) != 0){

                v = inflater.inflate(R.layout.item_left, parent, false);
                final ItemMenu itemMenu = (ItemMenu) getItem(position);
                ((TextView) v.findViewById(R.id.textViewLeft)).setText(itemMenu.getName());

        }
        return v;
    }
}
