package be.svv.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Category;
import be.svv.model.Course;
import be.svv.viewmodel.CategoryViewModel;
import be.svv.viewmodel.CourseViewModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{

    private List<Category> categories;
    private OnItemClickListener listener;
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
        CategoryViewModel categoryViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CategoryViewModel.class);
        holder.name.setText(currentItem.getName());
        holder.selectedColor.setBackgroundColor(currentItem.getColor());
        holder.categorySettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
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
                                int position = categories.indexOf(currentItem);
                                if (listener != null && position != RecyclerView.NO_POSITION)
                                {
                                    listener.onItemClick(currentItem);
                                }
                                return true;
                            case R.id.menu_delete:
                                alertDialog.setTitle("Supression");
                                alertDialog.setMessage("Voulez-vous vraiment supprimer ce cours ?");
                                alertDialog.setCancelable(true);

                                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick (DialogInterface dialog, int which)
                                    {
                                        categoryViewModel.delete(currentItem.getId());
                                        notifyItemRemoved(categories.indexOf(currentItem));
                                        categories.remove(currentItem);
                                        Toast.makeText(context, "Categorie supprimée", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                alertDialog.setNeutralButton("Annuler", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick (DialogInterface dialog, int which)
                                    {
                                        Toast.makeText(context, "Action annulée", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog alert = alertDialog.create();
                                alert.show();

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
        View selectedColor;

        public CategoryViewHolder (@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.category_name);
            categorySettings = itemView.findViewById(R.id.category_setting);
            selectedColor = itemView.findViewById(R.id.selected_color_adapter);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick (Category category);
    }

    public void setOnItemClickListener (OnItemClickListener listener)
    {
        this.listener = listener;
    }
}
