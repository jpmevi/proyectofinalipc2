/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaArchivo;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author potz
 */
public class DividirEtiquetas {
    public void dividirEtiquetas(File file,String pathArchivo) throws SQLException {

        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(file);
            // Obtengo todas las etiquetas PADRE del documento
            NodeList listadoGerentes = documento.getElementsByTagName("GERENTE");
            NodeList listadoCajeros = documento.getElementsByTagName("CAJERO");
                   
            NodeList listadoClientes = documento.getElementsByTagName("CLIENTE");
            NodeList listadoTransacciones = documento.getElementsByTagName("TRANSACCION");


            GerenteArchivo gerente = new GerenteArchivo();
            gerente.etiquetaGerenteDB(listadoGerentes);
            CajeroArchivo cajero = new CajeroArchivo();
            cajero.etiquetaCajeroDB(listadoCajeros);
            ClienteArchivo cliente = new ClienteArchivo();
            cliente.etiquetaClienteDB(listadoClientes,pathArchivo);

            
           
            CargarTransaccion transacion = new CargarTransaccion();
            transacion.etiquetaTransaccionDB(listadoTransacciones);

            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
