package be.svv.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import be.svv.mobileapplication.R;

public class AddCategoryActivity extends AppCompatActivity
{

    private EditText editTextName;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        editTextName = findViewById(R.id.edit_text_category_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle("Ajouter une catégorie");
    }

    private void saveCourse ()
    {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty())
        {
            Toast.makeText(this, "Le nom ne peut pas être vide!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra("NAME", name);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save:
                saveCourse();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}