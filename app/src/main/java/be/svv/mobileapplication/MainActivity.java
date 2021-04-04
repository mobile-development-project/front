package be.svv.mobileapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.svv.mobileapplication.course.CourseActivity;
import be.svv.mobileapplication.security.LoginActivity;

public class MainActivity extends AppCompatActivity
{

    Button signOut;
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
            Toast.makeText(MainActivity.this, "Signed out successfully !", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

}