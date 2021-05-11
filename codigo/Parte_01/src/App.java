public class App {
    public static void main(String[] args) throws Exception {
        
        ////#region
        //Testes referentes a classe Jogo e seus herdeiros
        Data hoje = new Data(11, 05, 2021);
        
        Playstation jg = new Playstation("God Of War", 75, hoje);     
        jg.toString();

        Computador jg2 = new Computador("Half-Life 3", 300, hoje);
        jg2.toString();

        Xbox jg3 = new Xbox("Battle Toads", 50, hoje);
        jg3.toString();
        ////#endregion
    }
}
