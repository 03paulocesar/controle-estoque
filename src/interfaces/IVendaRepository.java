package interfaces;

import entidades.Venda;
import java.util.List;

public interface IVendaRepository {
    void salvar(Venda venda);
    List<Venda> listar();
}