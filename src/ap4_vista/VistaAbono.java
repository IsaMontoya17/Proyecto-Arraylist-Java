
package ap4_vista;

import ap4_1.bean.Abono;
import ap4_1.logica.LAbono;
import java.util.Random;
import javax.swing.JOptionPane;

public class VistaAbono extends LAbono {

    Random rand = new Random();
    Abono abono = new Abono();

    public Abono datosAbono() {
        int codigo = rand.nextInt(900) + 100;
        abono.setCodigo(codigo);
        String nombre = JOptionPane.showInputDialog("Ingrese nombre del abono: ");
        abono.setNombre(nombre);
        String descripcion = JOptionPane.showInputDialog("Ingrese descripción del abono: ");
        abono.setDescripcion(descripcion);
        String utilidad = JOptionPane.showInputDialog("Ingrese utilidad del abono:  ");
        abono.setUtilidad(utilidad);
        int precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese precio del abono: "));
        abono.setPrecio(precio);
        super.ingresar(abono);

        return abono;
    }
}
