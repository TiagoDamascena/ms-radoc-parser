package ms.manipulador;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 08/07/2016.
 */
public class ManipuladorGenerico {

    public static List<ModeloAtividade> buscarAtividadesDeEnsino(List<String> atividades) {
        List<ModeloAtividade> modelos = new ArrayList<ModeloAtividade>();

        String REGEX = "(.+) (\\d{1,3}) (\\d{4}) (\\d) [A-Z0-9]+ \\w*\\s?\\d{1,3} \\d \\d{1,3} \\d{1,3}";
        Pattern pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher matcher = pattern.matcher(atividade);

            if (matcher.find()) {
                String descrição = matcher.group(1);
                int qtdHoras = Integer.parseInt(matcher.group(2));
                int ano = Integer.parseInt(matcher.group(3));
                int semestre = Integer.parseInt(matcher.group(4));
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
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
