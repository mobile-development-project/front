package be.svv.view.category;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import be.svv.mobileapplication.R;
import be.svv.model.Category;
import be.svv.model.Course;
import be.svv.model.request.CategoryRequest;
import be.svv.service.gson.GsonSingleton;
import top.defaults.colorpicker.ColorPickerPopup;

public class AddCategoryActivity extends AppCompatActivity
{

    public static final String EXTRA_CATEGORY = "CATEGORY";
    public static final String EXTRA_ID = "ID";

    private EditText editTextName;
    private Button buttonChoseColor;
    private View selectedColor;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        editTextName = findViewById(R.id.edit_text_category_name);
        buttonChoseColor = findViewById(R.id.pick_color_button);
        selectedColor = findViewById(R.id.preview_selected_color);

        buttonChoseColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                new ColorPickerPopup.Builder(AddCategoryActivity.this).initialColor(Color.RED)
                        .okTitle("Choisir")
                        .cancelTitle("Annuler")
                        .showValue(true)
                        .build()
                        .show(v, new ColorPickerPopup.ColorPickerObserver()
                        {
                            @Override
                            public void onColorPicked (int color)
                            {
                                selectedColor.setBackgroundColor(color);
                            }
                        });
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();

        // If a category is parsed in the intent, set the value of each fields
        if (intent.hasExtra(EXTRA_CATEGORY))
        {
            setTitle("Modifier une catégorie");
            Category category = GsonSingleton.getInstance().fromJson(intent.getStringExtra(EXTRA_CATEGORY), Category.class);
            editTextName.setText(category.getName());
            selectedColor.setBackgroundColor(category.getColor());
        }
        else
        {
            setTitle("Ajouter une catégorie");
        }
    }

    private void saveCourse ()
    {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty())
        {
            Toast.makeText(this, "Le nom ne peut pas être vide!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("TAG", ((ColorDrawable) selectedColor.getBackground()).getColor() + "");

        Intent data = new Intent();
        data.putExtra(EXTRA_CATEGORY, GsonSingleton.getInstance()
                .toJson(new CategoryRequest(name, ((ColorDrawable) selectedColor.getBackground()).getColor())));
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1)
        {
            data.putExtra(EXTRA_ID, id);
        }

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