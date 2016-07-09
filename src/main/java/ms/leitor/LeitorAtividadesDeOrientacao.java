package ms.leitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by Tiago on 09/07/2016.
 */
public class LeitorAtividadesDeOrientacao extends LeitorGenerico {

    public LeitorAtividadesDeOrientacao(File entrada) throws IOException{
        super(entrada);
        REGEX = "Atividades de orientação([\\p{L}\\s-\\d\\W]+)Atividades em projetos";
    }

    @Override
    public List<String> SepararAtividades() {
        List atividades = new ArrayList<String>();
        Matcher matcher = obterMatcher(REGEX, conteudoArquivo);


        if(matcher.find()){
            conteudoArquivo = matcher.group();
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades de orientação","");
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades em projetos","");
            conteudoArquivo = conteudoArquivo.replaceAll("[\\n\\r\\t]+"," ");
            String atividadesFormatadas = conteudoArquivo.replaceAll("\\r+","");
            String[] arrayAtividades = atividadesFormatadas.split("Tipo Orientação:\\s\\w*\\s");

            atividades = paraArrayList(arrayAtividades);
        }

        return atividades;
    }
}
