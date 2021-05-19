
public class Cliente {

	private String nome;
	private String cpf;
	private ListaJogos historicoCompras[];
	private double valorComprado;
	private IFidelidade classificacao; //Tive que inserir esse atributo para fazer o método "mostrarFidelidade" funcionar.
	
	
	public void setNome (String name)
	{
		nome = name;
	}
	public String getNome()
	{
		return nome;
	}
	
	
	public void setCpf (String cpf)
	{
		this.cpf = cpf;
	}
	public String getCpf()
	{
		return cpf;
	}
	
	
	public void setHistoricoDeCompras(ListaJogos vet[])
	{
		historicoCompras = vet;
	}
	public ListaJogos[] getHistoricoCompras()
	{
		return historicoCompras;
	}
	
	
	public void setValorComprado(double valorGasto)
	{
		valorComprado = valorGasto;
	}
	public double getValorComprado()
	{
		return valorComprado;
	}
	
	

	public boolean adicionarPedido(ListaJogos pedidos)
	{
		boolean ok = false;
		
		for(int i = 0; i < historicoCompras.length; i++)
		{
			if(historicoCompras[i] == null)
			{
				historicoCompras[i] = pedidos;
				
				ok = true;
			}
		}
		return ok;
	}
	
	
	//Nao seria melhor mostrar a fidelidade do cara?
	//Tipo se no momento ele é "ouro" .."prata"..
	public double mostraFidelidade() 
	{
		return classificacao.checaFidelidade();
		
	}
	

	
	public void atualizaFidelidade()
	{
		double valorGastoEmCompras = 0.0;
		
		for(int i = 0; i < this.historicoCompras.length; i++)
		{
			VetorJogos carrinhoAtual;
			carrinhoAtual = historicoCompras[i];
			
			Jogo vetorDeJogos[];
			vetorDeJogos = carrinhoAtual.getJogos();
			
			for(int j = 0; j < vetorDeJogos.length; j++)
			{
				Jogo jogoAtual;
				jogoAtual = vetorDeJogos[j];
				
				valorGastoEmCompras = valorGastoEmCompras + jogoAtual.getPreco();
			}
		}
		
		if(valorGastoEmCompras < 600.00)
		{
			//regular
			this.classificacao = new Regular();
		}
		else if(valorGastoEmCompras >= 600.00 && valorGastoEmCompras < 1200.00)
		{
			//prata	
			this.classificacao = new Prata();
		}
		else if(valorGastoEmCompras >= 1200.00 && valorGastoEmCompras < 2000.00)
		{
			//ouro
			this.classificacao = new Ouro();
		}
		else if(valorGastoEmCompras >= 2000)
		{
			//diamante
			this.classificacao = new Diamante();
		}
	}
	
	
	
	public String toString()
	{
		
		String texto = null;
		
		for(int i = 0; i < historicoCompras.length; i++)
		{
			texto = texto + historicoCompras[i];
		}
		
		return "Cliente: " + this.nome + " CPF: " + this.cpf +
				"Histórico de compras: " + texto +
				"Valor da compra atual: " + this.valorComprado;
	}
	
	
}









































