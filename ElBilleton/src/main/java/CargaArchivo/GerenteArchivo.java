/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaArchivo;

import Modelo.GerenteModel;
import Objeto.Gerente;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author potz
 */
public class GerenteArchivo {
    
    
    

    
    /**
     * Recibe todos los atributos con la etiqueta Padre <GERENTE>, recorre las
     * etiquetas hijas, obteniendo sus atributos y envia dichos datos la base de
     * Datos DB
     *
     * @param listadoGerente
     */
    public void etiquetaGerenteDB(NodeList listadoGerente) throws SQLException, UnsupportedEncodingException {
        // Recorro las etiquetas
        System.out.println(" <========>Gerente");

        Gerente gerente;

        for (int i = 0; i < listadoGerente.getLength(); i++) {

            gerente = new Gerente();

            // Cojo el nodo actual
            Node nodo = listadoGerente.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearGerente(gerente, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envio a la Base de Datos
                 */

                GerenteModel nuevoGerente = new GerenteModel();
                // Creacion de la Entidad Gerente
                nuevoGerente.agregarGerenteCodigo(gerente);
                // Creacion del Historial de Creacion de la Entidad Gerente
                System.out.println("");
            }

        }
    }

    
    public void crearGerente(Gerente gerente, String tag, String atributo) {
try{
        switch (tag.toUpperCase()) {
            case "CODIGO":
                gerente.setCodigo(Long.parseLong(atributo));
                break;

            case "NOMBRE":
                gerente.setNombre(atributo);
                break;

            case "TURNO":
                if (atributo.equalsIgnoreCase("MATUTINO")) {
                    gerente.setTurno(atributo);
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    gerente.setTurno(atributo);
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("Lanzar error, no se reconoce token");
                }
                break;

            case "DPI":
                gerente.setDPI(atributo);
                break;

            case "DIRECCION":
                gerente.setDireccion(atributo);
                break;

            case "SEXO":
                gerente.setSexo(atributo);
                break;

            case "PASSWORD":
                gerente.setPassword(atributo);
                break;

            default:
        }
        }catch(NumberFormatException e){
                
                }
    }
}
