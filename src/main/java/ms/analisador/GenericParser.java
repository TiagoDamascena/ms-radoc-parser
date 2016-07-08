package ms.analisador;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

/**
 * Created by Tiago on 07/07/2016.
 */
public class GenericParser {
    PDDocument document;

    public GenericParser(File input) {
        try {
            document = PDDocument.load(input);
        } catch (IOException ex) {
            System.out.println("GenericParser - Arquivo não encontrado.");
        }
    }

    public void GenerateTXT() throws IOException {
        BufferedWriter wr;
        PDFTextStripper stripper = new PDFTextStripper();
        File output = new File("D:\\output.txt");

        wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
        stripper.writeText(document, wr);
        wr.close();
    }

}
