package ap4_vista;
import ap4_1.bean.Abono;
import ap4_1.bean.Matera;
import ap4_1.bean.Planta;
import ap4_1.logica.LAbono;
import ap4_1.logica.LMatera;
import ap4_1.logica.LPlanta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Menu {

    public void Menu() {

        ArrayList<Planta> arrayPlantas = new ArrayList<>();
        ArrayList<Matera> arrayMateras = new ArrayList<>();
        ArrayList<Abono> arrayAbonos = new ArrayList<>();
        ArrayList<ArrayList<?>> arrayGeneral = new ArrayList<>();

        int opc = 0;
        int opc2 = 0;
        int cont = 0;
        VistaPlanta vplanta = new VistaPlanta();
        VistaAbono vabono = new VistaAbono();
        VistaMatera vmatera = new VistaMatera();
        LPlanta lplanta = new LPlanta();
        LAbono labono = new LAbono();
        LMatera lmatera = new LMatera();

        lmatera.cargarDatosMateras(arrayMateras);
        lplanta.cargarDatosPlantas(arrayPlantas);
        labono.cargarDatosAbonos(arrayAbonos);

        do {
            opc2 = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál array desea ingresar al arrayGeneral? \n"
                    + "1. Array de plantas \n"
                    + "2. Array de abonos \n"
                    + "3. Array de materas \n"));
            switch (opc2) {
                case 1:
                    if (!arrayGeneral.contains(arrayPlantas)) {
                        arrayGeneral.add(arrayPlantas);
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                        cont++;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "¡ERROR! ArrayList ya ha sido agregado");
                    }
                    break;
                case 2:
                    if (!arrayGeneral.contains(arrayAbonos)) {
                        arrayGeneral.add(arrayAbonos);
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                        cont++;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "¡ERROR! ArrayList ya ha sido agregado");
                    }
                    break;
                case 3:
                    if (!arrayGeneral.contains(arrayMateras)) {
                        arrayGeneral.add(arrayMateras);
                        JOptionPane.showMessageDialog(null, "¡Listo!");
                        cont++;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "¡ERROR! ArrayList ya ha sido agregado");
                    }
                    break;
                default:
                    break;
            }
        } while (cont < 3);

        do {

            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la opción a ejecutar \n"
                    + "1. Ingresar una planta \n"
                    + "2. Ingresar abono \n"
                    + "3. Ingresar matera \n"
                    + "4. Cargar cantidad de plantas de sombra \n"
                    + "5. Cargar total de pesos de todas las materas \n"
                    + "6. Cargar listado de nombre de las plantas solares y cuidados \n"
                    + "7. Listar nombre y utilidad de los abonos con precio mayor a $6.500 \n"
                    + "8. Plantas, materas y abonos más baratos \n"
                    + "9. Mostrar ArrayList general \n",
                    "Menú Principal", 1));

            switch (opc) {
                case 1:
                    Planta plantita = vplanta.datosPlanta();
                    arrayPlantas.add(plantita);
                    break;
                case 2:
                    Abono abonito = vabono.datosAbono();
                    arrayAbonos.add(abonito);
                    break;
                case 3:
                    Matera materita = vmatera.datosMatera();
                    arrayMateras.add(materita);
                    break;
                case 4:
                    int sombra;
                    sombra = lplanta.cantidadPlantasdeSombra(arrayGeneral);
                    JOptionPane.showMessageDialog(null, "La cantidad de plantas de sombra es: " + sombra);
                    break;
                case 5:
                    int precio = lmatera.totalPrecio(arrayGeneral);
                    JOptionPane.showMessageDialog(null, "El precio total es: " + precio);
                    break;
                case 6:
                    lplanta.listarPlantasSolares(arrayGeneral);
                    break;
                case 7:
                    labono.abonosCaros(arrayGeneral);
                    break;
                case 8:
                    int pabono = labono.abonoBarato(arrayGeneral);
                    int pplanta = lplanta.plantaBarata(arrayGeneral);
                    int pmatera = lmatera.materaBarata(arrayGeneral);
                    JOptionPane.showMessageDialog(null, "Productos con el precio más bajo: \n"
                            + "Matera: " + pmatera + "\n"
                            + "Planta: " + pplanta + "\n" + "Abono: " + pabono + "\n");
                    break;

                case 9:
                    mostrarContenidoArrayGeneral(arrayGeneral);
                    break;
            }

        } while (opc != 0);
    }

    public static void mostrarContenidoArrayGeneral(ArrayList<ArrayList<?>> arrayGeneral) {
        if (arrayGeneral.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Array vacío");
        } else {
            StringBuilder contenido = new StringBuilder();
            for (ArrayList<?> lista : arrayGeneral) {
                for (Object objeto : lista) {
                    if (objeto instanceof Planta) {
                        Planta planta = (Planta) objeto;
                        contenido.append("Planta: ").append(planta.getNombre()).append("\n");
                    } else if (objeto instanceof Matera) {
                        Matera matera = (Matera) objeto;
                        contenido.append("Matera: ").append(matera.getNombre()).append("\n");
                    } else if (objeto instanceof Abono) {
                        Abono abono = (Abono) objeto;
                        contenido.append("Abono: ").append(abono.getNombre()).append("\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, contenido.toString());
        }

    }
}