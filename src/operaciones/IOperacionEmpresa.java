package operaciones;

import modelos.Empresa;
import java.util.List;

public interface IOperacionEmpresa {
    void agregarEmpresa(Empresa empresa);
    List<Empresa> obtenerTodasEmpresas();
}