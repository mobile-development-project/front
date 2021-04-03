package be.svv.mobileapplication;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.svv.entity.Assignment;
import be.svv.entity.Course;
import be.svv.entity.Entity;
import be.svv.entity.User;
import be.svv.globals.ApiUrl;
import be.svv.repository.CategoryRepository;
import be.svv.service.Volley.VolleyCallback;

public class SecondaryActivity extends AppCompatActivity
{

    TextView name, email, id;
    Button signOut;
    Course course;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(v ->
        {
            switch (v.getId())
            {
                case R.id.signOut:
                    signOut();
                    break;
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
    }

    public void goToCourses (View view)
    {
        startActivity(new Intent(this, CourseActivity.class));
    }

    private void signOut ()
    {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task ->
        {
            Toast.makeText(SecondaryActivity.this, "Signed out successfully !", Toast.LENGTH_LONG).show();
            finish();
        });
    }

}