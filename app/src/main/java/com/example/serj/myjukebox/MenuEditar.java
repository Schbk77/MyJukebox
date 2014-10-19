package com.example.serj.myjukebox;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuEditar extends Activity {

    private ImageButton ib;
    private TextView tv1, tv2, tv3, tv4;
    private static int RESULT_LOAD_IMAGE = 1;
    private String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menueditar);
        mostrarDatos();
        ImageButton cargarImagen = (ImageButton)findViewById(R.id.ibEditarCaratula);
        cargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    public void mostrarDatos() {
        ib = (ImageButton)findViewById(R.id.ibEditarCaratula);
        tv1 = (TextView)findViewById(R.id.etEditarTitulo);
        tv2 = (TextView)findViewById(R.id.etEditarArtista);
        tv3 = (TextView)findViewById(R.id.etEditarAnio);
        tv4 = (TextView)findViewById(R.id.etEditarGenero);

        //Mostrar Disco
        ib.setImageBitmap(Principal.discos.get(Principal.aux).getCaratula());
        tv1.setText(Principal.discos.get(Principal.aux).getTitulo());
        tv2.setText(Principal.discos.get(Principal.aux).getArtista());
        tv3.setText(Principal.discos.get(Principal.aux).getAnio());
        tv4.setText(Principal.discos.get(Principal.aux).getGenero());
    }

    public void guardarCambios(View v) {
        Principal.discos.get(Principal.aux).setTitulo(tv1.getText().toString());
        Principal.discos.get(Principal.aux).setArtista(tv2.getText().toString());
        Principal.discos.get(Principal.aux).setAnio(tv3.getText().toString());
        Principal.discos.get(Principal.aux).setGenero(tv4.getText().toString());
        Principal.discos.get(Principal.aux).setCaratula(BitmapFactory.decodeFile(picturePath));
        Toast.makeText(this, getString(R.string.discomodificado), Toast.LENGTH_SHORT).show();
        Principal.ad.notifyDataSetChanged();
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            ib.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
