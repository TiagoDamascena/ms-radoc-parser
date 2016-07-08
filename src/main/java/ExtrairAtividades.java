import ms.analisador.GenericParser;
import ms.escritor.ConfeccionaDocumento;
import ms.manipulador.ModeloAtividade;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tiago on 06/07/2016.
 */
public class ExtrairAtividades {
    public static void main(String args[]) throws IOException {
        List<ModeloAtividade> modeloAtividadeList = new ArrayList<ModeloAtividade>();

        modeloAtividadeList.add(new ModeloAtividade("Teste 01", new DateTime(2016,01,01,0,0),new DateTime(2016,02,01,0,0)));//DateTime(Ano,Mes,Dia,Hora,Minuto)
        modeloAtividadeList.add(new ModeloAtividade("Teste 02",64, new DateTime(2016,01,01,0,0),new DateTime(2016,06,01,0,0)));
        modeloAtividadeList.add(new ModeloAtividade("Teste 03",128, new DateTime(2015,01,01,0,0),new DateTime(2016,06,01,0,0)));
        modeloAtividadeList.add(new ModeloAtividade("Teste 04", new DateTime(2015,01,01,0,0),new DateTime(2015,06,01,0,0)));

        ConfeccionaDocumento.popularDocumento(modeloAtividadeList);
    }
}
