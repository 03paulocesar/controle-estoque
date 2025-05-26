package interfaces;

import entidades.Produto;
import java.util.List;

public interface IProdutoRepository {
    void salvar(Produto produto);
    void remover(int id);
    void alterar(Produto produto);
    List<Produto> listar();
    Produto buscarPorId(int id);
}