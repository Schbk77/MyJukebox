package com.example.serj.myjukebox;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Principal extends Activity {
    /*****************************************VARIABLES********************************************/
    static ArrayList<Disco> discos;                 //Variable donde almaceno la biblioteca de discos
    private Disco d1, d2, d3, d4, d5;               //Discos de ejemplo que se crean por defecto
    static Adaptador ad;                            //Adaptador para objetos de tipo Disco
    static int aux;                                 //Variable auxiliar para saber la posición del Disco del ArrayList al que se accede
    static String titulo, artista, anio, genero;    //Variables estáticas auxiliares para acceder desde otra clase

    /********************************************ON...*********************************************/
    //PANTALLA PRINCIPAL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        initComponents();
    }

    //ACTION BAR
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
        if (id == R.id.action_anadir) {
            return anadir();
        }
        return super.onOptionsItemSelected(item);
    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.opEditar){
            return editar();
        }else if(id == R.id.opBorrar) {
            return borrar();
        }
        return super.onContextItemSelected(item);
    }

    /**********************************METODOS*****************************************************/
    //Método para añadir discos al ArrayList mediante la opcion de la ActionBar
    private boolean anadir() {
        Intent nuevoIntent = new Intent(this, MenuAnadir.class);
        startActivity(nuevoIntent);
        return true;
    }

    //Método para editar un disco ya añadido
    private boolean editar() {

    }

    //Método para borrar un disco ya añadido
    private boolean borrar() {

    }

    //Método para mostrar los detalles de un disco
    private boolean mostrarDisco(int pos) {
        Intent nuevoIntent = new Intent(this, MostrarDisco.class);
        startActivity(nuevoIntent);
        aux = pos;                              //Para acceder a la posicion del disco dentro del ArrayList dentro de otra clase
        return true;
    }

    //Método inicial para añadirle el Adaptador al ListView con algunos CDs por defecto
    private void initComponents() {
        final ListView lv = (ListView)findViewById(R.id.lvLista);
        discos = new ArrayList<Disco>();
        setDefaultCDS();
        ad = new Adaptador(this, R.layout.lista, discos);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarDisco(i);
            }
        });
        registerForContextMenu(lv);
    }

    /************************************MÉTODOS ADICIONALES***************************************/
    private void setDefaultCDS() {
        //Convertimos las imagenes a Bitmap
        Bitmap lonerism = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.lnrsm);
        Bitmap ledzepiii = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.lziii);
        Bitmap okcomp = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ok);
        Bitmap ramones = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.rmns);
        Bitmap revolver = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.rvlvr);

        //Creamos los Discos por defecto(AÑADIR A STRINGS.XML!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
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
