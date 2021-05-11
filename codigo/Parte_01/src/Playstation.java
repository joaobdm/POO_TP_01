public class Playstation extends Jogo {

    private static final double MULTIPLICADORPS = 2;

    Playstation(){
        super.ajustaPreco(this.preco*MULTIPLICADORPS);
    }
}
