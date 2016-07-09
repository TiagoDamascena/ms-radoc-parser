import ms.escritor.ConfeccionaDocumento;
import ms.leitor.LeitorAtividadesDeEnsino;
import ms.leitor.LeitorGenerico;
import ms.manipulador.ManipuladorGenerico;
import ms.manipulador.ModeloAtividade;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tiago on 06/07/2016.
 */
public class ExtrairAtividades {
    public static void main(String args[]) throws IOException {
        File input = new File("D:\\input.pdf");
        LeitorGenerico leitor = new LeitorAtividadesDeEnsino(input);

        List<String> atividadesDeEnsino = leitor.BuscarAtividades();
        List<ModeloAtividade> modelos = ManipuladorGenerico.buscarAtividadesDeEnsino(atividadesDeEnsino);

        ConfeccionaDocumento.popularDocumento(modelos);
    }
}
