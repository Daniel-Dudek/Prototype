package es.businessmind.prototype;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static final String ITEM_KEY = "item_key";
    private List<Items> itemsList;
    private Context context;

    public RecyclerViewAdapter(List<Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Items item = itemsList.get(position);

        holder.titulo.setText(itemsList.get(position).getTitleItem());
        holder.precio.setText(itemsList.get(position).getPrecio());
        holder.descripcion.setText(itemsList.get(position).getDescripcion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsItems.class);

                intent.putExtra(ITEM_KEY, item.getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
         return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, precio, descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tituloItem);
            precio = itemView.findViewById(R.id.txt_precio);
            descripcion = itemView.findViewById(R.id.descripcionItem);
        }
    }
}
