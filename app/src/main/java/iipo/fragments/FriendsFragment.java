package iipo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.adapters.FriendsAdapter;
import iipo.models.UserModel;

/**
 * Created by user on 05.06.2016.
 */
public class FriendsFragment extends Fragment {

    private ListView listView;
    private FriendsAdapter adapter;
    private ArrayList<UserModel> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.lvFriends);
        arrayList = new ArrayList<UserModel>();
        arrayList.add(new UserModel("Andy", "Smith", ""));
        arrayList.add(new UserModel("Jack", "Alone", ""));
        arrayList.add(new UserModel("Anatoly", "Borisov", ""));
        arrayList.add(new UserModel("Marina", "McCalcin", ""));
        adapter = new FriendsAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);
        return view;
    }
}