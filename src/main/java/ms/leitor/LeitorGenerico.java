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
 * Classe genérica responsável por ler atividades de PDFs
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

    /**
     * Chama os métodos responsáveis por ler o arquivo PDF e identificar as atividades presentes nele
     * @return uma lista de atividades em forma de strings
     * @throws IOException caso ocorra um erro ao ler o arquivo
     */
    public List<String> BuscarAtividades() throws IOException{
        LerAtividades();
        return SepararAtividades();
    }

    /**
     * Método abstrato onde deve ser definido como separar cada tipo de atividade do restante do arquivo
     * @return uma lista de atividades em forma de strings
     */
    public abstract List<String> SepararAtividades();

    /**
     * Lê o arquivo PDF e coloca o seu conteúdo em uma string
     * @throws IOException caso ocorra algum erro ao ler o arquivo
     */
    public void LerAtividades() throws IOException{
        BufferedWriter escritor;
        PDFTextStripper conversor = new PDFTextStripper();
        String texto = conversor.getText(documento);
        documento.close();

        conteudoArquivo = texto;

        removerLinhasInuteis();
    }

    /**
     * Transforma um array de Strings em uma lista de Strings removendo Strings vazias
     * @param entrada array de strings a ser transformado
     * @return lista de strings
     */
    public List<String> paraArrayList(String[] entrada){
        ArrayList array = new ArrayList<String>();

        for(int i = 0; i < entrada.length; i++){
            entrada[i] = entrada[i].trim();
            if(!entrada[i].equals("")){
                array.add(entrada[i].trim());
            }
        }
        return array;
    }

    /**
     * Remove algumas linhas inúteis da string gerada a partir do arquivo PDF
     */
    public void removerLinhasInuteis() {
        conteudoArquivo = conteudoArquivo.replaceAll("Data: \\d+/\\d+/\\d+ \\d+:\\d+:\\d+\\s+[\\w\\s]+Página\\s+\\d+\\s+/\\s+\\d+","");
        conteudoArquivo = conteudoArquivo.replaceAll("EXTRATO DAS ATIVIDADES\\s+-\\s+ANO BASE:\\s+\\d+","");
        conteudoArquivo = conteudoArquivo.replaceAll("UNIVERSIDADE FEDERAL DE GOIÁS\\r\\nSISTEMA DE CADASTRO DE ATIVIDADES DOCENTES","");
    }

    /**
     * Retorna um Matcher para um REGEX e uma Strind específicos
     * @param regex Expressão regular
     * @param conteudo String onde será buscada essa expressão
     * @return Matcher de expressões regulares
     */
    public Matcher obterMatcher(String regex, String conteudo) {
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(conteudo);
    }
}
