package com.example.galeriadearte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ObraAdapter extends ArrayAdapter<Obra> {
    private Context context;
    private List<Obra> obras;

    public ObraAdapter(Context context, List<Obra> obras) {
        super(context, 0, obras);
        this.context = context;
        this.obras = obras;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Obra obra = obras.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_obra, parent, false);
        }

        TextView titulo = convertView.findViewById(R.id.tituloObra);
        ImageView imagen = convertView.findViewById(R.id.imagenObra);

        titulo.setText(obra.getTitulo());
        String nombreImagen = obra.getImagen().replace(".jpg", ""); // Elimina la extensión
        int resId = context.getResources().getIdentifier(nombreImagen, "drawable", context.getPackageName());


        if (resId != 0) {
            imagen.setImageResource(resId);
        } else {
            imagen.setImageResource(R.drawable.imagen_default); // una imagen de respaldo
        }

        return convertView;
    }
}
