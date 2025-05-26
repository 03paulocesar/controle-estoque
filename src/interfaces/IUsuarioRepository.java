package interfaces;

import entidades.Usuario;
import java.util.List;

public interface IUsuarioRepository {
    void salvar(Usuario usuario);
    Usuario buscarPorLogin(String login);
    List<Usuario> listar();
}