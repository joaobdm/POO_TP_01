import java.util.Arrays;
import java.util.Comparator;

public class ListaJogos {

    private static final int MAXJOGOS = 500;
    private Jogo[] jogos;
    private StringBuilder sb;

    ListaJogos() {
        jogos = new Jogo[MAXJOGOS];
        for (int i = 0; i < jogos.length; i++) {
            jogos[i] = new Jogo();
        }
    }

    /**
     * Através do nome e plataforma, detecta se o jogo já existe no vetor em
     * questão.
     * 
     * @param nomeDoJogo
     * @param plataforma
     * @return
     */
    public boolean buscaJogo(String nomeDoJogo, String plataforma) {

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i].getNome().equals(nomeDoJogo) && jogos[i].getPlataforma().equals(plataforma)) {
                return true;
            }
            if (jogos[i].getNome().equals(""))
                return false;
        }
        return false;
    }

    public boolean buscaJogo(String nomeDoJogo){
        if(buscaJogo(nomeDoJogo, "Computador")) return true;
        if(buscaJogo(nomeDoJogo, "Playstation")) return true;
        if(buscaJogo(nomeDoJogo, "Xbox")) return true;
        else return false;
    }

    /**
     * Adiciona um Jogo j ao vetor caso já não exista o mesmo jogo nesse vetor.
     * 
     * @param j Jogo a ser inserido no vetor.
     * @return true se a operação foi feita com sucesso, false caso contrário.
     */
    public boolean adicionarJogo(Jogo j) {

        if (vetorVazio()) {
            this.jogos[0] = j;
            return true;
        }

        else if (!buscaJogo(j.getNome(), j.getPlataforma()) && !vetorCheio()) {
            for (int i = 0; i < jogos.length; i++) {
                if (jogos[i].getNome().equals("")) {
                    jogos[i] = j;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove um jogo do vetor e diminui uma posição de cada jogo posterior do
     * vetor, se houver.
     * 
     * @param posi Posição do vetor onde o jogo se encontra
     * @return Jogo removido
     */
    public Jogo removerJogo(int posi) {
        Jogo aux = jogos[posi];
        Jogo aux2 = new Jogo();
        
        //"Zerando" a posição removida
        jogos[posi] = aux2;

        //Reordenando o vetor
        for (int i = posi + 1; i < jogos.length; i++) {
            this.jogos[posi] = this.jogos[i];
            posi++;
        }

        return aux;
    }

    private boolean vetorVazio() {

        if (this.jogos[0].getNome().equals(""))
            return true;

        else
            return false;
    }

    private boolean vetorCheio() {
        if (!this.jogos[MAXJOGOS - 1].getNome().equals(""))
            return true;

        else
            return false;
    }

    public void ordenaPorPlatf()
    {
        Arrays.sort(this.jogos, new Comparator<Jogo>()
        {
            public int compare(Jogo o1, Jogo o2)
            {
                if(o1.getPlataforma().equals(o2.getPlataforma()))
                {
                    String a = (String) o1.getNome();
                    String b = (String) o2.getNome();
                    return a.compareTo(b);
                }
                else
                {
                    String a = (String) o1.getPlataforma();
                    String b = (String) o2.getPlataforma();
                    return a.compareTo(b);
                }
                
            }
        });
    } 

    @Override
    public String toString() {
        sb = new StringBuilder();
        for (int i = 0; i < jogos.length; i++) {
            if (!jogos[i].getNome().equals("")) {
                sb.append(jogos[i].toString());
            }
        }

        return sb.toString();
    }

}
