package operaciones;

import modelos.Empresa;
import java.util.ArrayList;
import java.util.List;

public class OperacionEmpresa implements IOperacionEmpresa {
    private List<Empresa> empresas = new ArrayList<>();

    @Override
    public void agregarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    @Override
    public List<Empresa> obtenerTodasEmpresas() {
        return new ArrayList<>(empresas);
    }
}