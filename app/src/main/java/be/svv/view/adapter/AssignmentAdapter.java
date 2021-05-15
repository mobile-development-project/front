package be.svv.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
    private OnItemClickListener listener;
    private Context context;
    private boolean settingsEnabled = true;
    private boolean notificationsEnabled = true;

    public AssignmentAdapter ()
    {
        this.assignments = new ArrayList<>();
    }

    public void setAssignments (List<Assignment> assignments)
    {
        this.assignments = assignments;
    }

    public void enableSettings (boolean enable)
    {
        settingsEnabled = enable;
    }

    public void enableNotifications (boolean enable)
    {
        notificationsEnabled = enable;
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
        AssignmentViewModel assignmentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(AssignmentViewModel.class);

        holder.name.setText(currentItem.getName());
        holder.finishAt.setText(currentItem.getFinishAt());
        holder.allowNotifications.setChecked(currentItem.isAllowNotifications());

        if (notificationsEnabled)
        {
            holder.allowNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
                {
                    currentItem.setAllowNotifications(!currentItem.isAllowNotifications());
                    AssignmentViewModel assignmentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(AssignmentViewModel.class);
                    assignmentViewModel.update(new AssignmentRequest(currentItem.getName(), Url.COURSES + currentItem.getCourse()
                            .getId(), currentItem.isAllowNotifications(), currentItem.getFinishAt()), currentItem.getId());
                }
            });
        }
        else
        {
            holder.allowNotifications.setVisibility(View.GONE);
        }


        if (settingsEnabled)
        {
            holder.settings.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    PopupMenu popup = new PopupMenu(context, holder.settings);
                    popup.inflate(R.menu.options_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                    {
                        @Override
                        public boolean onMenuItemClick (MenuItem item)
                        {
                            switch (item.getItemId())
                            {
                                case R.id.menu_edit:
                                    int position = assignments.indexOf(currentItem);
                                    Log.d("TAG", position + "");
                                    if (listener != null && position != RecyclerView.NO_POSITION)
                                    {
                                        listener.onItemClick(currentItem);
                                    }
                                    return true;
                                case R.id.menu_delete:
                                    alertDialog.setTitle("Supression");
                                    alertDialog.setMessage("Voulez-vous vraiment supprimer ce devoir ?");
                                    alertDialog.setCancelable(true);

                                    alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick (DialogInterface dialog, int which)
                                        {
                                            assignmentViewModel.delete(currentItem.getId());
                                            notifyItemRemoved(assignments.indexOf(currentItem));
                                            assignments.remove(currentItem);
                                            Toast.makeText(context, "Devoir supprimé", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    alertDialog.setNeutralButton("Annuler", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick (DialogInterface dialog, int which)
                                        {
                                            Toast.makeText(context, "Action annulée", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alert = alertDialog.create();
                                    alert.show();

                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                }
            });
        }
        else
        {
            holder.settings.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount ()
    {
        return assignments.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, finishAt, settings;
        Switch allowNotifications;

        public AssignmentViewHolder (@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.assignment_name);
            allowNotifications = itemView.findViewById(R.id.assignment_allow_notifications);
            finishAt = itemView.findViewById(R.id.assignment_finish_at);
            settings = itemView.findViewById(R.id.assignment_settings);
        }
    }


    public interface OnItemClickListener
    {
        void onItemClick (Assignment assignment);
    }

    public void setOnItemClickListener (OnItemClickListener listener)
    {
        this.listener = listener;
    }


}
