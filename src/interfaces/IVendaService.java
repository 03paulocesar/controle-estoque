package interfaces;

import entidades.Venda;
import java.util.List;

public interface IVendaService {
    void registrarVenda(Venda venda);
    List<Venda> listarVendas();
}