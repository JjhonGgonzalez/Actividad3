package operaciones;

import modelos.Empleado;
import java.util.List;

/**
 * INTERFACE: Define un contrato de métodos que deben implementarse.
 * No contiene código, solo firmas de métodos.
 */
public interface IOperacionEmpleado {
    // Método para agregar un empleado
    void agregarEmpleado(Empleado empleado);

    // Método para obtener todos los empleados
    List<Empleado> obtenerTodosEmpleados();

    // Método para buscar empleado por documento
    Empleado buscarPorDocumento(String documento);

    // Método para calcular salario de un empleado
    double calcularSalarioEmpleado(String documento);

    // Método para contar empleados en una empresa
    int contarEmpleadosEnEmpresa(String nit);
}