package com.example.serj.myjukebox;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Principal extends Activity {

    private ArrayList<Disco> discos;    //Variable donde almaceno la biblioteca de discos
    private Disco d1, d2, d3, d4, d5;   //Discos de ejemplo que se crean por defecto
    private Adaptador ad;               //Adaptador para objetos de tipo Disco

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        initComponents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents() {
        final ListView lv = (ListView)findViewById(R.id.lvLista);
        discos = new ArrayList<Disco>();
        setDefaultCDS();
        ad = new Adaptador(this, R.layout.lista, discos);
        lv.setAdapter(ad);
        registerForContextMenu(lv);
    }

    private void setDefaultCDS() {
        //Convertimos las imagenes a Bitmap
        Bitmap lonerism = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.lnrsm);
        Bitmap ledzepiii = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.lziii);
        Bitmap okcomp = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ok);
        Bitmap ramones = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.rmns);
        Bitmap revolver = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.rvlvr);

        //Creamos los Discos por defecto
        d1 = new Disco("Lonerism", "Tame Impala", "2012", "Psychedelic", lonerism);
        d2 = new Disco("Led Zeppelin III", "Led Zeppelin", "1970", "Rock", ledzepiii);
        d3 = new Disco("OK Computer", "Radiohead", "1997", "Alternative", okcomp);
        d4 = new Disco("Ramones", "Ramones", "1976", "Punk", ramones);
        d5 = new Disco("Revolver", "The Beatles", "1966", "Psychedelic", revolver);

        //Añadimos los Discos al ArrayList
        discos.add(d1);
        discos.add(d2);
        discos.add(d3);
        discos.add(d4);
        discos.add(d5);
    }

    private void tostada(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
