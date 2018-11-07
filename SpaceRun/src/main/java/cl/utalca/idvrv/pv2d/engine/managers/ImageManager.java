package cl.utalca.idvrv.pv2d.engine.managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * La clase ImageManager es la encargada de mantener en memoria todas las 
 * imagenes que se vayan cargando. Si una imagen no se volverá a usar deberá 
 * ser eliminada para no sobrecargar la memoria.
 * 
 * Nota: todos los archivos que esta clase cargue deberán estar contenidos dentro
 * de la carpeta 'resources/images'.
 * 
 * @author Pablo Rojas
 */
public class ImageManager 
{
    private static final String PATH = "resources" + File.separator + "images" + File.separator;
    
    private static HashMap<String, BufferedImage> resources;
    
    //https://es.wikipedia.org/wiki/Singleton
    private static HashMap<String, BufferedImage> getResources()
    {
        if(ImageManager.resources == null)
        {
            ImageManager.resources = new HashMap<>();
        }
        
        return ImageManager.resources;
    }
    
    /**
     * Retorna true si puede leer la imagen almacenada en la ruta proporcionada,
     * false de lo contrario.
     * 
     * Nota: todos los archivos que esta clase carga deberán estar contenidos dentro 
     * de la carpeta 'resources/images'.
     * 
     * @param filename
     * @return 
     */
    public static boolean register(String filename)
    {
        try 
        {
            String pathname = ImageManager.PATH + filename;
            File file = new File(pathname);
            BufferedImage image = ImageIO.read(file);
            ImageManager.getResources().put(filename, image);
        } 
        catch (IOException ex) 
        {
            return false;
        }
        return true;
    }
    
    /**
     * Retorna true si puede leer la imagen almacenada en la ruta proporcionada 
     * y el calculo del hashCode del archivo coincide con el valor de hashCode
     * proporcionado, false de lo contrario.
     * 
     * Nota: todos los archivos que esta clase carga deberán estar contenidos dentro 
     * de la carpeta 'resources/images'.
     * 
     * @param filename
     * @param hashCode
     * @return 
     */
    public static boolean register(String filename, int hashCode)
    {
        try 
        {
            String pathname = ImageManager.PATH + filename;
            File file = new File(pathname);
            //System.out.println(file.hashCode());
            if(file.hashCode() != hashCode)
            {
                return false;
            }
            BufferedImage image = ImageIO.read(file);
            ImageManager.getResources().put(filename, image);
        } 
        catch (IOException ex) 
        {
            return false;
        }
        return true;
    }
    
    public static BufferedImage get(String filename) throws ResourceNotFoundException
    {
        BufferedImage image = ImageManager.getResources().get(filename);
        if(image == null)
        {
            throw new ResourceNotFoundException(filename);
        }
        return image;
    }
    
    public static void release(String filename)
    {
        ImageManager.getResources().remove(filename);
    }
            
    
    
    
}
