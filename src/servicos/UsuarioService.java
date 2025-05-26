package servicos;

import entidades.Usuario;
import interfaces.IUsuarioRepository;
import interfaces.IUsuarioService;

public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository repo;

    public UsuarioService(IUsuarioRepository repo) {
        this.repo = repo;
        // Usuário padrão para login
        if (repo.buscarPorLogin("admin") == null) {
            repo.salvar(new Usuario("admin", "admin"));
        }
    }

    @Override
    public boolean login(String login, String senha) {
        if (login == null || senha == null || login.isEmpty() || senha.isEmpty()) {
            return false;
        }
        Usuario usuario = repo.buscarPorLogin(login);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}