package ms.manipulador;

import ms.leitor.LeitorAtividadesConsuni;
import ms.modelo.AtividadeConsuni;
import ms.modelo.ModeloAtividade;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 09/07/2016.
 *
 * Classe responsável por converter string de Atividades Acadêmicas Especiais em objetos
 */
public class ManipuladorAtividadesAcademicasEspeciais extends ManipuladorGenerico {

    public ManipuladorAtividadesAcademicasEspeciais() {
        super();
        REGEX = "(.+?)CHA:.+?(\\d+).+?Data início:.+?(\\d{2}\\/\\d{2}\\/\\d{4}).+?Data término:.+?(\\d{2}\\/\\d{2}\\/\\d{4}).+?Descrição Complementar:.+?(.+?)Descrição da Clientela:(.+)";
    }

    @Override
    public ModeloAtividade MontarModelo(Matcher buscador) {
        String descrição = buscador.group(1) + " - " + buscador.group(5) + " - " + buscador.group(6);
        int qtdHoras = Integer.parseInt(buscador.group(2));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(3));
        DateTime dtFim = formatter.parseDateTime(buscador.group(4));

        AtividadeConsuni atividade = LeitorAtividadesConsuni.buscarAtividade(buscador.group(1).trim());

        String codigo = atividade.getCodigo();
        int pontos = Integer.parseInt(atividade.getPontos());

        ModeloAtividade modelo = new ModeloAtividade(codigo, pontos, descrição, qtdHoras, dtInicio, dtFim);

        return modelo;
    }
}
