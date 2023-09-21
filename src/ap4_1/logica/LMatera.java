package ap4_1.logica;

import ap4_1.bean.Matera;
import ap4_1.bean.interfaces.ICrud;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class LMatera implements ICrud<Matera> {

    @Override
    public void ingresar(Matera objeto) {
        String materaInfo = objeto.getCodigo() + ", " + objeto.getNombre() + ", " + objeto.getMaterial() + ", " + objeto.getTamaño() + ", " + objeto.getPrecio() + "\n";
        try {
            FileWriter writer = new FileWriter("archivos/materas.txt", true); 
            writer.write(materaInfo);
            writer.close();
            JOptionPane.showMessageDialog(null, "Matera agregada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la planta.");
        }
    }

    public void cargarDatosMateras(ArrayList<Matera> arrayMateras) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("archivos/materas.txt"));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 5) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String material = partes[2];
                    String tamaño = partes[3];
                    String precioStr = partes[4].trim();
                    int precio = Integer.parseInt(precioStr);

                    Matera matera = new Matera();
                    matera.setCodigo(codigo);
                    matera.setNombre(nombre);
                    matera.setMaterial(material);
                    matera.setTamaño(tamaño);
                    matera.setPrecio(precio);

                    arrayMateras.add(matera);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de materas.");
        }
    }

    public int totalPrecio(ArrayList<?> arrayGeneral) {
        int precio = 0;

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Matera) {
                        Matera matera = (Matera) obj;
                        precio += matera.getPrecio();
                    }
                }
            }
        }

        return precio;
    }
    
    public int materaBarata(ArrayList<?> arrayGeneral) {
        int precioBarato = Integer.MAX_VALUE;

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Matera) {
                        Matera matera = (Matera) obj;
                        int precioActual = matera.getPrecio();
                        if (precioActual < precioBarato) {
                            precioBarato = precioActual;
                        }
                    }
                }
            }
        }

        if (precioBarato == Integer.MAX_VALUE) {
            return 0; 
        }

        return precioBarato;
    }

}
