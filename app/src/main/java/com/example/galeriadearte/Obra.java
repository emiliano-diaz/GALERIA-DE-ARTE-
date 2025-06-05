package com.example.galeriadearte;

public class Obra {
    private int id;
    private String titulo;
    private int anio;
    private String descripcion;
    private double precio;
    private String duenio;
    private String imagen;
    private int idArtista;

    // Constructor
    public Obra(int id, String titulo, int anio, String descripcion, double precio, String duenio, String imagen, int idArtista) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duenio = duenio;
        this.imagen = imagen;
        this.idArtista = idArtista;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getAnio() { return anio; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getDuenio() { return duenio; }
    public String getImagen() { return imagen; }
    public int getIdArtista() { return idArtista; }
}
