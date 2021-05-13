import java.util.Arrays;
import java.util.Comparator;

public class VetorJogos {

    private static final int MAXJOGOS = 500;
    private Jogo[] jogos;
    private StringBuilder sb;

    VetorJogos() {
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
        Jogo aux = new Jogo();

        // Clonando objeto jogo
        aux.setNome(jogos[posi].getNome());
        aux.setDataDeLanc(jogos[posi].getDataDeLanc());
        aux.setPlataforma(jogos[posi].getPlataforma());
        aux.setPreco(jogos[posi].getPreco());
        
        //"Zerando" a posição removida
        jogos[posi] = new Jogo();

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
        Arrays.sort(this.jogos, new Comparator<Object>()
        {
            public int compare(Object o1, Object o2)
            {
                String a = (String) o1;
                String b = (String) o2;
                return a.compareTo(b);
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
