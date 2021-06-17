public class App {
    public static void main(String[] args) throws Exception {

        // Loja loja = new Loja(new Data(01, 01, 2021));

        // loja.menuInterativo();

        Data hoje = new Data(17, 06, 2021);
        Data passado = new Data(07, 04, 2011);

        System.out.println(hoje.diferencaDias(passado));

    }
}
