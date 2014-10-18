package com.example.serj.myjukebox;

import android.graphics.Bitmap;

public class Disco {
    private String titulo;
    private String artista;
    private String anio;
    private String genero;
    private Bitmap caratula;

    public Disco(String titulo, String artista, String anio, String genero, Bitmap caratula) {
        this.titulo = titulo;
        this.artista = artista;
        this.anio = anio;
        this.genero = genero;
        this.caratula = caratula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Bitmap getCaratula() {
        return caratula;
    }

    public void setCaratula(Bitmap caratula) {
        this.caratula = caratula;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", anio='" + anio + '\'' +
                ", genero='" + genero + '\'' +
                ", caratula=" + caratula +
                '}';
    }
}
