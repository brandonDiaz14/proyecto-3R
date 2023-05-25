package co.edu.unipiloto.proyecto_3r;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView producto,descripcion,precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            producto = (TextView) itemView.findViewById(R.id.txtNombreProducto);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescripcionP);
            precio = (TextView)  itemView.findViewById(R.id.txtPrecio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgProducto);
        }
    }

    public List<CatalogoProducto> listaCatalogo;

    public RecyclerViewAdaptador(List<CatalogoProducto> listaCatalogo) {
        this.listaCatalogo = listaCatalogo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.producto.setText(listaCatalogo.get(position).getNombreProducto());
        holder.descripcion.setText(listaCatalogo.get(position).getDescripcion());
        holder.precio.setText(listaCatalogo.get(position).getPrecio());
        holder.fotoProducto.setImageResource(listaCatalogo.get(position).getImgProducto());

    }

    @Override
    public int getItemCount() {
        return listaCatalogo.size();
    }
}
