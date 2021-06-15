public class Loja {
	
    private Cliente [] clientesCadastrados;
    private ListaJogos bibliotecaJogos;
    private ListaJogos carrinhoCompras; 

    Loja()
    {
    	clientesCadastrados = new Cliente[1000]; 
        bibliotecaJogos = new ListaJogos();
        carrinhoCompras = new ListaJogos();
    }
    
    
    
    public double calculaDesconto (Cliente cliente) //coloquei um retorno para pegar o desconto do cliente específico.
    {
    	double descontoCategoria;
    	descontoCategoria = 0;
    	
    	descontoCategoria = cliente.checaFidelidade();
    	
    	
    	double somaCarrinho;
    	somaCarrinho = 0;
    	
    	Jogo vetor[];
    	vetor = carrinhoCompras.getJogos();

    	
    	for(int i = 0; i < vetor.length; i++)
    	{
    		somaCarrinho = somaCarrinho + vetor[i].getPreco();
    	}
    	
    	
    	double descontoProdutos;
    	descontoProdutos = 0;
    	
    	descontoProdutos = somaCarrinho / descontoCategoria;
    	
    	
    	return descontoProdutos;
    }
    
    
    
    
    public void cadastraCliente(String nome, String cpf) //Coloquei parâmetros.
    {
    	
    	Cliente novoCliente = new Cliente();
    	
    	novoCliente.setNome(nome);
    	novoCliente.setCpf(cpf);
    	
    	
    	for(int i = 0; i < clientesCadastrados.length; i++)
    	{
    		if (clientesCadastrados[i] == null)
    		{
    			clientesCadastrados[i] = novoCliente;
    			break;
    		}
    	}
    }
    

    
    public Cliente BuscaCliente(String procurado) //Coloquei um retorno. E coloquei como parâmetro de busca o cpf.
    {
    	Cliente pessoa = null;
    	
    	for(int i = 0; i < clientesCadastrados.length; i++)
    	{
    		if(clientesCadastrados[i].getCpf() == procurado)
    		{
    		  pessoa = clientesCadastrados[i];
    		}
    	}
    	
    	return pessoa;
    }
    
    
    
    public void iniciaPedido() //Não deveria passar um cliente?
    {
        carrinhoCompras = new ListaJogos();
    }
    
    
    
    public void fechaPedido(Cliente clienteAtual) //Coloquei o carrinho atual no histórico do cliente. E já abri um novo.
    {
    	clienteAtual.adicionarPedido(carrinhoCompras);
    	this.iniciaPedido();
    }
    
    
    
    public boolean buscaJogo(String nome, String plataforma) //Coloquei um parâmentro e um retorno.
    {
    	
    	return bibliotecaJogos.buscaJogo(nome, plataforma);
    	
    }
    
    
    //Percebi que em nenhum momento estávamos adicionando os jogos ao carrinho aqui na loja...
    public boolean adicionaAoCarrinho(Jogo jogoEscolhido)
    {
    	return carrinhoCompras.adicionarJogo(jogoEscolhido);
    }


}






































