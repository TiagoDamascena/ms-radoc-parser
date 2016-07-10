package ms.escritor;

import ms.manipulador.ModeloAtividade;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Bruno Messias on 08/07/2016.
 */
public class ConfeccionaDocumento {

    /**
     * Este método recebe a lista de atividades, e publica-as, ordenando-as por parágrafo.
     * O arquivo é gerado na pasta raiz do projeto
     * @param modeloAtividadeList
     * @throws IOException
     */
    public static void popularDocumento(List<ModeloAtividade> modeloAtividadeList) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            FileWriter atividadesExtraídas = new FileWriter(new File("AtividadesExtraídas.txt"));
            PrintWriter printWriter = new PrintWriter(atividadesExtraídas);
            for(ModeloAtividade atividade :modeloAtividadeList) {
                printWriter.println("Id da Atividade = "+atividade.getSequencialAtividade());
                printWriter.println(atividade.getDescricaoAtividade());
                printWriter.println("Quantidade de horas = "+atividade.getQtdeHorasAtividade());
                printWriter.println("Data de inicio = "+  simpleDateFormat.format(atividade.getDtInicioAtividade().toDate()));
                printWriter.println("Data de fim = "+ simpleDateFormat.format(atividade.getDtFimAtividade().toDate()));
                printWriter.println();
            }
            atividadesExtraídas.close();
        } catch(IOException e){
            System.out.println("ConfeccionaDocumento" + e.getMessage());
        }
    }
}
