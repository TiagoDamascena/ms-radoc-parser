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
 * Classe responsável por converter string de Atividades Administrativas em objetos
 */
public class ManipuladorAtividadesAdministrativas extends ManipuladorGenerico {

    public ManipuladorAtividadesAdministrativas() {
        super();
        REGEX = "([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)Descrição:\\s+([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)Órgão emissor[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?CHA:\\s+(\\d+)\\s+Data início:\\s+(\\d{2}\\/\\d{2}\\/\\d{4})\\s+Data término:\\s+(\\d{2}\\/\\d{2}\\/\\d{4})";
    }

    @Override
    public ModeloAtividade MontarModelo(Matcher buscador) {
        String descrição = buscador.group(1) + " - " + buscador.group(2);
        int qtdHoras = Integer.parseInt(buscador.group(3));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(4));
        DateTime dtFim = formatter.parseDateTime(buscador.group(5));

        AtividadeConsuni atividade = LeitorAtividadesConsuni.buscarAtividade(buscador.group(1).trim());

        String codigo = atividade.getCodigo();
        int pontos = Integer.parseInt(atividade.getPontos());

        ModeloAtividade modelo = new ModeloAtividade(codigo, pontos, descrição.trim(), qtdHoras, dtInicio, dtFim);
        return modelo;
    }
}
