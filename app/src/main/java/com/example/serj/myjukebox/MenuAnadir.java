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

    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuanadir);
        et1 = (EditText)findViewById(R.id.etTitulo);
        et2 = (EditText)findViewById(R.id.etArtista);
        et3 = (EditText)findViewById(R.id.etAnio);
        et4 = (EditText)findViewById(R.id.etGenero);
    }

    public void anadirDisco(View v) {
        if (!((String)et1.getText().toString()).isEmpty() &&
                !((String)et2.getText().toString()).isEmpty() &&
                !((String)et3.getText().toString()).isEmpty() &&
                !((String)et4.getText().toString()).isEmpty()) {
            String titulo = et1.getText().toString();
            String artista = et2.getText().toString();
            String anio = et3.getText().toString();
            String genero = et4.getText().toString();
            Bitmap vinilo = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.vinilo);
            Disco nuevoDisco = new Disco(titulo, artista, anio, genero, vinilo);
            Principal.discos.add(nuevoDisco);
            Toast.makeText(this, getString(R.string.discoaniadido), Toast.LENGTH_SHORT).show();
            Principal.ad.notifyDataSetChanged();
            this.finish();
        }else {
            Toast.makeText(this, getString(R.string.tostadaaniadirerror), Toast.LENGTH_SHORT).show();
        }
    }
}
