package be.svv.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import be.svv.model.Model;
import be.svv.model.request.ModelRequest;
import be.svv.repository.RepositoryInterface;
import retrofit2.Response;

public class EntityViewModel<T extends Model> extends ViewModel
{

    protected RepositoryInterface repositoryInterface;
    private MutableLiveData<List<T>> datas;

    protected <R extends RepositoryInterface> EntityViewModel (R repository)
    {
        this.repositoryInterface = repository;
        datas = new MutableLiveData<>();
        populateList();
    }

    public void populateList ()
    {
        repositoryInterface.getAll(new ViewModelCallback<T>()
        {
            @Override
            public void onResponse (Response<List<T>> response)
            {
                datas.setValue(response.body());
            }
        });
    }

    public MutableLiveData<List<T>> getAll ()
    {
        return datas;
    }

    public <T extends ModelRequest> void add (T request)
    {
        repositoryInterface.add(request, new ViewModelCallback()
        {
            @Override
            public void onResponse (Response response)
            {
                populateList();
            }
        });
    }

    public <T extends ModelRequest> void update (T request, int id)
    {
        repositoryInterface.update(request, id, new ViewModelCallback()
        {
            @Override
            public void onResponse (Response response)
            {
                populateList();
            }
        });
    }

    public void delete (int id)
    {
        repositoryInterface.delete(id, new ViewModelCallback()
        {
            @Override
            public void onResponse (Response response)
            {
                populateList();
            }
        });
    }

}
