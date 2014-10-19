package com.example.serj.myjukebox;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Disco>{
    private Context contexto;
    private ArrayList<Disco> discos;
    private int recurso;
    private static LayoutInflater i;

    public static class ViewHolder {
        public TextView tv1, tv2, tv3;
        public ImageView iv;
        public int posicion;
        public Typeface font_light, font_medium;
    }

    public Adaptador(Context context, int resource, ArrayList<Disco> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.recurso = resource;
        this.discos = objects;
        this.i = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =  null;

        if(convertView == null) {
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView)convertView.findViewById(R.id.tvTitulo);
            vh.tv2 = (TextView)convertView.findViewById(R.id.tvArtista);
            vh.tv3 = (TextView)convertView.findViewById(R.id.tvAnio);
            vh.iv = (ImageView)convertView.findViewById(R.id.ivCaratula);
            //Fuentes externas
            vh.font_light = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
            vh.font_medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-MediumItalic.ttf");
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }
        vh.posicion = position;
        vh.tv1.setText(discos.get(position).getTitulo());
        vh.tv2.setText(discos.get(position).getArtista());
        vh.tv3.setText(discos.get(position).getAnio());
        vh.iv.setImageBitmap(discos.get(position).getCaratula());
        //Cambiar fuentes
        vh.tv1.setTypeface(vh.font_medium);
        vh.tv2.setTypeface(vh.font_light);
        vh.tv3.setTypeface(vh.font_light);
        return convertView;
    }
}
