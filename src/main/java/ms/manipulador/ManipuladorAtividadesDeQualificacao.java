package ms.manipulador;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 09/07/2016.
 *
 * Classe responsável por converter string de Atividades de Qualificação em objetos
 */
public class ManipuladorAtividadesDeQualificacao extends ManipuladorGenerico {

    public ManipuladorAtividadesDeQualificacao() {
        super();
        REGEX = "\\s+[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?Descrição:\\s+([\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?)CHA:\\s+(\\d+)\\s+Data de início:\\s+(\\d{2}\\/\\d{2}\\/\\d{4})\\s+Data de término:\\s+(\\d{2}\\/\\d{2}\\/\\d{4})";
    }

    @Override
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModeloGenerico(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
