import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Loja {

	private Cliente[] clientesCadastrados;
	private ListaJogos bibliotecaJogos;
	private ListaJogos carrinhoCompras;

	Loja() {

		clientesCadastrados = new Cliente[1000];		

		try {
			carregaBiblioteca();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo contendo jogos disponíveis na loja não encontrado");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		carrinhoCompras = new ListaJogos(100);
	}

	public double calculaDesconto(Cliente cliente) // coloquei um retorno para pegar o desconto do cliente específico.
	{
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

	public void cadastraCliente(String nome, String cpf) // Coloquei parâmetros.
	{

		Cliente novoCliente = new Cliente();

		novoCliente.setNome(nome);
		novoCliente.setCpf(cpf);

		for (int i = 0; i < clientesCadastrados.length; i++) {
			if (clientesCadastrados[i] == null) {
				clientesCadastrados[i] = novoCliente;
				break;
			}
		}
	}

	public Cliente BuscaCliente(String procurado) // Coloquei um retorno. E coloquei como parâmetro de busca o cpf.
	{
		Cliente pessoa = null;

		for (int i = 0; i < clientesCadastrados.length; i++) {
			if (clientesCadastrados[i].getCpf() == procurado) {
				pessoa = clientesCadastrados[i];
			}
		}

		return pessoa;
	}

	public void iniciaPedido() // Não deveria passar um cliente?
	{
		carrinhoCompras = new ListaJogos(100);
	}

	public void fechaPedido(Cliente clienteAtual) // Coloquei o carrinho atual no histórico do cliente. E já abri um
													// novo.
	{
		clienteAtual.adicionarPedido(carrinhoCompras);
		this.iniciaPedido();
	}

	public boolean buscaJogo(String nome, String plataforma) // Coloquei um parâmentro e um retorno.
	{

		return bibliotecaJogos.buscaJogo(nome, plataforma);

	}

	// Percebi que em nenhum momento estávamos adicionando os jogos ao carrinho aqui
	// na loja...
	public boolean adicionaAoCarrinho(Jogo jogoEscolhido) {
		return carrinhoCompras.adicionarJogo(jogoEscolhido);
	}

	private void carregaBiblioteca() throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader("codigo/Parte_01/gamesLibrary.csv"));
		String aux;
		String[] divided, dateString;
		Jogo jogo;
		Data dataLanc;
		int c = Integer.parseInt(reader.readLine());
		bibliotecaJogos = new ListaJogos(c);

		for (int i = 0; i < c; i++) {

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

	public void mostraLojaCompleta(){
		bibliotecaJogos.toString();
	}
}
