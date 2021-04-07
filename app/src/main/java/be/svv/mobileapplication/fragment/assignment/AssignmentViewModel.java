package be.svv.mobileapplication.fragment.assignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssignmentViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public AssignmentViewModel ()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is assignement fragment");
    }

    public LiveData<String> getText ()
    {
        return mText;
    }
}