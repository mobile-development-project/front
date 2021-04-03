package be.svv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import be.svv.entity.Course;
import be.svv.mobileapplication.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
{

    private Course[] courses;
    private Context context;

    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, assignments, id;

        public CourseViewHolder (@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.textView1);
            assignments = itemView.findViewById(R.id.textView2);
            name = itemView.findViewById(R.id.textView3);
        }
    }

    public CourseAdapter (Context context, Course[] courses)
    {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.list_course_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder (@NonNull CourseAdapter.CourseViewHolder holder, int position)
    {
        holder.id.setText(courses[position].getId() + "");
        holder.name.setText(courses[position].getName());
        holder.assignments.setText(courses[position].getAssignments().length + " devoir(s)");

    }


    @Override
    public int getItemCount ()
    {
        return courses.length;
    }
}
