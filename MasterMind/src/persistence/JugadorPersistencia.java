package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import utils.Pair;

/**
 *
 * @author Pérez Ortiz, Alejandro
 */
public class JugadorPersistencia {

    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     * @return si se ha registrado con éxito
     */
    public Pair<Boolean, String> register(String n, String c) {
        File dir = new File("data/players/"+n);
        boolean b = dir.mkdirs();
        Pair<Boolean, String> p = new Pair();
        if(b) {      
            p.setRight("Te has registrado correctamente");
            File dir2 = new File("data/players/"+n+"/games");
            dir2.mkdir();
            File info = new File("data/players/"+n+"/info.txt");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(info));
                bw.write(n+" "+c);
                bw.close();
            }
            catch(IOException e) {
                p.setRight("Error en el registro");
                p.setLeft(false);
                return p;
            }
            p.setLeft(true);
        }
        else {
            p.setRight("El jugador ya existe");
            p.setLeft(false);
        }
        return p;
    }
    
    /**
     *
     * @param n nombre de usuario
     * @param c contraseña
     * @return si ha hecho login con éxito
     */
    public Pair<Boolean, String> login(String n, String c) {
        Pair<Boolean, String> p = new Pair();
        try{
            String linea;
            FileReader f = new FileReader("data/players/"+n+"/info.txt");
            BufferedReader b = new BufferedReader(f);
            linea = b.readLine();
            b.close();
            String palabra[] = linea.split(" ");
            if(!palabra[1].equals(c)) {
                p.setRight("La contraseña introducida es incorrecta");
                p.setLeft(false);
                return p;
            }
            p.setRight("Has iniciado sesión correctamente");
            p.setLeft(true);
        }
        catch(IOException ex) {
            p.setRight("El usuario introducido es incorrecto");
            p.setLeft(false);
            return p;
        }
        return p;
     }
    
    /**
     *
     * @param n1 nombre actual del usuario
     * @param n2 nombre que se quiere poner el usuario
     * @param c contraseña del usuario
     * @return si se ha cambiado el nombre correctamente
     */
    public boolean setName(String n1,String n2, String c) {
        File dir = new File("data/players/"+n1);
        File dir2 = new File("data/players/"+n2);
        boolean success = dir.renameTo(dir2);
        if(!success) {
            System.out.println("El nombre de usuario ya está en uso.");
            return false;
        }
        File info = new File("data/players/"+n2+"/info.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(info));
            bw.write(n2+" "+c);
            bw.close();
            System.out.println("Has cambiado tu nombre de usuario correctamente.");
            return true;
        }
        catch (IOException e) {
            System.out.println("Error al cambiar de nombre");
            return false;
        }
    }
    
    /**
     *
     * @param n el nombre del usuario
     * @param c la contraseña
     * @return si se ha cambiado la contraseña correctamente
     */
    public boolean setPassword(String n, String c) {
        File info = new File("data/players/"+n+"/info.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(info));
            bw.write(n+" "+c);
            bw.close();
            System.out.println("Has cambiado tu contraseña correctamente.");
            return true;
        }
        catch (IOException e) {
            System.out.println("Error al cambiar de contraseña");
            return false;
        }
    }
    
    private void borrarDirectorio(File f) {
        File[] ficheros = f.listFiles();
        for(int i = 0; i < ficheros.length; i++) {
            if(ficheros[i].isDirectory())
                borrarDirectorio(ficheros[i]);
            ficheros[i].delete();
        }
    }
    
    /**
     *
     * @param n nombre del usuario
     */
    public void elimina(String n) {
        File f = new File("data/players/"+n);
        borrarDirectorio(f);
        if(f.delete()) 
            System.out.println("El usuario se ha eliminado correctamente");
        else
            System.out.println("No se ha podido eliminar el usuario");
    }
}