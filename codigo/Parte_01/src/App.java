public class App {
    public static void main(String[] args) throws Exception {

        Data GOW = new Data(20, 04, 2018);
        Data TLOU = new Data(11, 05, 2021);
        Data HF3 = new Data(23, 05, 2020);
        Data BT = new Data(20, 8, 2020);

        Playstation jg = new Playstation("God Of War", 75, GOW);
        Playstation jg4 = new Playstation("The Last of Us", 120, TLOU);
        Computador jg2 = new Computador("Half-Life 3", 300, HF3);
        Xbox jg3 = new Xbox("Battle Toads", 50, BT);

        ListaJogos vetor = new ListaJogos();
        vetor.adicionarJogo(jg);
        vetor.adicionarJogo(jg2);
        vetor.adicionarJogo(jg3);
        vetor.adicionarJogo(jg4);
        vetor.ordenaPorPlatf();
        vetor.toString();
        System.out.println("################################");
        vetor.ordenaPorCond();
        vetor.toString();

        Cliente novoCliente;
        novoCliente = new Cliente();
        System.out.println(novoCliente.toString());

        novoCliente.adicionarPedido(vetor);

        novoCliente.atualizaFidelidade();
        System.out.println(novoCliente.checaFidelidade());
        System.out.println(novoCliente.toString());

    }
}
