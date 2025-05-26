package view;

import entidades.Produto;
import entidades.Venda;
import interfaces.IProdutoService;
import interfaces.IUsuarioService;
import interfaces.IVendaService;
import repositorios.ProdutoRepository;
import repositorios.UsuarioRepository;
import repositorios.VendaRepository;
import servicos.ProdutoService;
import servicos.UsuarioService;
import servicos.VendaService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IProdutoService produtoService = new ProdutoService(new ProdutoRepository());
        IUsuarioService usuarioService = new UsuarioService(new UsuarioRepository());
        IVendaService vendaService = new VendaService(new VendaRepository());
        Scanner sc = new Scanner(System.in);

        boolean logado = false;
        while (!logado) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            if (login.isEmpty() || senha.isEmpty()) {
                System.out.println("Informe login e senha.");
            } else if (usuarioService.login(login, senha)) {
                System.out.println("Login realizado com sucesso!");
                logado = true;
            } else {
                System.out.println("Login ou senha invalidos.");
            }
        }

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Registrar entrada");
            System.out.println("4. Registrar saida");
            System.out.println("5. Gerar relatorio");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Quantidade: ");
                    String qtdStr = sc.nextLine();
                    System.out.print("Valor: ");
                    String valorStr = sc.nextLine();
                    if (nome.isEmpty() || qtdStr.isEmpty() || valorStr.isEmpty()) {
                        System.out.println("Produto nao cadastrado. Preencha todos os campos.");
                    } else {
                        int id = produtoService.listar().size() + 1;
                        int qtd = Integer.parseInt(qtdStr);
                        double valor = Double.parseDouble(valorStr);
                        Produto p = new Produto(id, nome, qtd, valor);
                        if (produtoService.cadastrar(p)) {
                            System.out.println("Produto cadastrado com sucesso!");
                        } else {
                            System.out.println("Produto nao cadastrado. Preencha todos os campos corretamente.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("--- PRODUTOS ---");
                    for (Produto prod : produtoService.listar()) {
                        System.out.println("ID: " + prod.getId() + " | Nome: " + prod.getNome() +
                                " | Quantidade: " + prod.getQuantidade() + " | Valor: R$" + prod.getValor());
                    }
                    break;
                case 3:
                    System.out.print("ID do produto: ");
                    int idEntrada = Integer.parseInt(sc.nextLine());
                    System.out.print("Quantidade a adicionar: ");
                    int qtdEntrada = Integer.parseInt(sc.nextLine());
                    if (produtoService.registrarEntrada(idEntrada, qtdEntrada)) {
                        vendaService.registrarVenda(new Venda(idEntrada, qtdEntrada, "ENTRADA"));
                        System.out.println("Estoque atualizado com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado ou quantidade invalida.");
                    }
                    break;
                case 4:
                    System.out.print("ID do produto: ");
                    int idSaida = Integer.parseInt(sc.nextLine());
                    System.out.print("Quantidade a remover: ");
                    int qtdSaida = Integer.parseInt(sc.nextLine());
                    if (produtoService.registrarSaida(idSaida, qtdSaida)) {
                        vendaService.registrarVenda(new Venda(idSaida, qtdSaida, "SAIDA"));
                        System.out.println("Estoque atualizado com sucesso!");
                    } else {
                        System.out.println("Estoque insuficiente ou produto nao encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("--- RELATORIO DE PRODUTOS ---");
                    for (Produto prod : produtoService.listar()) {
                        System.out.println("ID: " + prod.getId() + " | Nome: " + prod.getNome() +
                                " | Quantidade: " + prod.getQuantidade() + " | Valor: R$" + prod.getValor());
                    }
                    System.out.println("--- MOVIMENTACOES ---");
                    for (Venda venda : vendaService.listarVendas()) {
                        System.out.println("Produto ID: " + venda.getIdProduto() +
                                " | Quantidade: " + venda.getQuantidade() +
                                " | Tipo: " + venda.getTipo());
                    }
                    System.out.println("Relatorio gerado com sucesso!");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}