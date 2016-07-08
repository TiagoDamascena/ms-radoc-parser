import ms.analisador.GenericParser;

import java.io.File;
import java.io.IOException;

/**
 * Created by Tiago on 06/07/2016.
 */
public class ExtrairAtividades {
    public static void main(String args[]) {
        File input = new File("D:\\input.pdf");

        GenericParser parser = new GenericParser(input);

        try {
            parser.GenerateTXT();
        } catch(IOException ex) {
            System.out.println("Io Exception");
        }

    }
}
