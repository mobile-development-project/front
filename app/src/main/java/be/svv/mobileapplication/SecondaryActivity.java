package be.svv.mobileapplication;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity
{

    TextView name, email, id;
    Button signOut;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
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

        if (acct != null)
        {
            String personName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();

            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
        }

    }

    private void signOut ()
    {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete (@NonNull Task<Void> task)
            {
                Toast.makeText(SecondaryActivity.this, "Signed out successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}