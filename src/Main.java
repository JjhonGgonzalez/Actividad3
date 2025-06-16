import javax.swing.JOptionPane;
import modelos.*;
import operaciones.*;
import java.util.List;

/**
 * Clase principal con método main
 * SE usa JOptionPane para interfaz gráfica simple.
 */
public class Main {
    // INTERFACES COMO TIPO: Permite cambiar implementación fácilmente
    private static IOperacionEmpresa operacionEmpresa = new OperacionEmpresa();
    private static IOperacionEmpleado operacionEmpleado = new OperacionEmpleado();

    public static void main(String[] args) {
        int opcion;
        do {
            // Menú principal usando JOptionPane
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "MENÚ PRINCIPAL\n\n" +
                                "1. Crear empresa\n" +
                                "2. Crear empleado\n" +
                                "3. Listar empresas\n" +
                                "4. Listar empleados\n" +
                                "5. Buscar empleado por documento\n" +
                                "6. Calcular salario de empleado\n" +
                                "7. Contar empleados por empresa\n" +
                                "8. Salir\n\n" +
                                "Seleccione una opción:"
                ));
            } catch (NumberFormatException e) {
                opcion = 0; // Si el usuario ingresa algo no numérico
            }

            switch (opcion) {
                case 1: crearEmpresa(); break;
                case 2: crearEmpleado(); break;
                case 3: listarEmpresas(); break;
                case 4: listarEmpleados(); break;
                case 5: buscarEmpleado(); break;
                case 6: calcularSalario(); break;
                case 7: contarEmpleados(); break;
                case 8: JOptionPane.showMessageDialog(null, "Saliendo del sistema"); break;
                default: JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 8); // Repetir hasta que elija salir
    }

    /**
     * Crea una nueva empresa y la agrega al sistema.
     * Solicita NIT, nombre, dirección y ciudad.
     */
    private static void crearEmpresa() {
        String nit = JOptionPane.showInputDialog("Ingrese NIT:");
        if (nit == null || nit.isEmpty()) return; // Si usuario cancela

        String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
        if (nombre == null || nombre.isEmpty()) return;

        String direccion = JOptionPane.showInputDialog("Ingrese dirección:");
        if (direccion == null || direccion.isEmpty()) return;

        String ciudad = JOptionPane.showInputDialog("Ingrese ciudad:");
        if (ciudad == null || ciudad.isEmpty()) return;

        // Crear nueva empresa y agregar a operaciones
        Empresa empresa = new Empresa(nit, nombre, direccion, ciudad);
        operacionEmpresa.agregarEmpresa(empresa);
        JOptionPane.showMessageDialog(null, "Empresa creada exitosamente!");
    }

    /**
     * Crea un nuevo empleado y lo asigna a una empresa.
     * Solicita documento, nombre, sueldo por hora y tipo de empleado.
     * Para gestores de proyectos, permite seleccionar un proyecto.
     */
    private static void crearEmpleado() {
        String documento = JOptionPane.showInputDialog("Ingrese documento:");
        if (documento == null || documento.isEmpty()) return;

        String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
        if (nombre == null || nombre.isEmpty()) return;

        double sueldoHora = 0;
        try {
            sueldoHora = Double.parseDouble(JOptionPane.showInputDialog("Sueldo por hora:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido. Se usará 0 como valor predeterminado.");
        }

        // Selección de tipo de empleado
        String tipo = JOptionPane.showInputDialog(
                "Tipo de empleado:\n" +
                        "1. Desarrollador\n" +
                        "2. Gestor de Proyectos\n" +
                        "3. Admin"
        );
        if (tipo == null) return;

        Empleado empleado = null;

        switch (tipo) {
            case "1":
                empleado = new Desarrollador(documento, nombre, sueldoHora);
                break;
            case "2":
                empleado = new GestorProyectos(documento, nombre, sueldoHora);
                // Mostrar proyectos disponibles
                StringBuilder proyectos = new StringBuilder("PROYECTOS DISPONIBLES:\n");
                for (Proyectos p : Proyectos.values()) {
                    proyectos.append("- ").append(p.name()).append("\n");
                }
                String proyectoStr = JOptionPane.showInputDialog(
                        proyectos.toString() +
                                "\nIngrese nombre del proyecto:"
                );

                if (proyectoStr == null || proyectoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Proyecto no asignado. Se usará por defecto.");
                    ((GestorProyectos) empleado).setProyecto(Proyectos.SISTEMA_GESTION);
                } else {
                    try {
                        Proyectos proyecto = Proyectos.valueOf(proyectoStr.toUpperCase());
                        ((GestorProyectos) empleado).setProyecto(proyecto);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Proyecto inválido. Se asignará SISTEMA_GESTION.");
                        ((GestorProyectos) empleado).setProyecto(Proyectos.SISTEMA_GESTION);
                    }
                }
                break;
            case "3":
                empleado = new Admin(documento, nombre, sueldoHora);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Se creará como Desarrollador");
                empleado = new Desarrollador(documento, nombre, sueldoHora);
        }

        // Asignar empresa al empleado
        String nit = JOptionPane.showInputDialog("Ingrese NIT de la empresa:");
        if (nit == null || nit.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empleado no asignado a empresa!");
            return;
        }

        // Buscar empresa por NIT
        List<Empresa> empresas = operacionEmpresa.obtenerTodasEmpresas();
        Empresa empresa = null;
        for (Empresa e : empresas) {
            if (e.getNit().equals(nit)) {
                empresa = e;
                break;
            }
        }

        if (empresa != null) {
            empleado.setEmpresa(empresa);
            operacionEmpleado.agregarEmpleado(empleado);
            JOptionPane.showMessageDialog(null, "Empleado creado exitosamente!");
        } else {
            JOptionPane.showMessageDialog(null, "Empresa no encontrada! Empleado no creado.");
        }
    }

    /**
     * Muestra todas las empresas registradas en el sistema.
     */
    private static void listarEmpresas() {
        List<Empresa> empresas = operacionEmpresa.obtenerTodasEmpresas();
        if (empresas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empresas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("EMPRESAS REGISTRADAS:\n\n");
        for (Empresa e : empresas) {
            sb.append(e.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    /**
     * Muestra todos los empleados registrados en el sistema.
     * Para gestores de proyectos, muestra el proyecto asignado.
     */
    private static void listarEmpleados() {
        List<Empleado> empleados = operacionEmpleado.obtenerTodosEmpleados();
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("EMPLEADOS REGISTRADOS:\n\n");
        for (Empleado e : empleados) {
            sb.append(e.toString());

            // Mostrar proyecto si es gestor
            if (e instanceof GestorProyectos) {
                sb.append(" | Proyecto: ").append(((GestorProyectos) e).getProyecto());
            }

            sb.append(" | Empresa: ").append(e.getEmpresa().getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    /**
     * Busca un empleado por su documento y muestra sus detalles.
     */
    private static void buscarEmpleado() {
        String documento = JOptionPane.showInputDialog("Ingrese documento del empleado:");
        if (documento == null || documento.isEmpty()) return;

        Empleado empleado = operacionEmpleado.buscarPorDocumento(documento);
        if (empleado == null) {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado");
            return;
        }

        StringBuilder info = new StringBuilder("EMPLEADO ENCONTRADO:\n\n");
        info.append(empleado.toString());

        if (empleado instanceof GestorProyectos) {
            info.append("\nProyecto: ").append(((GestorProyectos) empleado).getProyecto());
        }

        info.append("\nEmpresa: ").append(empleado.getEmpresa().getNombre());
        JOptionPane.showMessageDialog(null, info.toString());
    }

    /**
     * Calcula y muestra el salario bruto de un empleado.
     */
    private static void calcularSalario() {
        String documento = JOptionPane.showInputDialog("Ingrese documento del empleado:");
        if (documento == null || documento.isEmpty()) return;

        double salario = operacionEmpleado.calcularSalarioEmpleado(documento);
        if (salario <= 0) {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado");
        } else {
            JOptionPane.showMessageDialog(null, "Salario bruto: $" + salario);
        }
    }

    /**
     * Cuenta y muestra la cantidad de empleados en una empresa específica.
     */
    private static void contarEmpleados() {
        String nit = JOptionPane.showInputDialog("Ingrese NIT de la empresa:");
        if (nit == null || nit.isEmpty()) return;

        int cantidad = operacionEmpleado.contarEmpleadosEnEmpresa(nit);
        JOptionPane.showMessageDialog(null, "Total empleados en la empresa: " + cantidad);
    }
}