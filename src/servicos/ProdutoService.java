package servicos;

import entidades.Produto;
import interfaces.IProdutoRepository;
import interfaces.IProdutoService;
import java.util.List;

public class ProdutoService implements IProdutoService {
    private IProdutoRepository repo;

    public ProdutoService(IProdutoRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean cadastrar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty() ||
            produto.getQuantidade() < 0 || produto.getValor() <= 0) {
            return false;
        }
        repo.salvar(produto);
        return true;
    }

    @Override
    public boolean remover(int id) {
        Produto p = repo.buscarPorId(id);
        if (p != null) {
            repo.remover(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean atualizar(Produto produto) {
        Produto p = repo.buscarPorId(produto.getId());
        if (p != null) {
            repo.alterar(produto);
            return true;
        }
        return false;
    }

    @Override
    public List<Produto> listar() {
        return repo.listar();
    }

    @Override
    public boolean registrarEntrada(int id, int quantidade) {
        Produto p = repo.buscarPorId(id);
        if (p != null && quantidade > 0) {
            p.setQuantidade(p.getQuantidade() + quantidade);
            repo.alterar(p);
            return true;
        }
        return false;
    }

    @Override
    public boolean registrarSaida(int id, int quantidade) {
        Produto p = repo.buscarPorId(id);
        if (p != null && quantidade > 0 && p.getQuantidade() >= quantidade) {
            p.setQuantidade(p.getQuantidade() - quantidade);
            repo.alterar(p);
            return true;
        }
        return false;
    }
}