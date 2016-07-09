package ms.manipulador;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 08/07/2016.
 */
public abstract class ManipuladorGenerico {

    String REGEX;
    Pattern pattern;
    List<ModeloAtividade> modelos;

    public ManipuladorGenerico() {
        modelos = new ArrayList<ModeloAtividade>();
    }

    public abstract List<ModeloAtividade> ExtrairAtividades(List<String> atividades);

    public ModeloAtividade MontarModeloGenerico(Matcher buscador) {
        String descrição = buscador.group(1);
        int qtdHoras = Integer.parseInt(buscador.group(2));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(3));
        DateTime dtFim = formatter.parseDateTime(buscador.group(4));

        ModeloAtividade modelo = new ModeloAtividade(descrição, qtdHoras, dtInicio, dtFim);
        return modelo;
    }

    public ModeloAtividade MontarModeloGenerico2(Matcher buscador) {
        String descrição = buscador.group(1) + " - " + buscador.group(2);
        int qtdHoras = Integer.parseInt(buscador.group(3));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(4));
        DateTime dtFim = formatter.parseDateTime(buscador.group(5));

        ModeloAtividade modelo = new ModeloAtividade(descrição, qtdHoras, dtInicio, dtFim);
        return modelo;
    }

    public ModeloAtividade MontarModeloAtividadesDeEnsino(Matcher buscador) {
        String descrição = buscador.group(1);
        int qtdHoras = Integer.parseInt(buscador.group(2));
        int ano = Integer.parseInt(buscador.group(3));
        int semestre = Integer.parseInt(buscador.group(4));
        DateTime dtInicio;
        DateTime dtFim;

        if(semestre == 1) {
            dtInicio = new DateTime(ano, 1, 1, 0, 0);
            dtFim = new DateTime(ano, 7, 31, 0, 0);
        } else {
            dtInicio = new DateTime(ano, 8, 1, 0, 0);
            dtFim = new DateTime(ano, 12, 31, 0, 0);
        }

        ModeloAtividade modelo = new ModeloAtividade(descrição, qtdHoras, dtInicio, dtFim);
        return modelo;
    }

    public ModeloAtividade MontarModeloAtividadesDeExtensao(Matcher buscador) {
        String descrição = buscador.group(4) + " - " + buscador.group(5);
        int qtdHoras = Integer.parseInt(buscador.group(1));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(2));
        DateTime dtFim = formatter.parseDateTime(buscador.group(3));

        ModeloAtividade modelo = new ModeloAtividade(descrição, qtdHoras, dtInicio, dtFim);

        return modelo;
    }

    public ModeloAtividade MontarModeloAtividadesAcademicasEspeciais(Matcher buscador) {
        String descrição = buscador.group(1) + " - " + buscador.group(5) + " - " + buscador.group(6);
        int qtdHoras = Integer.parseInt(buscador.group(2));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtInicio = formatter.parseDateTime(buscador.group(3));
        DateTime dtFim = formatter.parseDateTime(buscador.group(4));

        ModeloAtividade modelo = new ModeloAtividade(descrição, qtdHoras, dtInicio, dtFim);

        return modelo;
    }
}