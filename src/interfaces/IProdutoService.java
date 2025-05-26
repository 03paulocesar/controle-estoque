package interfaces;

import entidades.Produto;
import java.util.List;

public interface IProdutoService {
    boolean cadastrar(Produto produto);
    boolean remover(int id);
    boolean atualizar(Produto produto);
    List<Produto> listar();
    boolean registrarEntrada(int id, int quantidade);
    boolean registrarSaida(int id, int quantidade);
}