package modelos;

/**
 * HERENCIA: GestorProyectos ES UN tipo de Empleado.
 * Tiene un atributo adicional: proyecto (del tipo enum Proyectos).
 *
 * Esta clase representa a un gestor de proyectos que trabaja en un proyecto específico.
 */
public class GestorProyectos extends Empleado {
    // ATRIBUTO: Cada gestor trabaja en UN proyecto específico
    private Proyectos proyecto;  // Tipo enum Proyectos

    /**
     * CONSTRUCTOR: Crea un nuevo Gestor de Proyectos.
     * Llama al constructor de la clase padre (Empleado) con 'super'
     *
     * @param documento Número de identificación
     * @param nombre Nombre completo
     * @param sueldoHora Valor por hora trabajada
     */
    public GestorProyectos(String documento, String nombre, double sueldoHora) {
        super(documento, nombre, sueldoHora); // super llama al constructor de Empleado
    }

    // GETTER: Devuelve el proyecto asignado al gestor
    public Proyectos getProyecto() {
        return proyecto;
    }

    // SETTER: Asigna un proyecto al gestor
    public void setProyecto(Proyectos proyecto) {
        // Solo se pueden asignar valores válidos del enum Proyectos
        this.proyecto = proyecto;
    }

    /**
     * Implementación del método abstracto de Empleado.
     * Calcula el salario bruto mensual.
     * <p>
     * POLIMORFISMO: Cada tipo de empleado implementa su propia versión.
     *
     * @return Salario bruto mensual (160 horas * sueldo por hora)
     */
    @Override
    public double salarioBruto() {
        return sueldoHora * 160; // 160 horas mensuales
    }

    /**
     * Sobreescribe cómo se muestra el objeto cuando se convierte a String.
     *
     * @return String con información del gestor y su proyecto
     */
    @Override
    public String toString() {
        // super.toString() llama al método de la clase padre
        return super.toString() + " | Proyecto: " + proyecto;
    }
}