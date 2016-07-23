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
 * Classe responsável por converter string de Atividades em Projetos em objetos
 */
public class ManipuladorAtividadesEmProjetos extends ManipuladorGenerico {

    public ManipuladorAtividadesEmProjetos() {
        super();
        REGEX = "([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)\\s+Tabela:\\s+([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)Unidade Responsável:[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?CHA:\\s+(\\d+)\\s+Data Início:\\s+(\\d+\\/\\d+\\/\\d+)\\s+Data Término:\\s+(\\d+\\/\\d+\\/\\d+)";
    }

    @Override
    public ModeloAtividade MontarModelo(Matcher buscador) {
        String descrição = buscador.group(1) + " - " + buscador.group(2);
        int qtdHoras = Integer.parseInt(buscador.group(3));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(4));
        DateTime dtFim = formatter.parseDateTime(buscador.group(5));

        AtividadeConsuni atividade = LeitorAtividadesConsuni.buscarAtividade(buscador.group(2).trim());

        String codigo = atividade.getCodigo();
        int pontos = Integer.parseInt(atividade.getPontos());

        ModeloAtividade modelo = new ModeloAtividade(codigo, pontos, descrição.trim(), qtdHoras, dtInicio, dtFim);
        return modelo;
    }
}
