package iipo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;

/**
 * Created by user on 06.06.2016.
 */
public class FragmentGroups extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tasks, container, false);
        return view;
    }
}
