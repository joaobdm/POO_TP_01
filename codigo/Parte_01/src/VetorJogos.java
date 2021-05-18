public class VetorJogos {

    private Jogo[] jogos;
    private StringBuilder sb = new StringBuilder();
    
    VetorJogos() {
        jogos = new Jogo[1000];
    }

    
    public Jogo[] getJogos()
    {
    	return jogos;
    }
    
    /**
     * Através do nome e plataforma, detecta se o jogo já existe no vetor em questão.
     * @param nomeDoJogo
     * @param plataforma
     * @return
     */
    public boolean buscaJogo(String nomeDoJogo, String plataforma) {

        for (Jogo jogo : jogos) {
            if (jogo.getNome().equals(nomeDoJogo)) {
                if (jogo.getPlataforma().equals(plataforma))
                    return true;
            }
        }
        return false;
    }

    /**
     * Adiciona um Jogo j ao vetor caso já não exista o mesmo jogo nesse vetor.
     * @param j Jogo a ser inserido no vetor.
     * @return true se a operação foi feita com sucesso, false caso contrário.
     */
    public boolean adicionarJogo(Jogo j) {
        if (!buscaJogo(j.getNome(), j.getPlataforma())) {
            for (Jogo jogo : jogos) {
                if (jogo == null) {
                    jogo = j;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove um jogo do vetor e diminui uma posição de cada jogo posterior do vetor, se houver.
     * @param posi Posição do vetor onde o jogo se encontra
     * @return Jogo removido
     */
    public Jogo removerJogo(int posi) {
        Jogo removido = null;
        removido = jogos[posi];
        jogos[posi] = null;
        while (this.jogos[posi+1] != null){
            jogos[posi] = jogos[posi+1];
            posi++;
            jogos[posi] = null;
        }
        return removido;
    }

    @Override
    public String toString() {
        for (Jogo jogo : jogos) {
            while (jogo != null) {
                sb.append(jogo.toString());
            }
        }
        return sb.toString();
    }

}
