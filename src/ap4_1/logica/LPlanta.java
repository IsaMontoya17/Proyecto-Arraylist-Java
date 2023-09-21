package ap4_1.logica;

import ap4_1.bean.Planta;
import ap4_1.bean.interfaces.ICrud;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class LPlanta implements ICrud<Planta> {

    @Override
    public void ingresar(Planta objeto) {

        String plantaInfo = objeto.getCodigo() + ", " + objeto.getNombre() + ", " + objeto.getCuidados() + ", " + objeto.getTipo() + ", " + objeto.getPrecio() + "\n";
        try {
            FileWriter writer = new FileWriter("./archivos/plantas.txt", true);
            writer.write(plantaInfo);
            writer.close();
            JOptionPane.showMessageDialog(null, "Planta agregada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la planta.");
        }
    }

    public void cargarDatosPlantas(ArrayList<Planta> arrayPlantas) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./archivos/plantas.txt"));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 5) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String cuidados = partes[2];
                    String tipo = partes[3];
                    String precioStr = partes[4].trim();
                    int precio = Integer.parseInt(precioStr);

                    Planta planta = new Planta();
                    planta.setCodigo(codigo);
                    planta.setNombre(nombre);
                    planta.setCuidados(cuidados);
                    planta.setTipo(tipo);
                    planta.setPrecio(precio);

                    arrayPlantas.add(planta);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de plantas.");
        }
    }
    
    public int cantidadPlantasdeSombra(ArrayList<?> arrayGeneral) {
        int cant = 0;

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                if (!lista.isEmpty() && lista.get(0) instanceof Planta) {
                    Iterator<?> iterador = lista.iterator();
                    while (iterador.hasNext()) {
                        Planta planta = (Planta) iterador.next();
                        if ("sombra".equals(planta.getTipo())) {
                            cant++;
                        }
                    }
                }
            }
        }

        return cant;
    }
    
    public void listarPlantasSolares(ArrayList<?> arrayGeneral) {
        StringBuilder info = new StringBuilder();

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Planta) {
                        Planta plantaActual = (Planta) obj;
                        if ("solar".equals(plantaActual.getTipo())) {
                            info.append(plantaActual.getNombre()).append(" ").append(plantaActual.getCuidados()).append("\n");
                        }
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, info.toString());
    }
    
    public int plantaBarata(ArrayList<?> arrayGeneral) {
        int precioBarato = Integer.MAX_VALUE;

        Iterator<?> iteradorExterno = arrayGeneral.iterator();
        while (iteradorExterno.hasNext()) {
            Object elemento = iteradorExterno.next();

            if (elemento instanceof ArrayList<?>) {
                ArrayList<?> lista = (ArrayList<?>) elemento;

                for (Object obj : lista) {
                    if (obj instanceof Planta) {
                        Planta planta = (Planta) obj;
                        int precioActual = planta.getPrecio();
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
