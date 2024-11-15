/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ernes
 */
public class Comandos {

    File archivo = null;
    File folderactual = null;

    public File getArchivo() {
        return archivo;
    }

    public File getFolderactual() {
        return folderactual;
    }
    
    void setarchivo(String nombre) {
        archivo = new File(nombre);
    }

    void setfolderactual(String nombre) {
        folderactual = new File(nombre);
    }

    boolean crearArchivo() throws IOException {
        return archivo.createNewFile();
    }

    boolean crearFolder() {
        return archivo.mkdir();
    }

    String borrar(File miarchivo) {
        if (antidoto(miarchivo)) {

            return "  Eliminado: " + miarchivo.getName() + "\n\n";
        } else {
            return "  No se pudo borrar el archivo: " + miarchivo.getName() + "\n\n";
        }
    }

    boolean antidoto(File miarchivo) {
        if (miarchivo.isDirectory()) {
            for (File c : miarchivo.listFiles()) {
                antidoto(c);
            }
        } else {
            return miarchivo.delete();
        }
        return miarchivo.delete();
    }

    String dir() {
        if (archivo.isDirectory()) {
            System.out.println("Directorio de: " + archivo.getAbsolutePath());
            System.out.println("");
            int cfiles = 0, cdirs = 0, totalBytes = 0;
            for (File child : archivo.listFiles()) {
                if (!child.isHidden()) {
                    Date ultimo = new Date(child.lastModified());
                    System.out.print(ultimo + "\t");
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.println("<DIR>\t\t");
                    } else {
                        cfiles++;
                        totalBytes += child.length();
                        System.out.println("    \t" + child.length() + "\t");
                    }
                    System.out.println(child.getName());
                }
            }
            return cfiles + " archivos\t" + totalBytes + " bytes" + "\n" + cdirs + " dirs\t";
        }
        return "La direccion ingresada no es una carpeta";
    }

    public  String obtenerFechaActual() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        return formato.format(fechaActual);
    }

    public  String obtenerHoraActual12Horas() {
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a");
        Date horaActual = new Date();
        return formatoHora.format(horaActual);
    }

    void reescribirArchivo(String contenido) {
        try (FileWriter fW = new FileWriter(archivo)) {
            fW.write(contenido + "\n");
        } catch (IOException e) {
            System.out.println("No se ha ha podido sobreescribir.");
        }
    }

    String leerArchivoComoString() {
        if (archivo.exists() && archivo.isFile()) {
            StringBuilder contenido = new StringBuilder();
            try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
            } catch (IOException e) {
                return "Error al leer el archivo.";
            }
            return contenido.toString();
        }
        return "El archivo no existe o no es un archivo válido.";
    }

}
