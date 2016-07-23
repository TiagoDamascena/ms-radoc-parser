package ms.manipulador;

import ms.leitor.LeitorAtividadesConsuni;
import ms.modelo.AtividadeConsuni;
import ms.modelo.ModeloAtividade;
import org.joda.time.DateTime;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 09/07/2016.
 *
 * Classe responsável por converter string de Atividades de Ensino em objetos
 */
public class ManipuladorAtividadesDeEnsino extends ManipuladorGenerico {

    public ManipuladorAtividadesDeEnsino() {
        super();
        REGEX = "(.+) (\\d{1,3}) (\\d{4}) (\\d) [A-Z0-9]+ \\w*\\s?\\d{1,3} \\d \\d{1,3} \\d{1,3}";
    }

    @Override
    public ModeloAtividade MontarModelo(Matcher buscador) {
        String descrição = buscador.group(1);
        int qtdHoras = Integer.parseInt(buscador.group(2));
        int ano = Integer.parseInt(buscador.group(3));
        int semestre = Integer.parseInt(buscador.group(4));
        DateTime dtInicio;
        DateTime dtFim;

        AtividadeConsuni atividade = LeitorAtividadesConsuni.buscarAtividade("Aulas presenciais na graduação");

        String codigo = atividade.getCodigo();
        int pontos = Integer.parseInt(atividade.getPontos());

        if(semestre == 1) {
            dtInicio = new DateTime(ano, 1, 1, 0, 0);
            dtFim = new DateTime(ano, 7, 31, 0, 0);
        } else {
            dtInicio = new DateTime(ano, 8, 1, 0, 0);
            dtFim = new DateTime(ano, 12, 31, 0, 0);
        }

        ModeloAtividade modelo = new ModeloAtividade(codigo, pontos, descrição, qtdHoras, dtInicio, dtFim);
        return modelo;
    }
}
