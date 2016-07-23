package ms.manipulador;

import ms.leitor.LeitorAtividadesConsuni;
import ms.modelo.AtividadeConsuni;
import ms.modelo.ModeloAtividade;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 08/07/2016.
 *
 * Classe genérica responsável por converter string de atividades em objetos
 */
public abstract class ManipuladorGenerico {

    String REGEX; //Expressão regular para extrair essa atividade
    Pattern pattern;
    List<ModeloAtividade> modelos; //Modelos de atividades extraidos

    /**
     * Construtor padrão da classe.
     */
    public ManipuladorGenerico() {
        modelos = new ArrayList<ModeloAtividade>();
    }

    /**
     * Método que extrai atividades. Deve ser implementado para cada tipo de atividade.
     * @param atividades String com as atividades a serem convertidas em objetos
     * @return lista de objetos das atividades
     */
    public List<ModeloAtividade> ExtrairAtividades(List<String> atividades) {
        pattern = Pattern.compile(REGEX, Pattern.DOTALL);

        for(String atividade: atividades) {
            Matcher buscador = pattern.matcher(atividade);

            if (buscador.find()) {
                ModeloAtividade modelo = MontarModelo(buscador);
                modelos.add(modelo);
            }
        }

        return modelos;
    }

    /**
     *
     * @param buscador
     * @return
     */
    public abstract ModeloAtividade MontarModelo(Matcher buscador);

}