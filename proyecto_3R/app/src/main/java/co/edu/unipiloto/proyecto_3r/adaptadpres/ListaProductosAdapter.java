package co.edu.unipiloto.proyecto_3r.adaptadpres;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unipiloto.proyecto_3r.R;
import co.edu.unipiloto.proyecto_3r.VerActivity;
import co.edu.unipiloto.proyecto_3r.entidades.Productos;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder> {

    ArrayList<Productos> listaProductos;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null,false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(listaProductos.get(position).getId()));
        holder.txtNombreV.setText(listaProductos.get(position).getNombre());
        holder.txtDescripcionV.setText(listaProductos.get(position).getDescripcion());
        holder.txtValorV.setText(listaProductos.get(position).getValor());



    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtNombreV, txtDescripcionV,txtValorV;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtNombreV = itemView.findViewById(R.id.nombreProductoV);
            txtDescripcionV = itemView.findViewById(R.id.descripcionProductoV);
            txtValorV = itemView.findViewById(R.id.valorProductoV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID",listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
