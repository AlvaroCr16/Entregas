package ud6;
import java.util.ArrayList;
import java.util.Scanner;
public class Contacto {
    String name;
    String phoneNumber;

    public Contacto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static String createContact(String getName, String getPhoneNumber) {
        return "Nombre: " + getName + " Telefono: " + getPhoneNumber;
    }

    class TelefonoMovil {
        String myNumber;
        private static ArrayList<Contacto> myContact = new ArrayList<Contacto>();

        public TelefonoMovil(String myNumber, ArrayList<Contacto> myContact) {
            this.myNumber = myNumber;
            this.myContact = myContact;
        }

        private static int findContact(Contacto con) {
            int ver = myContact.indexOf(con);
            if (ver >= 0) {
                return ver;
            }
            return -1;
        }

        public static int findContact(String stri) {
            for (int i = 0; i < myContact.size(); i++) {
                if (myContact.get(i).getName().equals(stri)) {
                    return i;
                }
            }
            return 0;
        }

        public static boolean addNewContact(Contacto cont) {
            if (findContact(cont) == -1) {
                myContact.add(cont);
                return true;
            } else {
                return false;
            }
        }

        public static boolean updateContact(Contacto contv, Contacto contn) {
            if (findContact(contv) == -1) {
                return false;
            } else {
                int index = myContact.indexOf(contv);
                myContact.set(index, contn);
                return true;
            }
        }

        public static boolean removeContact(Contacto contr) {
            if (findContact(contr) == -1) {
                return false;
            } else {
                int index = myContact.indexOf(contr);
                myContact.remove(contr);
                return true;
            }
        }

        public static Contacto queryContact(String contq) {
            if (findContact(contq) == 1) {
                return null;
            } else {
                return myContact.get(findContact(contq));
            }
        }

        public static void printContacts() {
            System.out.println("Lista de contactos: ");
            for (int i = 0; i < myContact.size(); i++) {
                System.out.println(myContact.get(i).getName() + " --> " + myContact.get(i).getPhoneNumber());
            }
        }
    }
    public static class Main {
        public static void imprimirMenu() {
            System.out.println("0. Para salir.");
            System.out.println("1. Para imprimir los contactos.");
            System.out.println("2. Para añadir un con tacto.");
            System.out.println("3. Para modificar un cotacto.");
            System.out.println("4. Para eliminar un contacto.");
            System.out.println("5. Para buscar un contacto.");
        }
        public static void actualiza() {
            Scanner sc5 = new Scanner(System.in);
            System.out.println("¿Qué contacto quieres actualizar?: ");
            String nombre = sc5.next();
            TelefonoMovil.queryContact(nombre);
            if (TelefonoMovil.queryContact(nombre) != null) {
                Scanner sc3 = new Scanner(System.in);
                System.out.println("¿Cúal es el nuevo nombre del contacto?: ");
                String nombre2 = sc3.next();
                Scanner sc4 = new Scanner(System.in);
                System.out.println("¿Cúal es el nuevo numero del contacto?: ");
                String numero2 = sc4.next();
                TelefonoMovil.updateContact(TelefonoMovil.queryContact(nombre), new Contacto(nombre2, numero2));
            } else {
                System.out.println("Ese contacto no existe");
            }
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean cont = true;
            int opc;
            while (cont) {
                imprimirMenu();
                System.out.println("Elige una opción: ");
                opc = sc.nextInt();
                switch (opc) {
                    case 0:
                        cont = false;
                        break;
                    case 1:
                        TelefonoMovil.printContacts();
                        break;
                    case 2:
                        System.out.println("Pon un nombre: ");
                        String nom = sc.next();
                        System.out.println("Pon un telefono: ");
                        String num = sc.next();
                        Contacto a = new Contacto(nom, num);
                        TelefonoMovil.addNewContact(a);
                        break;
                    case 3:
                        actualiza();
                        break;
                    case 4:
                        System.out.println("Escriba el contacto que quiera eliminar: ");
                        String eliminar = sc.next();
                        Contacto elim = TelefonoMovil.queryContact(eliminar);
                        TelefonoMovil.removeContact(elim);
                        break;
                    case 5:
                        System.out.println("Escribe el contacto que quieres ver: ");
                        String buscar = sc.next();
                        TelefonoMovil.queryContact(buscar);
                        break;
                    default:
                        System.out.println("Error, este número no esta en las opciones.");
                        break;
                }
            }
        }
    }
}