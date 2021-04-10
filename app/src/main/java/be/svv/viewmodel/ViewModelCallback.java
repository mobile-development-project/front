package be.svv.viewmodel;

import java.util.List;

import be.svv.model.Model;
import retrofit2.Response;

public interface ViewModelCallback<T extends Model>
{

    void onResponse (Response<List<T>> response);

}
