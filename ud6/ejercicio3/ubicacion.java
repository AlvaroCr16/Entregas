package ud6.ejercicio3;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ubicacion {
    int id;
    String descripcion;
    Map<String, Integer> exits = new HashMap<>();

    public ubicacion(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.exits=new HashMap<String, Integer>();
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void addExit(String direccion, int idUbicacion) {
        exits.put(direccion, idUbicacion);
    }
    public Map<String, Integer> getExits() {
        return exits;
    }
}

class Main {
    static Map<Integer, ubicacion> Ubicaciones = new HashMap<>();
    public Main() {

        ubicacion cero = new ubicacion(0, "Estás sentado en la clase de progarmación");
        ubicacion uno = new ubicacion(1, "Estás sen la cima de una montaña");
        ubicacion dos = new ubicacion(2, "Estás bañándote en la playa");
        ubicacion tres = new ubicacion(3, "Estás dentro de un edificio muy alto");
        ubicacion cuatro = new ubicacion(4, "Estás en un puente");
        ubicacion cinco = new ubicacion(5, "Estás en un bosque");

        Ubicaciones.put(0, cero);
        Ubicaciones.put(1, uno);
        Ubicaciones.put(2, dos);
        Ubicaciones.put(3, tres);
        Ubicaciones.put(4, cuatro);
        Ubicaciones.put(5, cinco);

        uno.addExit("N", 5);
        uno.addExit("S", 4);
        uno.addExit("E", 3);
        uno.addExit("O", 2);

        dos.addExit("N", 5);
        dos.addExit("S", 4);
        dos.addExit("E", 1);

        tres.addExit("O", 1);

        cuatro.addExit("N", 1);
        cuatro.addExit("O", 2);

        cinco.addExit("S", 1);
        cinco.addExit("O", 2);
    }

    public static void main(String[] args) {
        Main menu = new Main();
        Scanner tex = new Scanner(System.in);
        boolean bucle = true;
        String cord;
        int cal = 1;
        while (bucle) {
            System.out.println(Ubicaciones.get(cal).getDescripcion());
            for (String nom: Ubicaciones.get(cal).getExits().keySet()) {
                System.out.print("|" + nom + "| ");
            }
            System.out.println("|Q|");
            System.out.println("¿Donde quieres ir? ");
            try {
                cord = tex.next();

                if (cord.equals("Q") || cord.equals("q")) {
                    System.out.println("Gracias por ver el mapa.");
                    System.out.println("Hasta la proxima.");
                    break;
                }
                cal = Ubicaciones.get(cal).getExits().get(cord.toUpperCase());
            }catch (NullPointerException e){
                System.out.println("Introduce una dirección valida.");
            }
        }
    }
}
