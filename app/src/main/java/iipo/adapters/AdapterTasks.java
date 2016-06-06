package iipo.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.dialogs.InfoDialog;
import iipo.models.TaskModel;

/**
 * Created by user on 11.11.2015.
 */
public class AdapterTasks extends BaseAdapter {
    private ArrayList<TaskModel> arrayList;
    private Context base;
    private LayoutInflater inflater;
    private boolean mode = true;

    public AdapterTasks(Context context, ArrayList<TaskModel> menus) {
        base = context;
        arrayList = menus;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AdapterTasks(Context context, ArrayList<TaskModel> menus, boolean mode) {
        this(context, menus);
        this.mode = mode;
    }

    public static class ViewHolder{
        TextView textTitle;
        View status;
        TextView textInfo;
        TextView textDateFinish;
        TextView textDateStart;
        ImageView iconMore;
        ImageView iconDelete;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            convertView = inflater.inflate(R.layout.task_item, parent, false);
            viewHolder.textTitle = (TextView) convertView.findViewById(R.id.title_text);
            viewHolder.status = convertView.findViewById(R.id.statusView);
            viewHolder.textInfo = (TextView) convertView.findViewById(R.id.textViewTaskInfo);
            viewHolder.textDateFinish = (TextView) convertView.findViewById(R.id.textViewDataFinish);
            viewHolder.textDateStart = (TextView) convertView.findViewById(R.id.textViewDataStart);
            viewHolder.iconMore = (ImageView) convertView.findViewById(R.id.iconMoreInfo);
            viewHolder.iconDelete = (ImageView) convertView.findViewById(R.id.iconDelete);
            if(mode){
                viewHolder.iconDelete.setVisibility(View.VISIBLE);
            } else {
                viewHolder.iconDelete.setVisibility(View.GONE);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final TaskModel task = arrayList.get(position);
        viewHolder.textTitle.setText(task.getTitle());
        viewHolder.status.setBackgroundColor(base.getResources().getColor(R.color.TabsColor));
        viewHolder.textDateFinish.setText(task.getDateFinish());
        viewHolder.textDateStart.setText(task.getDateStart());
        viewHolder.textInfo.setText(task.getInfo());
        viewHolder.iconMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoDialog dialog = InfoDialog.newInstance(task.getTitle(), task.getInfo());
                dialog.show(((AppCompatActivity) base).getSupportFragmentManager(), "InfoDialog");
            }
        });
        if(mode){
            viewHolder.iconDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(base);
                    builder.setTitle("Удаление задачи")
                            .setMessage("Вы действительно хотите удалить задачу?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        return convertView;
    }
}
