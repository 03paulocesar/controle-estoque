package repositorios;

import entidades.Venda;
import interfaces.IVendaRepository;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository implements IVendaRepository {

    @Override
    public void salvar(Venda venda) {
        String sql = "INSERT INTO vendas (id_produto, quantidade, tipo) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getIdProduto());
            stmt.setInt(2, venda.getQuantidade());
            stmt.setString(3, venda.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Venda> listar() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda v = new Venda(
                    rs.getInt("id_produto"),
                    rs.getInt("quantidade"),
                    rs.getString("tipo")
                );
                vendas.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }
}