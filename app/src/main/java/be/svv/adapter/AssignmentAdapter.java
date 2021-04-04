package be.svv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.svv.entity.Assignment;
import be.svv.mobileapplication.R;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>
{

    private ArrayList<Assignment> assignments;
    private Context context;


    public class AssignmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, finisAt;
        Switch allowNotifications;

        public AssignmentViewHolder (@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.assignment_name);
            finisAt = itemView.findViewById(R.id.assignment_finish_at);
            allowNotifications = itemView.findViewById(R.id.assignment_allow_notifications);
        }

    }

    public AssignmentAdapter (Context context, ArrayList<Assignment> assignments)
    {
        this.assignments = assignments;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentAdapter.AssignmentViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_assignment_adapter, parent, false);

        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position)
    {
        Assignment currentItem = assignments.get(position);
        holder.name.setText(currentItem.getName());
        holder.finisAt.setText(currentItem.getFinishAt().toLocaleString());
        holder.allowNotifications.setChecked(currentItem.isAllowNotifications());
    }


    @Override
    public int getItemCount ()
    {
        return assignments.size();
    }
}
