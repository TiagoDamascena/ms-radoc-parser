package ms.leitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by Tiago on 09/07/2016.
 */
public class LeitorAtividadesDeQualificação extends LeitorGenerico {

    public LeitorAtividadesDeQualificação(File entrada) throws IOException{
        super(entrada);
        REGEX = "Atividades de qualificação[\\s\\d\\p{L}\\/\\-\\.\\_\\:\\,\\>\\=\\<\\(\\'\\\"\\@\\!\\)]+?Atividades acadêmicas especiais";
    }

    @Override
    public List<String> SepararAtividades() {
        List atividades = new ArrayList<String>();
        Matcher matcher = obterMatcher(REGEX, conteudoArquivo);


        if(matcher.find()){
            conteudoArquivo = matcher.group();
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades de qualificação","");
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades acadêmicas especiais","");
            conteudoArquivo = conteudoArquivo.replaceAll("[\\n\\r\\t]+"," ");
            String atividadesFormatadas = conteudoArquivo.replaceAll("\\r+","");
            String[] arrayAtividades = atividadesFormatadas.split("Tabela:");

            atividades = paraArrayList(arrayAtividades);
        }

        return atividades;
    }
}
