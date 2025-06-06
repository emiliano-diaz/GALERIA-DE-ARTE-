package com.example.galeriadearte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ObrasPagerAdapter extends RecyclerView.Adapter<ObrasPagerAdapter.ViewHolder> {

    private final Context context;
    private final List<Obra> obras;

    public ObrasPagerAdapter(Context context, List<Obra> obras) {
        this.context = context;
        this.obras = obras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_obra, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Obra obra = obras.get(position);
        holder.txtTitulo.setText(obra.getTitulo());
        holder.txtAnio.setText("Año: " + obra.getAnio());
        holder.txtDescripcion.setText("Descripción: " + obra.getDescripcion());
        holder.txtPrecio.setText("Precio: $" + obra.getPrecio());
        holder.txtDuenio.setText("Dueño: " + obra.getDuenio());

        // Si usás imágenes desde assets o recurso, se puede mejorar esto
        int resId = context.getResources().getIdentifier(
                obra.getImagen().replace(".jpg", ""), "drawable", context.getPackageName());

        if (resId != 0) {
            holder.imgObra.setImageResource(resId);
        } else {
            holder.imgObra.setImageResource(R.drawable.imagen_default); // imagen por defecto
        }
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtAnio, txtDescripcion, txtPrecio, txtDuenio;
        ImageView imgObra;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.tituloObra);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtDuenio = itemView.findViewById(R.id.txtDuenio);
            imgObra = itemView.findViewById(R.id.imagenObra);
        }
    }
}
