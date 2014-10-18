package com.example.serj.myjukebox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MostrarDisco extends Activity {

    private ImageView iv;
    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrardisco);

        iv = (ImageView)findViewById(R.id.ivMostrarCaratula);
        tv1 = (TextView)findViewById(R.id.tvMostrarTitulo);
        tv2 = (TextView)findViewById(R.id.tvMostrarArtista);
        tv3 = (TextView)findViewById(R.id.tvMostrarAnio);
        tv4 = (TextView)findViewById(R.id.tvMostrarGenero);

        iv.setImageBitmap(Principal.discos.get(Principal.aux).getCaratula());
        tv1.setText(Principal.discos.get(Principal.aux).getTitulo());
        tv2.setText(Principal.discos.get(Principal.aux).getArtista());
        tv3.setText(Principal.discos.get(Principal.aux).getAnio());
        tv4.setText(Principal.discos.get(Principal.aux).getGenero());
    }
}
