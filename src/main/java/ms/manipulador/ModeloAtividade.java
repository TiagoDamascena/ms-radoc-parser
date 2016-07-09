package ms.manipulador;

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
    public ModeloAtividade(String descricaoAtividade, DateTime dtInicioAtividade,DateTime dtFimAtividade){
        this.sequencialAtividade = ++contadorAtividade;
        popularArrayChar(descricaoAtividade);
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
    public ModeloAtividade(String descricaoAtividade, int qtdeHorasAtividade, DateTime dtInicioAtividade,DateTime dtFimAtividade){
        this.sequencialAtividade = ++contadorAtividade;
        popularArrayChar(descricaoAtividade);
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
    private void popularArrayChar(String descricaoAtividade){

        for ( int i = descricaoAtividade.length(); i < 512 ; i++){
            descricaoAtividade = descricaoAtividade+".";
        }
        this.descricaoAtividade = descricaoAtividade.toCharArray();
    }

    /*-----GETTERS-----*/

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
