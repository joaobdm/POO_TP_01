public class Computador extends Jogo {

    private static final double MULTIPLICADORPC = 4;

    Computador(){
        super.ajustaPreco(this.preco*MULTIPLICADORPC);
    }
}
