package com.example.johannes.colorpicker;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by a1402972 on 3.11.2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder> {

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view_item, viewGroup, false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
        //taskViewHolder.headline.setText(tasks.get(position).getOtsikko());
        taskViewHolder.cv.setCardBackgroundColor(Color.parseColor(tasks.get(position).getOtsikko()));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView headline;

        TaskViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            headline = (TextView)itemView.findViewById(R.id.Headline);
        }
    }

    List<Task> tasks;
    RVAdapter(List<Task> tasks){
        this.tasks = tasks;
    }
}
