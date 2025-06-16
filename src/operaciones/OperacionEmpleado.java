package operaciones;

import modelos.Empleado;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa la interface IOperacionEmpleado.
 * Debe codificar todos los métodos definidos en la interface.
 */
public class OperacionEmpleado implements IOperacionEmpleado {
    // ArrayList: Lista dinámica para almacenar empleados
    private List<Empleado> empleados = new ArrayList<>();

    @Override
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado); // Añade empleado a la lista
    }

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        // Devuelve una copia de la lista para proteger los datos originales
        return new ArrayList<>(empleados);
    }

    @Override
    public Empleado buscarPorDocumento(String documento) {
        // PROGRAMACIÓN FUNCIONAL: Streams y Lambdas
        // Filtra la lista buscando coincidencia en documento
        return empleados.stream()
                .filter(e -> e.getDocumento().equals(documento))
                .findFirst() // Toma el primer resultado
                .orElse(null); // Si no encuentra, devuelve null
    }

    @Override
    public double calcularSalarioEmpleado(String documento) {
        Empleado empleado = buscarPorDocumento(documento);
        if (empleado != null) {
            return empleado.salarioBruto(); // Polimorfismo: llama al método correcto
        }
        return 0; // Si no existe el empleado
    }

    @Override
    public int contarEmpleadosEnEmpresa(String nit) {
        // Cuenta empleados cuyo NIT de empresa coincide
        return (int) empleados.stream()
                .filter(e -> e.getEmpresa() != null && e.getEmpresa().getNit().equals(nit))
                .count(); // Cuantos elementos cumplen la condición
    }
}