import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loja {

	private ArrayList<Cliente> clientesCadastrados;
	private ListaJogos bibliotecaJogos;
	private ListaJogos carrinhoCompras;

	Loja() {

		clientesCadastrados = new ArrayList<Cliente>();

		try {
			carregaBiblioteca();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo contendo jogos disponíveis na loja não encontrado");
		} catch (NumberFormatException e0) {
			System.out.println("Conteúdo do arquivo não condiz com o esperado: Formatação de número");
		} catch (NullPointerException e1) {
			System.out.println("Conteúdo do arquivo não condiz com o esperado");
		} catch (Exception e2) {
			System.out.println("Ocorreu um erro inesperado");
		}

		carrinhoCompras = new ListaJogos(100);
	}

	public double calculaDesconto(Cliente cliente) {
		/*
		 * MÉTODO DEVE APENAS FAZER O CALCULO DOS PRODUTOS DO CARRINHO E MULTIPLICAR
		 * PELO DESCONTO QUE DEVE SER RETORNADO EM UM MÉTODO PELA CLASSE CLIENTE.
		 */

		double descontoCategoria;
		descontoCategoria = 0;

		descontoCategoria = cliente.checaFidelidade();

		double somaCarrinho;
		somaCarrinho = 0;

		Jogo vetor[];
		vetor = carrinhoCompras.getJogos();

		for (int i = 0; i < vetor.length; i++) {
			somaCarrinho = somaCarrinho + vetor[i].getPreco();
		}

		double descontoProdutos;
		descontoProdutos = 0;

		descontoProdutos = somaCarrinho / descontoCategoria;

		return descontoProdutos;
	}

	public void cadastraCliente(String nome, String cpf) {

		if (!checaCliente(cpf)) {
			Cliente novoCliente = new Cliente();

			novoCliente.setNome(nome);
			novoCliente.setCpf(cpf);

			clientesCadastrados.add(novoCliente);
			System.out.println("Cliente cadastrado com sucesso");
		} else
			System.out.println("CPF já consta no banco de cadastro");
	}

	private void cadastraCliente() throws IOException {
		System.out.print("Digite o nome do cliente que deseja cadastrar: ");
		String nome = teclado();
		System.out.print("\nDigite o CPF do cliente que deseja cadastrar: ");
		String cpf = teclado();
		System.out.println();
		cadastraCliente(nome, cpf);
	}

	public Cliente buscaCliente(String cpf) {

		for (int i = 0; i < clientesCadastrados.size(); i++) {
			if (clientesCadastrados.get(i).getCpf() == cpf) {
				return clientesCadastrados.get(i);
			}
		}

		System.out.println("Cliente não encontrado");
		return null;

	}

	public void iniciaPedido() // Não deveria passar um cliente?
	{
		carrinhoCompras = new ListaJogos(100);
	}

	public void fechaPedido(Cliente clienteAtual) {
		try {
			clienteAtual.adicionarPedido(carrinhoCompras);
			// Método do calculo da compra vem aqui miriam
			this.iniciaPedido();
		} catch (NullPointerException e0) {
			System.out.println("O pedido deve ser fechado em nome de um cliente já cadastrado");
		}
	}

	private void fechaPedido() throws IOException {
		Cliente clienteAtual;
		System.out.print("Digite o CPF do cliente (Somente números): ");
		String cpf = teclado();
		clienteAtual = buscaCliente(cpf);
		fechaPedido(clienteAtual);
	}

	public boolean buscaJogo(String nome, String plataforma) // Coloquei um parâmentro e um retorno.
	{

		return bibliotecaJogos.existeJogo(nome, plataforma);

	}

	private void buscaJogo() throws IOException {
		System.out.print("Digite o nome do jogo que deseja buscar: \n");
		String plataforma = "", nome = teclado();
		System.out.println("\nSelecione a plataforma desejada: ");
		System.out.println("[1] Computador");
		System.out.println("[2] Playstation");
		System.out.println("[3] XBox");
		int opcao = Integer.parseInt(teclado());
		boolean found = false;
		switch (opcao) {
			case 1:
				plataforma = "Computador";
				break;
			case 2:
				plataforma = "Playstation";
				break;
			case 3:
				plataforma = "XBox";
				break;

			default:
				break;
		}
		found = buscaJogo(nome, plataforma);
		if (found) {
			System.out.println("Jogo encontrado !");
			System.out.println("Deseja adicionar ao carrinho?");
			System.out.println("[1] Sim");
			System.out.println("[2] Não");
			opcao = Integer.parseInt(teclado());
			switch (opcao) {
				case 1:
					try {
						adicionaAoCarrinho(nome, plataforma);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("Nada foi adicionado ao carrinho");
					break;
			}
		} else
			System.out.println("Infelizmente não temos esse jogo :(");
	}

	// Percebi que em nenhum momento estávamos adicionando os jogos ao carrinho aqui
	// na loja...
	public void adicionaAoCarrinho(String jogoEscolhido, String plataforma) throws Exception {
		try {
			carrinhoCompras.adicionarJogo(bibliotecaJogos.buscaJogo(jogoEscolhido, plataforma));

		} catch (NullPointerException e0) {
			System.out.println("Jogo buscado para plataforma escolhida não foi encontrado");
		} catch (ArrayIndexOutOfBoundsException e1) {
			System.out.println("Carrinho de compras cheio");
		}

	}

	private boolean checaCliente(String cpf) {
		boolean resp = false;
		for (int i = 0; i < clientesCadastrados.size(); i++) {
			if (clientesCadastrados.get(i).getCpf() == cpf) {
				resp = true;
				return resp;
			}
		}
		return resp;
	}

	private void carregaBiblioteca() throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader("codigo/Parte_01/gamesLibrary.csv"));
		String aux;
		String[] divided, dateString;
		Jogo jogo;
		Data dataLanc;
		int tamanho = Integer.parseInt(reader.readLine());
		bibliotecaJogos = new ListaJogos(tamanho);

		for (int i = 0; i < tamanho; i++) {

			aux = reader.readLine();
			divided = aux.split(",");

			dateString = divided[3].split("-");
			dataLanc = new Data(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]),
					Integer.parseInt(dateString[2]));

			if (divided[2].equals("Computador"))
				jogo = new Computador(divided[0], Double.parseDouble(divided[1]), dataLanc);
			else if (divided[2].equals("Playstation"))
				jogo = new Playstation(divided[0], Double.parseDouble(divided[1]), dataLanc);
			else
				jogo = new Xbox(divided[0], Double.parseDouble(divided[1]), dataLanc);

			bibliotecaJogos.adicionarJogo(jogo);
		}

		reader.close();

	}

	public void menuInterativo() throws Exception {

		int opcao = 0;
		imprimeMenu();
		do {
			opcao = Integer.parseInt(teclado());
			switch (opcao) {
				case 1:
					cadastraCliente();
					break;
				case 2:
					iniciaPedido();
					System.out.println("Vamos começar as compras !");
					break;
				case 3:
					buscaJogo();
					break;
				case 4:
					mostraCarrinho();
					break;
				case 5:
					fechaPedido();
					break;
				default:
					break;
			}

		} while (opcao != 0);
	}

	private void imprimeMenu() {
		System.out.println("\n########## MENU DA LOJA ##########\n");
		System.out.println("Selecione a opção desejada:");
		System.out.println("[1] Cadastrar Cliente");
		System.out.println("[2] Abrir Pedido");
		System.out.println("[3] Buscar Jogo na biblioteca");
		System.out.println("[4] Mostrar carrinho");
		System.out.println("[5] Fechar Pedido");
		System.out.println("[0] Sair");
	}

	private String teclado() throws IOException {
		String s = new String();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		return s;
	}

	public void mostraLojaCompleta() {
		bibliotecaJogos.toString();
	}

	public void mostraCarrinho() {
		carrinhoCompras.toString();
	}
}
