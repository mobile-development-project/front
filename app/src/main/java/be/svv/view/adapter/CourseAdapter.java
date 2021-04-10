package be.svv.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.model.Course;
import be.svv.globals.Helpers;
import be.svv.mobileapplication.R;
//import be.svv.view.course.ShowCourseActivity;
//import be.svv.service.Gson.GsonSingleton;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>
{

    private List<Course> courses;

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
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull CourseAdapter.CourseViewHolder holder, int position)
    {
        Course currentItem = courses.get(position);
        int sizeAssignments = currentItem.getAssignments().size();
        holder.name.setText(currentItem.getName());
        holder.assignments.setText(sizeAssignments == 0 ? "Pas de devoir" : sizeAssignments + Helpers.toPlurar("devoir", sizeAssignments));

        //        holder.itemView.setOnClickListener(v ->
        //        {
        //            Intent intent = new Intent(context, ShowCourseActivity.class);
        //            intent.putExtra("COURSE", (GsonSingleton.getInstance().toJson(currentItem)));
        //            context.startActivity(intent);
        //        });
    }

    @Override
    public int getItemCount ()
    {
        return courses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, assignments;

        public CourseViewHolder (@NonNull View itemView)
        {
            super(itemView);
            assignments = itemView.findViewById(R.id.course_assignements);
            name = itemView.findViewById(R.id.course_name);
        }
    }
}
