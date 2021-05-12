public class Computador extends Jogo {

    private static final double MULTIPLICADORPC = 1.2;

    Computador(){
        super.ajustaPreco(this.preco*MULTIPLICADORPC);
        super.plataforma = "Computador";
    }

    Computador(String nome, double preco, Data data) {
        super(nome, preco, data);
        super.ajustaPreco(this.preco*MULTIPLICADORPC);
        super.plataforma = "Computador";
    }

    
}
