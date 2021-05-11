public class Data {

    static private int contadorExecucao = 0;
    static private Data dataRecente = new Data();
    private int dia;
    private int mes;
    private int ano;

    public Data() {
        if (contadorExecucao < 2) {
            ano = 1990;
            mes = 1;
            dia = 1;
            contadorExecucao++;
        }

        else {
            dataRecente.passarDia();
            this.ano = dataRecente.ano;
            this.mes = dataRecente.mes;
            this.dia = dataRecente.dia;
        }
    }

    public Data(int dia, int mes, int ano) {
        if (contadorExecucao < 2)
            contadorExecucao++;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;

        if (!dataValida()) {
            this.dia = 1;
            this.mes = 1;
            this.ano = 1900;
        } else {
            if (maisRecente())
                atualizarDataMaisRecente();
        }

    }

    private boolean verificaBissexto() {
        boolean resp = false;
        if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
            resp = true;
        }
        return resp;
    }

    private boolean verificaTritaEUmDias() {
        boolean resp = false;

        if (mes <= 7 && mes % 2 != 0)
            resp = true;
        else if (mes >= 8 && mes % 2 == 0)
            resp = true;
        return resp;
    }

    private boolean dataValida() {
        boolean resp = true;

        if (!anoValido() || !mesValido() || !diaValido())
            resp = false;

        return resp;
    }

    private boolean anoValido() {
        boolean resp = true;
        if (this.ano < 1900)
            resp = false;
        return resp;
    }

    private boolean mesValido() {
        boolean resp = true;
        if (mes < 1 || mes > 12)
            resp = false;
        return resp;
    }

    private boolean diaValido() {
        boolean resp = true;

        if (mes == 2) {
            if (verificaBissexto()) {
                if (dia > 29 || dia < 1)
                    resp = false;
            } else {
                if (dia > 28 || dia < 1)
                    resp = false;
            }
        } else if (verificaTritaEUmDias()) {
            if (dia > 31 || dia < 1) {
                resp = false;
            }
        } else {
            if (dia > 30 || dia < 1) {
                resp = false;
            }
        }
        return resp;
    }

    private void passarAno() {
        ano++;
    }

    private void passarMes() {
        mes++;
        if (!mesValido()) {
            mes = 1;
            passarAno();
        }
    }

    private void passarDia() {
        dia++;
        if (!diaValido()) {
            dia = 1;
            passarMes();
        }
    }

    private boolean maisRecente() {
        boolean resp = false;
        if (this.ano >= dataRecente.ano) {
            if (this.ano > dataRecente.ano) {
                resp = true;
            } else {
                if (this.mes >= dataRecente.mes) {
                    if (this.mes > dataRecente.mes) {
                        resp = true;
                    } else {
                        if (this.dia >= dataRecente.dia) {
                            resp = true;
                        }
                    }
                }
            }
        }
        return resp;
    }

    private void atualizarDataMaisRecente() {
        dataRecente.ano = this.ano;
        dataRecente.mes = this.mes;
        dataRecente.dia = this.dia;
    }

    /**
     * Compara duas datas e retorna a mais recente
     * 
     * @param verificada Data passada por parâmetro a ser comparada com a data do
     *                   objeto em questão
     * @return retorna a data mais recente
     */
    public Data comparaData(Data verificada) {
        Data resp = verificada;
        if (this.ano >= dataRecente.ano) {
            if (this.ano > dataRecente.ano) {
                resp = this;
            } else {
                if (this.mes >= dataRecente.mes) {
                    if (this.mes > dataRecente.mes) {
                        resp = this;
                    } else {
                        if (this.dia >= dataRecente.dia) {
                            resp = this;
                        }
                    }
                }
            }
        }
        return resp;
    }

    public void mostrarData() {
        System.out.printf("DATA: %d/%d/%d", dia, mes, ano);
        System.out.println();
    }

    public void somarDias(int diasExtras) {
        if (diasExtras >= 0) {
            for (int i = 0; i < diasExtras; i++) {
                passarDia();
            }
            if (maisRecente())
                atualizarDataMaisRecente();
        } else
            System.out.println("PARÂMETRO INVÁLIDO, OPERAÇÃO CANCELADA");
    }

    public void alterarData(int novoDia, int novoMes, int novoAno) {

        Data novaData = new Data();

        novaData.ano = novoAno;
        novaData.mes = novoMes;
        novaData.dia = novoDia;

        if (novaData.dataValida()) {
            ano = novoAno;
            mes = novoMes;
            dia = novoDia;
            if (novaData.maisRecente())
                novaData.atualizarDataMaisRecente();
        } else
            System.out.println("NOVA DATA INVÁLIDA, NENHUMA ALTERAÇÃO APLICADA");
    }

    @Override
    public String toString(){
        String s;
        s = dia+"/"+mes+"/"+ano;
        return s;
    }

    // public void altera(){
    //     contadorExecucao = 5;        
    // }

    // public void alteraMostra(){        
    //     System.out.println(contadorExecucao);
    // }
}