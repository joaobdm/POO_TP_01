public class Jogo {

    protected String nome;
    protected double preco;
    protected String plataforma;
    // protected dataDeLanc;
    protected String genero;

    Jogo() {
        nome = "";
        preco = 0.0;
        plataforma = "";
        // data
        genero = "";
    }

    @Override
    public String toString() {
        System.out.println("Nome: " + nome);
        System.out.printf("Preço: %.2f\n", preco);
        System.out.println("Plataforma: " + plataforma);        
        return super.toString();
    }
}
