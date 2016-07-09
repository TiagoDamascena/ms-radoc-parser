package ms.leitor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiago on 07/07/2016.
 *
 * Classe responsável por ler PDFs
 */
public abstract class LeitorGenerico {
    PDDocument documento;
    String conteudoArquivo;
    String REGEX;

    /**
     * Carrega o arquivo a ser lido
     * @param entrada arquivo que será lido
     * @throws IOException caso o arquivo não seja encontrado
     */
    public LeitorGenerico(File entrada) throws IOException{
        documento = PDDocument.load(entrada);
    }

    public List<String> BuscarAtividades() throws IOException{
        LerAtividades();
        return SepararAtividades();
    }

    public abstract List<String> SepararAtividades();

    public void LerAtividades() throws IOException{
        BufferedWriter escritor;
        PDFTextStripper conversor = new PDFTextStripper();
        String texto = conversor.getText(documento);
        documento.close();

        conteudoArquivo = texto;

        removerLinhasInuteis();
    }

    public List<String> paraArrayList(String[] entrada){
        ArrayList array = new ArrayList<String>();

        for(int i = 0; i < entrada.length; i++){
            entrada[i] = entrada[i].trim();
            if(entrada[i] != ""){
                array.add(entrada[i].trim());
            }
        }
        return array;
    }

    public void removerLinhasInuteis() {
        conteudoArquivo = conteudoArquivo.replaceAll("Data: \\d+/\\d+/\\d+ \\d+:\\d+:\\d+\\s+[\\w\\s]+Página\\s+\\d+\\s+/\\s+\\d+","");
        conteudoArquivo = conteudoArquivo.replaceAll("EXTRATO DAS ATIVIDADES\\s+-\\s+ANO BASE:\\s+\\d+","");
        conteudoArquivo = conteudoArquivo.replaceAll("UNIVERSIDADE FEDERAL DE GOIÁS\\r\\nSISTEMA DE CADASTRO DE ATIVIDADES DOCENTES","");
    }

    public Matcher obterMatcher(String regex, String conteudo) {
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(conteudo);
    }
}
