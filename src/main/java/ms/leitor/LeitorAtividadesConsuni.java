package ms.leitor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Created by Bruni on 23/07/2016.
 */
public class LeitorAtividadesConsuni {

    private static LeitorAtividadesConsuni la = new LeitorAtividadesConsuni();
    /**
     *
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static int searchWord(String atividadeProcurada) throws JDOMException, IOException, URISyntaxException {
        ClassLoader classLoader = la.getClass().getClassLoader();
        URI uri = classLoader.getResource("Atividades.xml").toURI();
        File file = new File(uri);

        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(file);

        Element categories = doc.getRootElement();

        List elements = categories.getChildren();
        Iterator i = elements.iterator();

        List<Theme> themes = new ArrayList<>();

        while(i.hasNext()){

            Element category = (Element)i.next();
            Theme theme = new Theme(category.getAttributeValue("nome"));
            List<Element> children = category.getChildren();

            for (Element atividade : children){
                if(atividadeProcurada.equals(atividade)){
                    return 0;
                }
                else {
                    List<Element> tipo = atividade.getChildren();
                    if(!tipo.isEmpty()){
                        for(Element categoria : tipo){
                            if(atividadeProcurada.equals(tipo)){
                                return 0;
                            }
                        }
                    }
                    theme.addWord(word.getText());
                }
                themes.add(theme);


            }


            return themes;
        }
    }

    public void procuraFilhos(Element element, String atividadeProcurada){
        List<Element> childrens = element.getChildren();

        if(!childrens.isEmpty()){
            for (Element children : childrens){
                if(atividadeProcurada.equals(children.getAttributeValue("nome"))){
                    montaCodigo(children);
                    return;
                }
                else {
                    procuraFilhos(children, atividadeProcurada);
                }
            }
        }
    }

    public String montaCodigo(Element element){
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
}