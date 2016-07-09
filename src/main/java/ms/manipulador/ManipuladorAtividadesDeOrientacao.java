package ms.manipulador;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 09/07/2016.
 */
public class ManipuladorAtividadesDeOrientacao extends ManipuladorGenerico {

    public ManipuladorAtividadesDeOrientacao() {
        super();
        REGEX = "Título do trabalho:([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)Tabela:([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)Orientando:[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?CHA:\\s+(\\d+)\\s+Data início:\\s+(\\d+\\/\\d+\\/\\d+)[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?Data término:\\s+(\\d+\\/\\d+\\/\\d+)";
    }

    @Override
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModeloGenerico2(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
