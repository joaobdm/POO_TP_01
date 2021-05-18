import java.util.Arrays;
import java.util.Comparator;

import javax.print.attribute.standard.JobOriginatingUserName;

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
    /**
    * ordena o vetor em ordem alfabética primeiro comprarando as plataformas, se ouver um "empate", o desempate
    * será entre o nome do jogo
    * @param o1 jogo que é reorganizado
    * @param o2 jogo que é comparado para reorganização
    */
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

    public void ordenaPorCond()
    {
        Arrays.sort(this.jogos, new Comparator<Jogo>()
        {
            public int compare(Jogo o1, Jogo o2)
            {
                Integer a = (Integer) o1.getDataDeLanc().getAno();
                Integer b = (Integer) o2.getDataDeLanc().getAno();
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
