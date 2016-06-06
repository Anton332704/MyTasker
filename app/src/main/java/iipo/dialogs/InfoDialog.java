package iipo.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.adapters.FriendsAdapter;
import iipo.models.UserModel;

/**
 * Created by user on 06.06.2016.
 */
public class InfoDialog extends android.support.v4.app.DialogFragment {

    private ListView listView;
    private FriendsAdapter adapter;
    private ArrayList<UserModel> arrayList;
    private TextView textInfo;
    private Button buttonOk;

    public static InfoDialog newInstance(String name, String info){
        InfoDialog dialog = new InfoDialog();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("info", info);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_task_dialog, container, false);
        listView = (ListView) view.findViewById(R.id.listFriendsInTask);
        textInfo = (TextView) view.findViewById(R.id.textViewInfo);
        textInfo.setText(getArguments().getString("info", "-"));
        buttonOk = (Button) view.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        arrayList = new ArrayList<UserModel>();
        arrayList.add(new UserModel("Olga", "Minaeva", ""));
        arrayList.add(new UserModel("Petr", "Osokin", ""));
        arrayList.add(new UserModel("Mishel", "Aliev", ""));
        arrayList.add(new UserModel("Maksim", "Belov", ""));
        adapter = new FriendsAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString("name", "Информация о задаче");
        getDialog().setTitle(title);
    }
}
