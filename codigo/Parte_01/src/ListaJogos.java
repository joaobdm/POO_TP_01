import java.util.Arrays;
import java.util.Comparator;

public class ListaJogos {

    private static final int MAXJOGOS = 500;
    private Jogo[] jogos;
    private StringBuilder sb;

    ListaJogos() {
        jogos = new Jogo[MAXJOGOS];
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

            if (jogos[i] == null)
                return false;
            if (jogos[i].getNome().toLowerCase().equals(nomeDoJogo.toLowerCase()) && 
            jogos[i].getPlataforma().toLowerCase().equals(plataforma.toLowerCase())) {
                return true;
            }

        }
        return false;
    }

    /**
     * Método sobre carregado, retornando simplesmente se o jogo existe,
     * independente da plataforma
     */
    public boolean buscaJogo(String nomeDoJogo) {
        if (buscaJogo(nomeDoJogo, "Computador"))
            return true;
        if (buscaJogo(nomeDoJogo, "Playstation"))
            return true;
        if (buscaJogo(nomeDoJogo, "Xbox"))
            return true;
        else
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
                if (jogos[i] == null) {
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

        // "Zerando" a posição removida
        jogos[posi] = null;

        // Reordenando o vetor
        for (int i = posi + 1; i < jogos.length; i++) {
            this.jogos[posi] = this.jogos[i];
            posi++;
        }

        return aux;
    }

    private boolean vetorVazio() {

        if (this.jogos[0] == null)
            return true;

        else
            return false;
    }

    private boolean vetorCheio() {
        if (this.jogos[MAXJOGOS - 1] != null)
            return true;

        else
            return false;
    }

    public void ordenaPorPlatf() {
        Arrays.sort(this.jogos, new Comparator<Jogo>() {
            public int compare(Jogo o1, Jogo o2) {

                if (o1 == null || o2 == null)
                    return 0;

                if (o1.getPlataforma().equals(o2.getPlataforma())) {
                    String a = (String) o1.getNome();
                    String b = (String) o2.getNome();
                    return a.compareTo(b);
                } else {
                    String a = (String) o1.getPlataforma();
                    String b = (String) o2.getPlataforma();
                    return a.compareTo(b);
                }

            }
        });
    }

   /* public void ordenaPorCond()
    {
        Arrays.sort(this.jogos, new Comparator<Jogo>()
        {
            public int compare(Jogo o1, Jogo o2)
            {
                Data a = new Data(17, 05, 2020);
                Data b = (Data) o2.getDataDeLanc();
                return a.compareTo(b);
            }
        });
    }*/

    @Override
    public String toString() {
        sb = new StringBuilder();
        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                sb.append(jogos[i].toString());
            }
        }

        return sb.toString();
    }

}
