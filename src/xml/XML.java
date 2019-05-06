
package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XML {

    public static void main(String[] args) {
        leerXML();
    }
    
    public static void leerXML(){
        
        try{
            File file = new File("xmlPrueba.xml");
            
            Document doc = (new SAXReader()).read(file);
            
            Iterator registros = doc.selectNodes("/lista/registro").iterator();
            
            while(registros.hasNext()){
                Element registro = (Element) registros.next();
                Iterator columns = registro.elementIterator();
                while(columns.hasNext()){
                    Element column = (Element) columns.next();
                    
                    System.out.print(column.getName() + ": " + column.getText() + ", ");
                }
                System.out.println("");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void escribirXML(){
        try{
            
        Document doc = DocumentHelper.createDocument();
        
        Element padre = doc.addElement("lista");
        Element hijo = padre.addElement("registro");
        Element registro = hijo.addAttribute("id", "1");
        registro.addElement("nombre").addText("luis guillermo");
        registro.addElement("apellido").addText("marquez cañas");
        registro.addElement("edad").addText("23");
        
        FileWriter xmlFisico = new FileWriter("xmlPrueba.xml");
        XMLWriter toXML = new XMLWriter(xmlFisico);
        
        toXML.write(doc);
        toXML.close();
        
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
}
