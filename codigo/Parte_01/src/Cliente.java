
public class Cliente {

	private String nome;
	private String cpf;
	private VetorJogos historicoCompras[];
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
	
	
	public void setHistoricoDeCompras(VetorJogos vet[])
	{
		historicoCompras = vet;
	}
	public VetorJogos[] getHistoricoCompras()
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
	
	

	public boolean adicionarPedido(VetorJogos pedidos)
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
	
	
	public void mostraFidelidade() 
	{
		classificacao.checaFidelidade();
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









































