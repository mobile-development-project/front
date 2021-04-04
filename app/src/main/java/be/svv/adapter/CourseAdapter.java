package be.svv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.svv.entity.Course;
import be.svv.mobileapplication.R;
import be.svv.mobileapplication.course.ShowCourseActivity;
import be.svv.service.Gson.GsonSingleton;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
{

    private ArrayList<Course> courses;
    private Context context;


    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, assignments, id;

        public CourseViewHolder (@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.course_id);
            assignments = itemView.findViewById(R.id.course_assignements);
            name = itemView.findViewById(R.id.course_name);
        }

    }

    public CourseAdapter (Context context, ArrayList<Course> courses)
    {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_course_adapter, parent, false);

        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull CourseAdapter.CourseViewHolder holder, int position)
    {
        Course currentItem = courses.get(position);
        holder.id.setText(currentItem.getId() + "");
        holder.name.setText(currentItem.getName());
        holder.assignments.setText(currentItem.getAssignments().size() + " devoir(s)");

        holder.itemView.setOnClickListener(v ->
        {
            Intent intent = new Intent(context, ShowCourseActivity.class);
            intent.putExtra("COURSE", (GsonSingleton.getInstance().toJson(currentItem)));
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount ()
    {
        return courses.size();
    }
}
