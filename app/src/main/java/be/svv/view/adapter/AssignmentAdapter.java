package be.svv.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.model.Assignment;
import be.svv.mobileapplication.R;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>
{

    private List<Assignment> assignments;


    public AssignmentAdapter ()
    {
        this.assignments = new ArrayList<>();
    }

    public void setAssignments (List<Assignment> assignments)
    {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public AssignmentAdapter.AssignmentViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_assignment_adapter, parent, false);

        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position)
    {
        Assignment currentItem = assignments.get(position);
        holder.name.setText(currentItem.getName());
        holder.finisAt.setText(currentItem.getFinishAt().toLocaleString());
        holder.allowNotifications.setChecked(currentItem.isAllowNotifications());

        //        holder.allowNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        //        {
        //            @Override
        //            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
        //            {
        //                currentItem.setAllowNotifications(!currentItem.isAllowNotifications());
        //                RepositoryFactory repositoryFactory = new RepositoryFactory(context);
        //                Repository assignmentRepository = repositoryFactory.getRepository(Assignment.class);
        //                assignmentRepository.update(currentItem.getId(), currentItem, new VolleyCallback()
        //                {
        //                    @Override
        //                    public void onSuccess (String result)
        //                    {
        //                        Log.d("RESPONSE", result);
        //                    }
        //                });
        //            }
        //        });
    }


    @Override
    public int getItemCount ()
    {
        return assignments.size();
    }

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

}
