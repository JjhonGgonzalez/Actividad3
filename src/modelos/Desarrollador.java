package modelos;
//Desarrollador es un tipo de empleado que hereda todos los atributos y metodos de Empleado
public class Desarrollador  extends Empleado{

    // Constructor recibe los mismos parametros de la clase padre "Empleado"
    public Desarrollador(String documento, String nombre, double sueldoHora){
        // super es usado para llamar el constructor de Empleado
        super(documento, nombre, sueldoHora);
    }

     @Override
    // Implementacion del metodo salarioBruto(), teniendo en cuenta que el salario puede variar entre empleados
public double salarioBruto(){
        return (float) (sueldoHora * 205);
     }
}
