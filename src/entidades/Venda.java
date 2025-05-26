package entidades;

public class Venda {
    private int idProduto;
    private int quantidade;
    private String tipo; // "ENTRADA" ou "SAIDA"

    public Venda() {}

    public Venda(int idProduto, int quantidade, String tipo) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}