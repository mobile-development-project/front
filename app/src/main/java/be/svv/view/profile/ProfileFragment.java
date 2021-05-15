package be.svv.view.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.svv.mobileapplication.R;
import be.svv.view.FragmentListener;

public class ProfileFragment extends Fragment
{

    private FragmentListener listener;
    private Button signOut;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        signOut = root.findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> listener.onAction());
        getActivity().setTitle("Mon profile");
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