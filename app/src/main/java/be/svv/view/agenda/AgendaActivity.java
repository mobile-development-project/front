package be.svv.view.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnDayLongClickListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Agenda;
import be.svv.model.Assignment;
import be.svv.model.Category;
import be.svv.model.request.AgendaRequest;
import be.svv.service.gson.GsonSingleton;
import be.svv.viewmodel.AssignmentViewModel;
import be.svv.viewmodel.AgendaViewModel;

public class AgendaActivity extends AppCompatActivity
{

    public static final int ADD_CATEGORY_TO_AGENDA = 1;
    public static final String EXTRA_ASSIGNMENT = "ASSIGNMENT";
    public static final String EXTRA_CATEGORY = "CATEGORY";
    public static final String EXTRA_AGENDA_ID = "AGENDA_ID";

    private TextView txtSelectedDate;
    private AgendaViewModel agendaViewModel;
    private AssignmentViewModel assignmentViewModel;
    private CalendarView calendarView;
    private List<EventDay> events;
    private SimpleDateFormat sdf;
    private Button btnAddCategory;

    private ArrayList<Assignment> localAssignments;
    private ArrayList<Agenda> localAgendas;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        setTitle("Agenda");
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        events = new ArrayList<>();
        txtSelectedDate = findViewById(R.id.txt_selected_date);
        calendarView = findViewById(R.id.calendar_view);
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getAll().observe(this, new Observer<List<Assignment>>()
        {
            @Override
            public void onChanged (List<Assignment> assignments)
            {
                localAssignments = (ArrayList<Assignment>) assignments;
                for (Assignment assignment : assignments)
                {
                    try
                    {
                        Date date = sdf.parse(assignment.getFinishAt());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        events.add(new EventDay(cal, R.drawable.ic_baseline_home_work_24));
                        calendarView.setEvents(events);
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        agendaViewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        agendaViewModel.getAll().observe(this, new Observer<List<Agenda>>()
        {
            @Override
            public void onChanged (List<Agenda> agendas)
            {
                localAgendas = (ArrayList<Agenda>) agendas;
                for (Agenda agenda : agendas)
                {
                    if (agenda.getCategories().size() != 0)
                    {
                        for (Category category : agenda.getCategories())
                        {
                            try
                            {
                                Drawable unwrappedDrawable = AppCompatResources.getDrawable(AgendaActivity.this, R.drawable.black_cursor);
                                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable).getConstantState().newDrawable().mutate();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                {
                                    wrappedDrawable.setTint(category.getColor());
                                }

                                Date date = sdf.parse(agenda.getDate());
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                events.add(new EventDay(cal, wrappedDrawable));
                                calendarView.setEvents(events);
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        calendarView.setOnDayClickListener(new OnDayClickListener()
        {
            @Override
            public void onDayClick (EventDay eventDay)
            {
                txtSelectedDate.setText(sdf.format(eventDay.getCalendar().getTime()));
            }
        });

        calendarView.setOnDayLongClickListener(new OnDayLongClickListener()
        {
            @Override
            public void onDayLongClick (@NotNull EventDay eventDay)
            {
                Intent intent = new Intent(AgendaActivity.this, ShowDayEventsActivity.class);
                int agendaId = 0;
                ArrayList<Category> categories = new ArrayList<>();
                ArrayList<Assignment> assignments = new ArrayList<>();

                String selectedDate = sdf.format(eventDay.getCalendar().getTime());
                for (Assignment assignment : localAssignments)
                {
                    String assignmentDate = assignment.getFinishAt().substring(0, 10);
                    if (assignmentDate.equals(selectedDate))
                    {
                        assignments.add(assignment);
                    }
                }
                if (assignments.size() != 0)
                {
                    intent.putExtra(EXTRA_ASSIGNMENT, GsonSingleton.getInstance().toJson(assignments));

                }
                for (Agenda agenda : localAgendas)
                {
                    if (agenda.getDate().equals(selectedDate))
                    {
                        agendaId = agenda.getId();
                        for (Category category : agenda.getCategories())
                        {
                            categories.add(category);
                        }
                    }
                }
                if (categories.size() != 0)
                {
                    intent.putExtra(EXTRA_CATEGORY, GsonSingleton.getInstance().toJson(categories));
                }
                intent.putExtra(EXTRA_AGENDA_ID, agendaId);

                startActivity(intent);
            }
        });

        btnAddCategory = findViewById(R.id.btn_add_category_to_agenda);
        btnAddCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!txtSelectedDate.getText().toString().isEmpty())
                {
                    Intent intent = new Intent(getApplicationContext(), AddCategoryToAgendaActivity.class);
                    intent.putExtra(AddCategoryToAgendaActivity.EXTRA_DATE, txtSelectedDate.getText().toString());
                    startActivityForResult(intent, ADD_CATEGORY_TO_AGENDA);
                }
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CATEGORY_TO_AGENDA && resultCode == RESULT_OK)
        {
            AgendaRequest agendaRequest = GsonSingleton.getInstance()
                    .fromJson(data.getStringExtra(AddCategoryToAgendaActivity.EXTRA_CATEGORY), AgendaRequest.class);
            agendaViewModel.add(agendaRequest);
            Toast.makeText(this, "Catégorie ajoutée", Toast.LENGTH_SHORT).show();
        }
    }
}