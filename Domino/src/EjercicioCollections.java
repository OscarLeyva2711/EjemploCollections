import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EjercicioCollections {
    public static void main(String[] args) {
        leerYGuardarFrecuencia();
    }

    public static void leerYGuardarFrecuencia() {
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();
        String rutaEntrada = "/Users/donlevantin/Java/Sample Data v3/customers-10000.csv";
        String rutaSalida = "/Users/donlevantin/Java/Sample Data v3/FrecuenciaPalabras.txt";

        try (BufferedReader leer = new BufferedReader(new FileReader(rutaEntrada))) {
            String linea;
            int c = 0;

            while ((linea = leer.readLine()) != null && c < 50) {
                // Dividimos la línea en palabras (separadas por espacios o comas)
                String[] palabras = linea.split("[ ,]+");

                for (String palabra : palabras) {
                    boolean encontrada = false;
                    for (String key : frecuenciaPalabras.keySet()) {
                        if (key.equalsIgnoreCase(palabra)) {  // Compara ignorando mayúsculas/minúsculas
                            frecuenciaPalabras.put(key, frecuenciaPalabras.get(key) + 1);  // Incrementa la frecuencia
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        frecuenciaPalabras.put(palabra, 1);  // Agrega la palabra si no está en el mapa
                    }
                }
                c++;
            }

            // Guardar el resultado en un archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida))) {
                for (Map.Entry<String, Integer> entry : frecuenciaPalabras.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
            }

            System.out.println("Frecuencia de palabras guardada en: " + rutaSalida);
        } catch (IOException ex) {
            System.err.println("Error al leer o escribir el archivo: " + ex.getMessage());
        }
    }
}