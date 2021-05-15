package be.svv.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.svv.model.Agenda;
import be.svv.model.request.DeleteCategoryRequest;
import be.svv.repository.CalendarRepository;
import be.svv.repository.RepositoryFactory;
import retrofit2.Response;

public class AgendaViewModel extends EntityViewModel<Agenda>
{

    private CalendarRepository api = (CalendarRepository) RepositoryFactory.getInstance().getRepository(Agenda.class);
    private MutableLiveData<List<Agenda>> events;

    public AgendaViewModel ()
    {
        super(RepositoryFactory.getInstance().getRepository(Agenda.class));
        events = new MutableLiveData<>();
    }

    public void deleteCategory (int id, DeleteCategoryRequest deleteCategoryRequest)
    {
        api.deleteCategory(id, deleteCategoryRequest, new ViewModelCallback<Agenda>()
        {
            @Override
            public void onResponse (Response<List<Agenda>> response)
            {
                populateList();
            }
        });
    }

}
