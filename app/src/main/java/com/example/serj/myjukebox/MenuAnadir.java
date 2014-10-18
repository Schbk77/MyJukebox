package com.example.serj.myjukebox;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuAnadir extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuanadir);

    }

    public void anadirDisco(View v) {
        Principal.titulo = ((EditText)findViewById(R.id.etTitulo)).getText().toString();
        Principal.artista = ((EditText)findViewById(R.id.etArtista)).getText().toString();
        Principal.anio = ((EditText)findViewById(R.id.etAnio)).getText().toString();
        Principal.genero = ((EditText)findViewById(R.id.etGenero)).getText().toString();
        Bitmap vinilo = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.vinilo);
        Disco nuevoDisco = new Disco(Principal.titulo, Principal.artista, Principal.anio, Principal.genero, vinilo);
        Principal.discos.add(nuevoDisco);
        Toast.makeText(this, "Disco añadido", Toast.LENGTH_SHORT).show();
        Principal.ad.notifyDataSetChanged();
        this.finish();
    }
}
