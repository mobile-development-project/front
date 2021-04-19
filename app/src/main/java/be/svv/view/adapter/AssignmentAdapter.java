package be.svv.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.globals.Url;
import be.svv.model.Assignment;
import be.svv.mobileapplication.R;
import be.svv.model.request.AssignmentRequest;
import be.svv.viewmodel.AssignmentViewModel;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>
{

    private List<Assignment> assignments;
    private Context context;

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
        context = view.getContext();
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position)
    {
        Assignment currentItem = assignments.get(position);
        holder.name.setText(currentItem.getName());
        holder.finisAt.setText(currentItem.getFinishAt().toLocaleString());
        holder.allowNotifications.setChecked(currentItem.isAllowNotifications());

        holder.allowNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
            {
                currentItem.setAllowNotifications(!currentItem.isAllowNotifications());
                AssignmentViewModel assignmentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(AssignmentViewModel.class);
                assignmentViewModel.update(new AssignmentRequest(currentItem.getName(), Url.COURSES + currentItem.getCourse()
                        .getId(), currentItem.isAllowNotifications()), currentItem.getId());
            }
        });
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
