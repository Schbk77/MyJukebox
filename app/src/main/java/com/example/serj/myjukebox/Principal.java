package com.example.serj.myjukebox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;


public class Principal extends Activity {
    /*****************************************VARIABLES********************************************/
    private Disco d1, d2, d3, d4, d5;               //Discos de ejemplo que se crean por defecto
    static ArrayList<Disco> discos;                 //Variable donde almaceno la biblioteca de discos
    static Adaptador ad;                            //Adaptador para objetos de tipo Disco
    static int aux;                                 //Variable auxiliar para saber la posición del Disco del ArrayList al que se accede

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        aux = info.position;
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
        Intent nuevoIntent = new Intent(this, MenuEditar.class);
        startActivity(nuevoIntent);
        return true;
    }

    //Método para borrar un disco ya añadido
    private boolean borrar() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(R.string.dialog_message);
        alert.setTitle(R.string.dialog_title);
        alert.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                discos.remove(aux);
                ad.notifyDataSetChanged();
                tostada("Disco borrado");
            }
        });
        alert.setNegativeButton(R.string.no,null);
        AlertDialog dialog = alert.create();
        dialog.show();
        return true;
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
        d1 = new Disco(getString(R.string.titulod1), getString(R.string.artistad1), getString(R.string.aniod1), getString(R.string.generod1), lonerism);
        d2 = new Disco(getString(R.string.titulod2), getString(R.string.artistad2), getString(R.string.aniod2), getString(R.string.generod2), ledzepiii);
        d3 = new Disco(getString(R.string.titulod3), getString(R.string.artistad3), getString(R.string.aniod3), getString(R.string.generod3), okcomp);
        d4 = new Disco(getString(R.string.titulod4), getString(R.string.artistad4), getString(R.string.aniod4), getString(R.string.generod4), ramones);
        d5 = new Disco(getString(R.string.titulod5), getString(R.string.artistad5), getString(R.string.aniod5), getString(R.string.generod5), revolver);

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

    //Comentario prueba
}
