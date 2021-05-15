package be.svv.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.InstanceIdResult;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import be.svv.mobileapplication.R;
import be.svv.view.profile.ProfileFragment;
import be.svv.view.security.LoginActivity;

public class MainActivity extends AppCompatActivity implements FragmentListener
{

    private static final String DATA_FRAGMENT = "DATA_FRAGMENT";
    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    private static final String PROFILE_FRAGMENT = "PROFILE_FRAGMENT";

    private GoogleSignInClient mGoogleSignInClient;
    private BottomNavigationView bottomNavigationView;

    private DataFragment dataFragment;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        if (savedInstanceState != null)
        {
            dataFragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DATA_FRAGMENT);
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);
            profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(PROFILE_FRAGMENT);
        }
        else
        {
            dataFragment = new DataFragment();
            homeFragment = new HomeFragment();
            profileFragment = new ProfileFragment();
        }

        //        if (!dataFragment.isInLayout())
        //        {
        //            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME_FRAGMENT).commit();
        //        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME_FRAGMENT).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, homeFragment, HOME_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.navigation_course:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, dataFragment, DATA_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, profileFragment, PROFILE_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
            return true;
        });

        //GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
    }


    @Override
    public void onBackPressed ()
    {
        if (bottomNavigationView.getSelectedItemId() == R.id.navigation_home)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    public void onAction ()
    {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Toast.makeText(MainActivity.this, "Vous êtes déconnecté!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

}