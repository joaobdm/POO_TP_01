public class VetorJogos {

    private Jogo[] jogos;
    private StringBuilder sb = new StringBuilder();

    VetorJogos() {
        jogos = new Jogo[1000];
    }

    public boolean buscaJogo(String nomeDoJogo, String plataforma) {

        for (Jogo jogo : jogos) {
            if (jogo.getNome().equals(nomeDoJogo)) {
                if (jogo.getPlataforma().equals(plataforma))
                    return true;
            }
        }
        return false;
    }

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

    @Override
    public String toString(){
        for (Jogo jogo : jogos) {
            while (jogo != null){
                sb.append(jogo.toString());
            }
        }
        return sb.toString();
    }

}
