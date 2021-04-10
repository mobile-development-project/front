package be.svv.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import be.svv.model.Model;
import be.svv.repository.Repository;

public class EntityViewModel<T extends Model> extends ViewModel
{

    private Repository repository;
    private MutableLiveData<List<T>> datas;

    protected <T extends Repository> EntityViewModel (T repository)
    {
        this.repository = repository;
    }

}
