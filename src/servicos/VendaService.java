package servicos;

import entidades.Venda;
import interfaces.IVendaRepository;
import interfaces.IVendaService;
import java.util.List;

public class VendaService implements IVendaService {
    private IVendaRepository repo;

    public VendaService(IVendaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void registrarVenda(Venda venda) {
        repo.salvar(venda);
    }

    @Override
    public List<Venda> listarVendas() {
        return repo.listar();
    }
}