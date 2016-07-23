package ms.manipulador;

import ms.modelo.ModeloAtividade;

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
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModeloAtividadesDeEnsino(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
