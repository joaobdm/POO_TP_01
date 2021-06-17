import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	/**
	 * Esse m�todo calcula desconto de uma determinada compra.
	 * Para isso, ele soma todos os valores dos jogos do carrinho.
	 * Logo ap�s, multimplica pelo desconto referente � categoria do cliente.
	 * 
	 * @param cliente - cliente que est� realizando a compra.
	 * @return valor me reais do desconto da compra.
	 */
	public double calculaDesconto(Cliente cliente) 
	{
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
	
	
	/**
	 *
	 * @return Retorna o valor total do carrinho de compras, ainda sem desconto.
	 */
	public double calculaValorPagar()
	{
		Jogo vetor[];
		vetor = carrinhoCompras.getJogos();

		double somaCarrinho;
		somaCarrinho = 0;
		
		for (int i = 0; i < vetor.length; i++) {
			somaCarrinho = somaCarrinho + vetor[i].getPreco();
		}
		
	return somaCarrinho;
	}

	
	
	public void cadastraCliente(String nome, String cpf) {

		if (!checaCliente(cpf)) {
			Cliente novoCliente = new Cliente();

			novoCliente.setNome(nome);
			novoCliente.setCpf(cpf);

			clientesCadastrados.add(novoCliente);
		} else
			System.out.println("CPF já consta no banco de cadastro");
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

	
	public void fechaPedido(Cliente clienteAtual) 
	{
		double descontoNaCompraAtual = 0;
		
		try 
		{
			clienteAtual.adicionarPedido(carrinhoCompras);
			
			descontoNaCompraAtual = this.calculaDesconto(clienteAtual);
			
			double valorPagar;
			valorPagar =  this.calculaValorPagar() - descontoNaCompraAtual;
			
			
			System.out.printf("Valor a pagar: %.2f", valorPagar);
			System.out.println();
			System.out.printf("Seu desconto nessa compra foi de: %.2f", descontoNaCompraAtual);
			System.out.println();
			System.out.println("Obrigado!");
			
			
			clienteAtual.atualizaFidelidade();
			
			
			// Método do calculo da compra vem aqui miriam
			
			
			this.iniciaPedido();
		} 
		catch (NullPointerException e0) 
		{
			System.out.println("O pedido deve ser fechado em nome de um cliente já cadastrado");
		}
	}

	public boolean buscaJogo(String nome, String plataforma) // Coloquei um parâmentro e um retorno.
	{

		return bibliotecaJogos.existeJogo(nome, plataforma);

	}

	
	public boolean buscaJogo(String nome) // Coloquei um parâmentro e um retorno.
	{

		return bibliotecaJogos.existeJogo(nome);

	}

	// Percebi que em nenhum momento estávamos adicionando os jogos ao carrinho aqui
	// na loja...
	public void adicionaAoCarrinho(String jogoEscolhido, String plataforma) throws Exception 
	{
		try 
		{
			carrinhoCompras.adicionarJogo(bibliotecaJogos.buscaJogo(jogoEscolhido, plataforma));

		} 
		catch (NullPointerException e0) 
		{
			System.out.println("Jogo buscado para plataforma escolhida não foi encontrado");
		} 
		catch (ArrayIndexOutOfBoundsException e1) 
		{
			System.out.println("Carrinho de compras cheio");
		}

	}

	private boolean checaCliente(String cpf) 
	{
		boolean resp = false;
		
		for (int i = 0; i < clientesCadastrados.size(); i++) 
		{
			if (clientesCadastrados.get(i).getCpf() == cpf) 
			{
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

	public void mostraLojaCompleta() {
		bibliotecaJogos.toString();
	}

	public void mostraCarrinho() {
		carrinhoCompras.toString();
	}
}
