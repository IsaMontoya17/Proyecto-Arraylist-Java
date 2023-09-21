package ap4_vista;

import ap4_1.bean.Planta;
import ap4_1.logica.LPlanta;
import java.util.Random;
import javax.swing.JOptionPane;

public class VistaPlanta extends LPlanta {

    Planta planta = new Planta();

    Random rand = new Random();

    public Planta datosPlanta() {
        int codigo = rand.nextInt(900) + 100;
        planta.setCodigo(codigo);
        String nombre = JOptionPane.showInputDialog("Ingrese nombre de la planta: ");
        planta.setNombre(nombre);
        String cuidados = JOptionPane.showInputDialog("Ingrese cuidados de la planta: ");
        planta.setCuidados(cuidados);
        String tipo = JOptionPane.showInputDialog("Ingrese tipo de la planta, (solar/sombra): ");
        while (!tipo.equals("solar") && !tipo.equals("sombra")) {
            tipo = JOptionPane.showInputDialog("ERROR: Ingrese tipo de la planta, (solar/sombra): ");
        }
        String tipo2 = tipo.toLowerCase();
        planta.setTipo(tipo2);
        int precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese precio de la planta"));
        planta.setPrecio(precio);
        super.ingresar(planta);
        
        return planta;
    }
}
