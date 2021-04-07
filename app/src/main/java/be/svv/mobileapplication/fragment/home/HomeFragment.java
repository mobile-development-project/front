package be.svv.mobileapplication.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import be.svv.mobileapplication.R;
import be.svv.mobileapplication.fragment.FragmentListener;

public class HomeFragment extends Fragment
{

    private FragmentListener listener;
    private HomeViewModel homeViewModel;
    private Button signOut;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        signOut = root.findViewById(R.id.signOut);
        signOut.setOnClickListener(v -> listener.onAction());

        return root;
    }

    @Override
    public void onAttach (@NonNull Context context)
    {
        super.onAttach(context);
        if (context instanceof FragmentListener)
        {
            listener = (FragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implements FragmentListener");
        }
    }

    @Override
    public void onDetach ()
    {
        super.onDetach();
        listener = null;
    }
}