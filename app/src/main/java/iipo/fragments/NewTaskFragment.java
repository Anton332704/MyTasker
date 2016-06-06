package iipo.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.DbHelper;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;

/**
 * Created by user on 15.05.2016.
 */
public class NewTaskFragment extends Fragment {

    private Context context;
    private LinearLayout layoutDateFinish;
    private LinearLayout layoutDateStart;
    private LinearLayout layoutTimeFinish;
    private LinearLayout layoutTimeStart;
    private EditText editTitle, editInfo;
    private TextView textDateFinish, textDateStart;
    private TextView textTimeFinish, textTimeStart;
    private Button button;

    private Spinner spinnerGroup;
    private Calendar calendar;

    private String yearString = "_";
    private static final String[] data = {"Семья", "Друзья", "Работа"};


    public static NewTaskFragment newInstance(String name){
        NewTaskFragment fragmentTasks = new NewTaskFragment();
        return fragmentTasks;
    }

    private void addData(String str){

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DATE);
            final int hour = calendar.get(Calendar.HOUR_OF_DAY);
            final int minute = calendar.get(Calendar.MINUTE);
            switch (v.getId()){
                case R.id.layoutDateFinish:
                    DatePickerDialog datePickerDialogFinish = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            yearString = dayOfMonth + "/" + monthOfYear + 1 + "/" + year;
                            textDateFinish.setText(yearString);
                        }
                    }, year,  month,  day);
                    datePickerDialogFinish.show();
                    break;
                case R.id.layoutDateStart:
                    DatePickerDialog datePickerDialogStart = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            yearString = dayOfMonth + "/" + monthOfYear + 1 + "/" + year;
                            textDateStart.setText(yearString);
                        }
                    }, year,  month,  day);
                    datePickerDialogStart.show();
                    break;
                case R.id.layoutTimeFinish:
                    TimePickerDialog timePickerDialogFinish = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        }
                    }, hour + 10, minute, true);
                    timePickerDialogFinish.show();
                    break;
                case R.id.layoutTimeStart:
                    TimePickerDialog timePickerDialogStart = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        }
                    }, hour, minute, true);
                    timePickerDialogStart.show();
                    break;
            }
        }
    };

    public static final String TASK_TITLE = "task_title";
    public static final String TASK_STATUS = "task_status";
    public static final String TASK_INFO = "task_info";
    public static final String TASK_DATE_FINISH = "task_date_finish";
    public static final String TASK_DATE_START = "task_date_start";
    public static final String TASK_ID_GROUP = "task_date_start";

    private View.OnClickListener addClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DbHelper dbHelper = new DbHelper(getActivity());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            //Cursor c = database.query(DbHelper.TASK_TABLE, null, null, null, null, null, null);
            ContentValues cv = new ContentValues();
            cv.put(TASK_TITLE, editTitle.getText().toString());
            cv.put(TASK_STATUS, 1);
            cv.put(TASK_INFO, editInfo.getText().toString());
            cv.put(TASK_DATE_FINISH, textDateFinish.getText().toString());
            cv.put(TASK_DATE_START, textDateStart.getText().toString());
            cv.put(TASK_ID_GROUP, spinnerGroup.getSelectedItemPosition());
            database.insert(DbHelper.TASK_TABLE, null, cv);
            cv.clear();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_new_task, container, false);
        button = (Button)view.findViewById(R.id.buttonAddTask);
        button.setOnClickListener(addClick);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup = (Spinner) view.findViewById(R.id.spinnerGroup);
        spinnerGroup.setAdapter(adapter);
        spinnerGroup.setPrompt("Группы");
        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerGroup.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layoutDateFinish = (LinearLayout) view.findViewById(R.id.layoutDateFinish);
        layoutDateStart = (LinearLayout) view.findViewById(R.id.layoutDateStart);
        layoutTimeFinish = (LinearLayout) view.findViewById(R.id.layoutTimeFinish);
        layoutTimeStart = (LinearLayout) view.findViewById(R.id.layoutTimeStart);

        editTitle = (EditText) view.findViewById(R.id.title_text);
        editInfo = (EditText) view.findViewById(R.id.textViewTaskInfo);
        textDateFinish = (TextView) view.findViewById(R.id.textViewDataFinish);
        textDateStart = (TextView) view.findViewById(R.id.textViewDateStart);

        calendar = Calendar.getInstance();

        layoutDateFinish.setOnClickListener(onClick);
        layoutDateStart.setOnClickListener(onClick);
        layoutTimeFinish.setOnClickListener(onClick);
        layoutTimeStart.setOnClickListener(onClick);

        return view;
    }
}
