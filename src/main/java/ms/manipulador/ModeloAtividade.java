package ms.manipulador;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by Bruno on 07/07/2016.
 */
public class ModeloAtividade {
    private static int contadorAtividade = 0;
    private int sequencialAtividade;
    private char[] descricaoAtividade;
    private String qtdeHorasAtividade;
    private DateTime dtInicioAtividade;
    private DateTime dtFimAtividade;



    /**
     * Construtor de atividades que não contenham o dado CHA
     * @param descricaoAtividade
     * @param dtInicioAtividade
     * @param dtFimAtividade
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
     * @param descricaoAtividade
     * @param qtdeHorasAtividade
     * @param dtInicioAtividade
     * @param dtFimAtividade
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
     * Padroniza todos os tamanhos de Strings para 512 caracteres adicionando pontos no final do nome da atividade
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
