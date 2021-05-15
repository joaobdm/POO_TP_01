public class App {
    public static void main(String[] args) throws Exception {
        
        ////#region
        //Testes referentes a classe Jogo e seus herdeiros
        Data hoje = new Data(11, 05, 2021);
        
        Playstation jg = new Playstation("God Of War", 75, hoje);   
        Playstation jg4 = new Playstation("Bod Of War", 75, hoje);     

        Computador jg2 = new Computador("Half-Life 3", 300, hoje);        

        Xbox jg3 = new Xbox("Battle Toads", 50, hoje);        
        ////#endregion

        ListaJogos vetor = new ListaJogos();
        vetor.adicionarJogo(jg);
        vetor.adicionarJogo(jg2);
        vetor.adicionarJogo(jg3);
        vetor.adicionarJogo(jg4);
        vetor.ordenaPorPlatf();
        vetor.toString();        
    }
}
