package ms.modelo;

import java.text.DecimalFormat;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by Bruno on 07/07/2016.
 *
 * Classe que representa o modelo de uma atividade
 */
public class ModeloAtividade {
    private static int contadorAtividade;

    private char[] codGrupoPontuacao;
    private int pontos;
    private int sequencialAtividade;
    private char[] descricaoAtividade;
    private String qtdeHorasAtividade;
    private DateTime dtInicioAtividade;
    private DateTime dtFimAtividade;

    /**
     * Construtor de atividades que não contenham o dado CHA
     * @param descricaoAtividade descrição da atividade
     * @param dtInicioAtividade data de início da atividade
     * @param dtFimAtividade data de término da atividade
     */
    public ModeloAtividade(String codGrupoPontuacao, int pontos, String descricaoAtividade, DateTime dtInicioAtividade,DateTime dtFimAtividade){
        this.setCodGrupoPontuacao(codGrupoPontuacao);
        this.pontos = pontos;
        this.sequencialAtividade = ++contadorAtividade;
        this.setDescricaoAtividade(descricaoAtividade);
        this.dtInicioAtividade = dtInicioAtividade;
        this.dtFimAtividade = dtFimAtividade;
        calcularCHA();
    }

    /**
     * Construtor de atividade que contém todos os dados necessários
     * @param descricaoAtividade descrição da atividade
     * @param qtdeHorasAtividade carga horária anual da atividade
     * @param dtInicioAtividade data de início da atividade
     * @param dtFimAtividade data de término da atividade
     */
    public ModeloAtividade(String codGrupoPontuacao, int pontos, String descricaoAtividade, int qtdeHorasAtividade, DateTime dtInicioAtividade,DateTime dtFimAtividade){
        this.setCodGrupoPontuacao(codGrupoPontuacao);
        this.pontos = pontos;
        this.sequencialAtividade = ++contadorAtividade;
        this.setDescricaoAtividade(descricaoAtividade);
        this.dtInicioAtividade = dtInicioAtividade;
        this.dtFimAtividade = dtFimAtividade;
        this.qtdeHorasAtividade = new DecimalFormat("#.##").format(qtdeHorasAtividade);
    }

    /**
     * Calcula a quantidade de CHA, quando a atividade não contém o campo respectivo.
     */
    private void calcularCHA(){
            Duration diffDias = new Duration(dtInicioAtividade,dtFimAtividade);
            this.qtdeHorasAtividade = new DecimalFormat("#.##").format(diffDias.getStandardDays()*8);
    }


    /**
     * Padroniza todos os tamanhos de Strings para 512 caracteres adicionando pontos no final da descrição da atividade
     * @param descricaoAtividade
     */
    private void setDescricaoAtividade(String descricaoAtividade) {

        if(descricaoAtividade.length() > 512) {
            descricaoAtividade = descricaoAtividade.substring(0, 511);
        }
        this.descricaoAtividade = descricaoAtividade.toCharArray();
    }

    private void setCodGrupoPontuacao(String codGrupoPontuacao) {
        if(codGrupoPontuacao.length() > 12) {
            codGrupoPontuacao = codGrupoPontuacao.substring(0, 11);
        }

        this.codGrupoPontuacao = codGrupoPontuacao.toCharArray();
    }

    /*-----GETTERS-----*/

    public char[] getCodGrupoPontuacao() {
        return codGrupoPontuacao;
    }

    public int getPontos() {
        return pontos;
    }

    public int getSequencialAtividade() {
        return sequencialAtividade;
    }

    public char[] getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public String getQtdeHorasAtividade() {
        return qtdeHorasAtividade;
    }

    public DateTime getDtInicioAtividade() {
        return dtInicioAtividade;
    }

    public DateTime getDtFimAtividade() {
        return dtFimAtividade;
    }
}
