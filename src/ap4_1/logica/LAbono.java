package ap4_1.logica;

import ap4_1.bean.Abono;
import ap4_1.bean.Planta;
import ap4_1.bean.interfaces.ICrud;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class LAbono implements ICrud<Abono> {

    @Override
    public void ingresar(Abono objeto) {
        String abonoInfo = objeto.getCodigo() + ", " + objeto.getNombre() + ", " + objeto.getDescripcion() + ", " + objeto.getUtilidad() + ", " + objeto.getPrecio() + "\n";
        try {
            FileWriter writer = new FileWriter("./archivos/abono.txt", true);
            writer.write(abonoInfo); 
            writer.close();
            JOptionPane.showMessageDialog(null, "Abono agregada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la planta.");
        }
    }

    public void cargarDatosAbonos(ArrayList<Abono> arrayAbonos) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./archivos/abono.txt"));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 5) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String descripcion = partes[2];
                    String utilidad = partes[3];
                    String precioStr = partes[4].trim();
                    int precio = Integer.parseInt(precioStr);

                    Abono abono = new Abono();
                    abono.setCodigo(codigo);
                    abono.setNombre(nombre);
                    abono.setDescripcion(descripcion);
                    abono.setUtilidad(utilidad);
                    abono.setPrecio(precio);

                    arrayAbonos.add(abono);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de abonos.");
        }
    }
    
    public void abonosCaros(ArrayList<?> arrayGeneral) {
        StringBuilder info = new StringBuilder();

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Abono) {
                        Abono abono = (Abono) obj;
                        if (abono.getPrecio() > 6500) {
                            info.append(abono.getNombre()).append(" ").append(abono.getUtilidad()).append("\n");
                        }
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, info.toString());
    }

    public int abonoBarato(ArrayList<?> arrayGeneral) {
        int precioBarato = Integer.MAX_VALUE;

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Abono) {
                        Abono abono = (Abono) obj;
                        int precioActual = abono.getPrecio();
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
