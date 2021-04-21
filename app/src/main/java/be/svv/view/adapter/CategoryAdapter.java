package be.svv.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{

    private List<Category> categories;
    private Context context;

    public CategoryAdapter ()
    {
        categories = new ArrayList<>();
    }

    public void setCategories (List<Category> categories)
    {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category_adapter, parent, false);
        context = view.getContext();
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull CategoryAdapter.CategoryViewHolder holder, int position)
    {
        Category currentItem = categories.get(position);
        holder.name.setText(currentItem.getName());

        holder.categorySettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                PopupMenu popup = new PopupMenu(context, holder.categorySettings);
                popup.inflate(R.menu.options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick (MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.menu_edit:
                                Toast.makeText(context, "Edit...", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu_delete:
                                Toast.makeText(context, "delete...", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount ()
    {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, categorySettings;

        public CategoryViewHolder (@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.category_name);
            categorySettings = itemView.findViewById(R.id.category_setting);
        }
    }
}
