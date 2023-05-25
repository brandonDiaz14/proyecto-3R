package co.edu.unipiloto.proyecto_3r;

public class CatalogoProducto {
    private String nombreProducto;
    private String precio;
    private String descripcion;

    private int imgProducto;

    public CatalogoProducto() {
    }

    public CatalogoProducto(String nombreProducto, String precio, String descripcion, int imgProducto) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imgProducto = imgProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(int imgProducto) {
        this.imgProducto = imgProducto;
    }
}
