package modelos;

public class Admin extends Empleado {
    public Admin(String documento, String nombre, double sueldoHora){
        super(documento, nombre, sueldoHora);
    }
    @Override
    public double salarioBruto() {
        return sueldoHora * 160;
    }
}
