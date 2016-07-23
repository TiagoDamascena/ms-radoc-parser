package ms.leitor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import ms.modelo.AtividadeConsuni;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Created by Bruni on 23/07/2016.
 */
public class LeitorAtividadesConsuni {

    /**
     *
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public AtividadeConsuni buscarAtividade(String atividadeProcurada) throws JDOMException, IOException, URISyntaxException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URI uri = classLoader.getResource("Atividades.xml").toURI();
        File file = new File(uri);

        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(file);

        Element categories = doc.getRootElement();

        List elements = categories.getChildren();
        Iterator i = elements.iterator();

        while(i.hasNext()){

            Element category = (Element)i.next();

            if(atividadeProcurada.equals(category.getAttributeValue("nome"))) {
               return montarModelo(category);
            } else {
                AtividadeConsuni atividadeConsuni = procuraFilhos(category, atividadeProcurada);
                if(atividadeConsuni != null) {
                    return atividadeConsuni;
                }
            }
        }
        return null;
    }

    private AtividadeConsuni procuraFilhos(Element element, String atividadeProcurada){
        List<Element> childrens = element.getChildren();

        if(!childrens.isEmpty()){
            for (Element children : childrens){
                if(atividadeProcurada.equals(children.getAttributeValue("nome"))){
                    return montarModelo(children);
                }
                else {
                    return procuraFilhos(children, atividadeProcurada);
                }
            }
        }
        return null;
    }

    private String montaCodigo(Element element){
        String cod = new String();
        cod = element.getAttributeValue("valor");

        while(true){
            element = element.getParentElement();
            if(!element.isRootElement()){
                cod = element.getAttributeValue("valor") + cod;
            }
            else{
                return cod;
            }
        }
    }

    private AtividadeConsuni montarModelo(Element element) {
        AtividadeConsuni atividade = new AtividadeConsuni();
        atividade.setCodigo(montaCodigo(element));
        Element pontos = element.getChild("pontos");
        if(pontos != null) {
            atividade.setPontos(pontos.getValue());
        }
        return atividade;
    }
}