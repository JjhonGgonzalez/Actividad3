//Indica que la clase pertenece al paquete "modelos"
package modelos;

// Se declara la clase abstracta llamada empleado
public abstract class Empleado {
    // Atributos protegidos (Solo son visibles en esta clase y en sus subclases, pero no puede ser adcedido fera de su paquete)
    protected String documento;
    protected String nombre;
    protected double sueldoHora;
    protected Empresa empresa;
    // Constructor se encarga de crear los objetos de las subclases de Empleado, le pasa los parametros que trae a los atributos de la clase Empleado
    public Empleado(String documento, String nombre, double sueldoHora){
        this.documento = documento;
        this.nombre = nombre;
        this.sueldoHora = sueldoHora;
    }

    // Metodos get y set obtiene o modifica los valores de atributos privados o protegidos desde fuera de la clase
    public String getDocumento(){
        return documento;
    }
    public void setDocumento(String documento){
    this.documento = documento;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public double getsueldoHora(double sueldoHora){
        return sueldoHora;
    }
    public void setSueldoHora(double sueldoHora){
        this.sueldoHora = sueldoHora;
    }
    public Empresa getEmpresa(){return empresa;}
    public void setEmpresa(Empresa empresa){this.empresa = empresa;}
    // Metodo abstracto para calcular salario Bruto
    public abstract double salarioBruto();

    // Convierte el objeto en texto de forma legible
    @Override
    public String toString() {
        return nombre + " (" + documento +")";
    }
}
