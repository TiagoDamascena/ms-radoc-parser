package ms.leitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by Tiago on 09/07/2016.
 *
 * Classe responsável por ler Atividades de Ensino
 */
public class LeitorAtividadesDeEnsino extends LeitorGenerico {

    public LeitorAtividadesDeEnsino(File entrada) throws IOException{
        super(entrada);
        REGEX = "Atividades de ensino([\\p{L}\\s-\\d]+)Atividades de orientação";
    }

    @Override
    public List<String> SepararAtividades() {
        List atividades = new ArrayList<String>();
        Matcher matcher = obterMatcher(REGEX, conteudoArquivo);


        if(matcher.find()){
            conteudoArquivo = matcher.group();
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades de ensino","");
            conteudoArquivo = conteudoArquivo.replaceAll("Atividades de orientação","");
            conteudoArquivo = conteudoArquivo.replaceAll("\\n+"," ");
            String atividadesFormatadas = conteudoArquivo.replaceAll("\\r+","");
            String[] arrayAtividades = atividadesFormatadas.split("\\d+\\s(SIM|NÃO)");

            atividades = paraArrayList(arrayAtividades);
        }

        return atividades;
    }

    @Override
    public void removerLinhasInuteis() {
        super.removerLinhasInuteis();
        conteudoArquivo = conteudoArquivo.replaceAll("Curso Disciplina CHA Ano Sem Turma Sub Nº alunos Nº sub CHT CHP CHAC Conjug","");
        conteudoArquivo = conteudoArquivo.replaceAll("RGCG - Regime de Graduação Semestral","");
        conteudoArquivo = conteudoArquivo.replaceAll("LEGENDA: CHA - Carga horária da atividade \\| Sem - Semestre \\| Sub - Subturma \\| CHT, CHP e CHAC - Carga horária teórica, prática e acessória \\| Conjug - Disciplina conjugada","");
    }
}
