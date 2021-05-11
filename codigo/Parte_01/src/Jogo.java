public class Jogo {

    protected String nome;
    protected double preco;
    protected String plataforma;
    protected Data dataDeLanc;    

    Jogo() {
        nome = "";
        preco = 0.0;
        plataforma = "";
        dataDeLanc = new Data();        
    }

    Jogo(String nome, double preco, Data data){
        this.nome = nome;
        this.preco = preco;        
        dataDeLanc = data;
    }

    @Override
    public String toString() {
        System.out.println("Nome: " + nome);
        System.out.printf("Preço: %.2f\n", preco);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Data Lançamento: " + dataDeLanc.toString() + "\n");        
        return super.toString();
    }

    protected void ajustaPreco(double novoPreco){
        this.preco = novoPreco;
    }
}
