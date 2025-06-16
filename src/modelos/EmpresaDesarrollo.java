package modelos;
//Hereda EmpresaDesarrollo es un tipo de Empresa
public class EmpresaDesarrollo extends Empresa {
    // Constructor llama al constructor de la clase padre
    public EmpresaDesarrollo(String nit, String nombre, String direccion, String ciudad) {
        super(nit, nombre, direccion, ciudad);
    }
}