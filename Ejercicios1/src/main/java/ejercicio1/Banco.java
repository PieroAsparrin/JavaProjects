package ejercicio1;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Alumno
 */

/*IMPORTANTE!!
HOLA TITUX SOY PIERO, QUERIA COMENTARTE MEDIANTE ESTE TEXTO QUE LA ENTRADA DEL SCANNER PRESENTA ERRORES A LA HORA DE TRATAR DE LIMPIAR SU BUFFER
ASI QUE CREE EL TEXTO "CONTINUAR" QUE AVECES CONTINUA CON LA EJECUCION DEL PROGRAMA CON NORMALIDAD Y EN OTRAS OCASIONES AL SALIR DE UN BUCLE
ANIDADO ES NECESARIO PRECIONAR "ENTER" PARA SEGUIR CON LA EJECUCION, TAMBIEN AÑADI UN MARCADOR PARA QUE TENGAS UNA GUIA SOBRE DONDE SE ENCUENTRA
EL PROGRAMA EN ESE MOMENTO PORQUE SE QUE LAS CONSOLAS DE JAVA PUEDEN SER LIOSAS YA QUE NO CUENTAN CON UNA FUNCION DE BORRADO COMO EN C# U OTROS 
LENGUAJES DE PROGRAMACION(SOLO APLICA EN NEEDBEANS EN CODE SI BORRA TODO). MUCHAS GRACIAS Y SUERTE CON LA CORRECCION DE TRABAJOS*/
public class Banco {

    static Scanner entrada = new Scanner(System.in);
    static Cuenta Cuentausuario = new Cuenta("Usuario", 0);

    public static void main(String[] args) {
        boolean salir = false;

        registrarse(Cuentausuario.getTitular());

        while (!salir) {
            limpiarConsola();
            System.out.println("CONTINUAR..");
            entrada.nextLine();
            System.out.println("USUARIO: " + Cuentausuario.getTitular() + "\t" + "FONDOS: " + Cuentausuario.getCantidad() + "€");
            System.out.println("Elija una acción a realizar:\n1.Ingresar dinero.\n2.Retirar dinero.\n3.Salir");
            String eleccion = entrada.nextLine();
            switch (eleccion) {
                case "1":
                    limpiarConsola();
                    ingresar(Cuentausuario.getCantidad());
                    break;

                case "2":
                    limpiarConsola();
                    retirar(Cuentausuario.getCantidad());
                    break;

                case "3":
                    System.out.println("Cerrando sesión.");
                    salir = true;
                    break;

                default:
                    limpiarConsola();
                    System.out.println("Elije una opción válida..");
                    break;
            }
        }
    }

    /**
     * Pide un nombre al usuario para hacer referencia a él.(String)
     * @param titular 
     */
    public static void registrarse(String titular) {
        String nombre = "";
        System.out.println("Escribe tu nombre:");
        nombre = entrada.nextLine();
        Cuentausuario.setTitular(nombre);
    }

    public static void ingresar(double fondos) {
        double ingreso = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println("Escribe la cantidad a ingresar");
            try {
                ingreso = entrada.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Escribe un valor válido.");
                entrada.next();
            }
        }

        if (ingreso <= 0) {
            System.out.println("No puedes ingresar numéros negativos o con un valor de 0€..");
            fondos = fondos;
        } else {
            fondos = fondos + ingreso;
            Cuentausuario.setCantidad(fondos);
        }
    }

    public static void retirar(double fondos) {
        double retiro = 0;
        boolean valido = false;
        if (fondos == 0) {
            System.out.println("No tienes dinero en la cuenta que puedas retirar!.");
        } else {
            while (!valido) {
                System.out.println("Escribe la cantidad a retirar:");
                try {
                    retiro = entrada.nextDouble();
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Escribe un valor válido");
                    entrada.next();
                }
            }
            if (retiro <= 0) {
                System.out.println("La cantidad a retirar no puede ser un numero negativo..");
            } else {
                if (fondos > retiro || fondos == retiro) {
                    fondos = fondos - retiro;
                    Cuentausuario.setCantidad(fondos);
                } else {
                    System.out.println("La cantidad a retirar es mayor al dinero almacenado en la cuenta, ingresa una cantidad menor o igual a tus fondos e intentalo nuevamente..");
                    fondos = fondos;
                    Cuentausuario.setCantidad(fondos);
                }
            }
        }
    }

    public static void limpiarConsola() {
        //ESTO ES UN MARCADOR EN JAVA Y BORRA LA CONSOLA EN VISUAL STUDIO CODE.
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            /*No hacer nada*/
        }
        /*Marcador*/
    }
}
