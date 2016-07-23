package ms.manipulador;

import ms.modelo.ModeloAtividade;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 09/07/2016.
 *
 * Classe responsável por converter string de Atividades de Extensão em objetos
 */
public class ManipuladorAtividadesDeExtensao extends ManipuladorGenerico {

    public ManipuladorAtividadesDeExtensao() {
        super();
        REGEX = ".+?CHA:.+?(\\d+).+?Data início:.+?(\\d+\\/\\d+\\/\\d+).+?Data término:.+?(\\d+\\/\\d+\\/\\d+).+?Descrição da atividade:.+?(.+).+?Descrição da clientela:.+?(.+)";
    }

    @Override
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModeloAtividadesDeExtensao(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }
}
