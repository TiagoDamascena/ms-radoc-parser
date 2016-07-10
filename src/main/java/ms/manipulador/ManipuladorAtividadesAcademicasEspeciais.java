package ms.manipulador;

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
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModeloAtividadesAcademicasEspeciais(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
