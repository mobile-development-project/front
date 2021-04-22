package be.svv.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.model.Course;
import be.svv.globals.Helpers;
import be.svv.mobileapplication.R;
import be.svv.service.gson.GsonSingleton;
import be.svv.viewmodel.CourseViewModel;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
{

    private List<Course> courses;
    private OnItemClickListener listener;
    private Context context;

    public CourseAdapter ()
    {
        courses = new ArrayList<>();
    }

    public void setCourses (List<Course> courses)
    {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_course_adapter, parent, false);
        context = view.getContext();
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull CourseAdapter.CourseViewHolder holder, int position)
    {
        Course currentItem = courses.get(position);
        CourseViewModel courseViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CourseViewModel.class);

        int sizeAssignments = currentItem.getAssignments().size();
        holder.name.setText(currentItem.getName());
        holder.assignments.setText(sizeAssignments == 0 ? "Pas de devoir" : sizeAssignments + Helpers.toPlurar("devoir", sizeAssignments));

        holder.courseSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                PopupMenu popup = new PopupMenu(context, holder.courseSettings);
                popup.inflate(R.menu.options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick (MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.menu_edit:
                                int position = courses.indexOf(currentItem);
                                if (listener != null && position != RecyclerView.NO_POSITION)
                                {
                                    listener.onItemClick(currentItem);
                                }
                                return true;
                            case R.id.menu_delete:
                                alertDialog.setTitle("Supression");
                                alertDialog.setMessage("Voulez-vous vraiment supprimer ce cours ?");
                                alertDialog.setCancelable(true);

                                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick (DialogInterface dialog, int which)
                                    {
                                        courseViewModel.delete(currentItem.getId());
                                        notifyItemRemoved(courses.indexOf(currentItem));
                                        courses.remove(currentItem);
                                        Toast.makeText(context, "Cours supprimé", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount ()
    {
        return courses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, assignments, courseSettings;

        public CourseViewHolder (@NonNull View itemView)
        {
            super(itemView);
            assignments = itemView.findViewById(R.id.course_assignements);
            name = itemView.findViewById(R.id.course_name);
            courseSettings = itemView.findViewById(R.id.course_settings);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick (Course course);
    }

    public void setOnItemClickListener (OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
