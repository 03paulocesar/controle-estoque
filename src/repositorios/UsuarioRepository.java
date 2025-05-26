package repositorios;

import entidades.Usuario;
import interfaces.IUsuarioRepository;
import conexao.Conexao;
import java.sql.*;

public class UsuarioRepository implements IUsuarioRepository {

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("login"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }@Override
public java.util.List<entidades.Usuario> listar() {
    java.util.List<entidades.Usuario> usuarios = new java.util.ArrayList<>();
    String sql = "SELECT * FROM usuarios";
    try (java.sql.Connection conn = conexao.Conexao.getConexao();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            entidades.Usuario u = new entidades.Usuario(
                rs.getString("login"),
                rs.getString("senha")
            );
            usuarios.add(u);
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
    return usuarios;
}
}