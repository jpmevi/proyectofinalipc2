/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaArchivo;

import Modelo.ClienteModel;
import Modelo.CuentaModel;
import Objeto.Cliente;
import Objeto.Cuenta;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NodeList;






import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
public class ClienteArchivo {
     public void etiquetaClienteDB(NodeList listadoPaciente,String path) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        // Recorro las etiquetas

        Cliente cliente;
        List<Cuenta> cuentas = new ArrayList<>();
        

        for (int i = 0; i < listadoPaciente.getLength(); i++) {
            cliente = new Cliente();
            cuentas.clear();
            // Cojo el nodo actual
            Node nodo = listadoPaciente.item(i);
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
                    NodeList hijoCuentas = hijo.getChildNodes();
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                         if (hijo.getNodeName().equalsIgnoreCase("CUENTAS")) {
                            cuentas = etiquetaCuentasDelCliente(hijo);
                            //especialidades = tagEspecialidad(hijo);

                        } else {
                            crearCliente(cliente, hijo.getNodeName(), hijo.getTextContent(), path);

                        }
                    }

                }            
                
                ClienteModel nuevoCliente = new ClienteModel();
                nuevoCliente.agregarClienteCodigo(cliente);
                CuentaModel nuevaCuenta = new CuentaModel();
                for (int j = 0; j < cuentas.size(); j++) {
                    nuevaCuenta.agregarCuentaArchivo(cuentas.get(j),cliente.getCodigo());
                }
                System.out.println("");
            }

        }
    }
     
     
     
     public List<Cuenta> etiquetaCuentasDelCliente(Node cuentas) {
        // Recorro las etiquetas
        List<Cuenta> cuentasVariasCliente = new ArrayList<>();
        cuentasVariasCliente.clear();
        System.out.println("<========>CUENTAS VARIAS DEL CLIENTE<========>");
        // for (int i = 0; i < especialidad.getLength(); i++) {
        // Cojo el nodo actual
        Node nodo = cuentas;
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
                    if (hijo.getNodeName().equalsIgnoreCase("CUENTA")) {
                        System.out.println("AQUI INGRESE A LA ETIQUETA CUENTA SINGULAR Y ENVIE EL HIJO AL SIGUIENTE METODO");
                        //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                        cuentasVariasCliente = etiquetaCuentaCliente(hijos);
                   // especilidadesMedicas.add(hijo.getTextContent());
                    } else {
                        System.out.println("Error al Ingresar a la Etiqueta CUENTA SINGULAR");
                    }
                    
                }

            }
            System.out.println("");
        }

        return cuentasVariasCliente;
    }

        public List<Cuenta> etiquetaCuentaCliente(NodeList cuentaUnica) {
        // Recorro las etiquetas
        Cuenta cuenta;
        List<Cuenta> cuentasUnicasCliente = new ArrayList<>();
        cuentasUnicasCliente.clear();
        System.out.println("<========>CUENTA UNICA DEL CLIENTE<========>");
        for (int i = 0; i < cuentaUnica.getLength(); i++) {
            cuenta = new Cuenta();
            
        // Cojo el nodo actual
        Node nodo = cuentaUnica.item(i);
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
                    
                    //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                    //especilidadesMedicas.add(hijo.getTextContent());
                    try{
                            System.out.println("Etiqueta dentro de Cuenta: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            switch (hijo.getNodeName().toUpperCase()) {
                                case "CODIGO":
                                    cuenta.setCodigo(Long.parseLong(hijo.getTextContent()));
                                    break;
                                case "CREADA":
                                    cuenta.setFechaCreacion(Date.valueOf( hijo.getTextContent()));
                                    break;
                                case "CREDITO":
                                    cuenta.setMonto(Double.parseDouble(hijo.getTextContent()));
                                    break;
                                default:
                                    System.out.println("Mostrar error, etiqueta no conocida");
                                //throw new AssertionError();
                            }
                            
                            }catch(NumberFormatException ex){
                
                }
                            
                }//AQUI CIERRA EL NODE.ELEMENTNODE

            }
            System.out.println("AGREGO EL OBJETO CUENTA AL ARRAYLIST");
            cuentasUnicasCliente.add(cuenta);
        }

        
        }
        return cuentasUnicasCliente;
    }
     
     
     
     
     public void crearCliente(Cliente cliente, String tag, String atributo,String path) throws FileNotFoundException {
try{
        switch (tag.toUpperCase()) {
            case "CODIGO":
                cliente.setCodigo(Long.parseLong(atributo));
                break;

            case "NOMBRE":
                cliente.setNombre(atributo);
                break;

            case "DPI":
                cliente.setDpi(atributo);
                break;
                
            case "BIRTH":
                cliente.setFechaNacimiento(Date.valueOf( atributo));
                break;
                
            case "DIRECCION":
                cliente.setDireccion(atributo); 
                break;

            case "SEXO":
                cliente.setSexo(atributo);
                break;
                
             case "DPI-PDF":
                cliente.setPdfdpi(new FileInputStream(path+atributo) );
                break;
            case "PASSWORD":
                cliente.setPassword(atributo);
                break;

            default:
                
        }
        }catch(NumberFormatException e){
                
                }
    }
}
