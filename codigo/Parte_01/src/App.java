public class App {
    public static void main(String[] args) throws Exception {
        
        ////#region
        //Testes referentes a classe Jogo e seus herdeiros
        Data GOW = new Data(20, 04, 2018);
        Data BOW = new Data(11, 05, 2021);
        Data HF3 = new Data(23, 05, 2020);
        Data BT = new Data(20, 8, 2020);


        Playstation jg = new Playstation("God Of War", 75, GOW);   
        Playstation jg4 = new Playstation("Bod Of War", 57, BOW);     
        Computador jg2 = new Computador("Half-Life 3", 300, HF3);        
        Xbox jg3 = new Xbox("Battle Toads", 50, BT);        
        ////#endregion

        ListaJogos vetor = new ListaJogos();
        vetor.adicionarJogo(jg);
        vetor.adicionarJogo(jg2);
        vetor.adicionarJogo(jg3);
        vetor.adicionarJogo(jg4);
        // vetor.ordenaPorPlatf();        
        // vetor.toString(); 
        // vetor.removerJogo(3).toString();
        
        if(vetor.buscaJogo("god of wa")) System.out.println("SIM !!");
        else System.out.println("NAOOO !!!");
    }
}
