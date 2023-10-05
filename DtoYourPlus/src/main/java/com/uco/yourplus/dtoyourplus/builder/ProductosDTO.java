package com.uco.yourplus.dtoyourplus.builder;

import java.util.UUID;

import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.ZERO;
import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.getDefaultString;
import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getDefaultUUID;
import static com.uco.yourplus.dtoyourplus.builder.categoria.CategoriaDTOBuilder.getCategoriaDTOBuilder;
import static com.uco.yourplus.dtoyourplus.builder.laboratorio.LaboratorioDTOBuilder.getLaboratorioDTOBuilder;

public class ProductosDTO {

    private UUID id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String imagen;
    private LaboratorioDTO laboratorio;
    private CategoriaDTO categoria;

    public ProductosDTO(){
        setId(null);
        setNombre(EMPTY);
        setPrecio(ZERO);
        setDescripcion(EMPTY);
        setImagen(EMPTY);
        setLaboratorio(new LaboratorioDTO());
        setCategoria(new CategoriaDTO());
    }

    public ProductosDTO(final UUID id, final String nombre, final int precio, final String descripcion,
                        final String imagen, final LaboratorioDTO laboratorio, final CategoriaDTO categoria){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setImagen(imagen);
        setLaboratorio(laboratorio);
        setCategoria(categoria);
    }

    public static ProductosDTO create(final UUID id, final String nombre, final int precio, final String descripcion,
                                      final String imagen, final LaboratorioDTO laboratorio, final CategoriaDTO categoria){
        return new ProductosDTO(id,nombre,precio,descripcion,imagen,laboratorio,categoria);
    }

    public static ProductosDTO create(final UUID id){
        return new ProductosDTO(id,EMPTY,ZERO,EMPTY,EMPTY,new LaboratorioDTO(),new CategoriaDTO());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = getDefaultString(nombre);
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = getDefaultIfNull(precio,ZERO);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getDefaultString(descripcion);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = getDefaultString(imagen);
    }

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = getDefaultIfNull(laboratorio,getLaboratorioDTOBuilder().build());
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = getDefaultIfNull(categoria,getCategoriaDTOBuilder().build());
    }
}
