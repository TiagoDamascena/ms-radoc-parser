package ms.manipulador;

import ms.modelo.ModeloAtividade;

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
