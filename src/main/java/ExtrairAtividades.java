import ms.escritor.ConfeccionaDocumento;
import ms.leitor.*;
import ms.manipulador.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 06/07/2016.
 *
 * Classe principal do projeto.
 */
public class ExtrairAtividades {
    /**
     * Recebe um arquivo RADOC do tipo PDF e extrai as atividades presentes nele
     * @param args arquivo RADOC a ser lido
     */
    public static void main(String args[]) {
        String caminhoArquivo = args[0];

        File entrada = new File(caminhoArquivo);
        try {
            ConfeccionaDocumento.popularDocumento(Extrair(entrada));
        } catch (IOException ex) {
            System.out.println("Erro ao ler o arquivo, tente novamente.");
        }
    }

    /**
     * Executa as chamadas de métodos para ler as atividades e convertê-las de texto para objetos
     * @param entrada arquivo a ser lido
     * @return lista de atividades
     * @throws IOException caso ocorra algum erro ao ler o arquivo
     */
    public static List<ModeloAtividade> Extrair(File entrada) throws IOException{
        List<ModeloAtividade> modelos = new ArrayList<ModeloAtividade>();

        List<LeitorGenerico> leitores = new ArrayList<LeitorGenerico>();
        List<ManipuladorGenerico> manipuladores = new ArrayList<ManipuladorGenerico>();

        leitores.add(new LeitorAtividadesDeEnsino(entrada));
        leitores.add(new LeitorAtividadesDeOrientacao(entrada));
        leitores.add(new LeitorAtividadesEmProjetos(entrada));
        leitores.add(new LeitorAtividadesDeExtensao(entrada));
        leitores.add(new LeitorAtividadesDeQualificacao(entrada));
        leitores.add(new LeitorAtividadesAcademicasEspeciais(entrada));
        leitores.add(new LeitorAtividadesAdministrativas(entrada));

        manipuladores.add(new ManipuladorAtividadesDeEnsino());
        manipuladores.add(new ManipuladorAtividadesDeOrientacao());
        manipuladores.add(new ManipuladorAtividadesEmProjetos());
        manipuladores.add(new ManipuladorAtividadesDeExtensao());
        manipuladores.add(new ManipuladorAtividadesDeQualificacao());
        manipuladores.add(new ManipuladorAtividadesAcademicasEspeciais());
        manipuladores.add(new ManipuladorAtividadesAdministrativas());

        for (LeitorGenerico leitor: leitores) {
            List<String> atividades = leitor.BuscarAtividades();
            List<ModeloAtividade> modelosAtividades = manipuladores.get(leitores.indexOf(leitor)).ExtrairAtividades(atividades);

            modelos.addAll(modelosAtividades);
        }

        return modelos;
    }
}
