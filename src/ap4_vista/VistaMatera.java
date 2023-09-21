
package ap4_vista;

import ap4_1.bean.Matera;
import ap4_1.logica.LMatera;
import java.util.Random;
import javax.swing.JOptionPane;

public class VistaMatera extends LMatera {

    Random rand = new Random();
    Matera matera = new Matera();

    public Matera datosMatera() {
        int codigo = rand.nextInt(900) + 100;
        matera.setCodigo(codigo);
        String nombre = JOptionPane.showInputDialog("Ingrese nombre de la matera: ");
        matera.setNombre(nombre);
        String material = JOptionPane.showInputDialog("Ingrese material de la matera: ");
        matera.setMaterial(material);
        String tamaño = JOptionPane.showInputDialog("Ingrese tamaño de la matera ");
        matera.setTamaño(tamaño);
        int precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese precio de la matera"));
        matera.setPrecio(precio);
        super.ingresar(matera);

        return matera;
    }
}
