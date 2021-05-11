public class Jogo {

    protected String nome;
    protected double preco;
    protected String plataforma;
    protected Data dataDeLanc;
    protected String genero;

    Jogo() {
        nome = "";
        preco = 0.0;
        plataforma = "";
        dataDeLanc = new Data();
        genero = "";
    }

    @Override
    public String toString() {
        System.out.println("Nome: " + nome);
        System.out.printf("Preço: %.2f\n", preco);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Data Lançamento: " + dataDeLanc.toString());        
        return super.toString();
    }
}
