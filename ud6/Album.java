package ud6;
import java.util.*;
class Cancion {
    String titulo;
    double duracion;
    public Cancion(String titulo, double duracion){
        this.titulo= titulo;
        this.duracion= duracion;
    }
    public String getTitulo() {
        return titulo;
    }
    @Override
    public String toString() {
        return "Cancion: \n" +
                "Titulo = " + titulo +
                " / Duracion = " + duracion + "\n";
    }
}

public class Album {
    String nombre;
    String artista;
    ArrayList <Cancion> canciones = new ArrayList<>();
    public Album (String nombre, String artista){
        this.nombre=nombre;
        this.artista=artista;
    }
    public Cancion createcancion (String titulo, double duracion){
        return new Cancion(titulo,duracion);
    }
    public int findSong(String titulo) {
        for (int i = 0; i < canciones.size(); i++) {
            if (canciones.get(i).getTitulo().equals(titulo)) {
                return i;
            }
        }
        return -1;
    }
    public boolean addNewSong (String titulo, double duracion){
        if (findSong(titulo) == -1) {
            canciones.add(createcancion(titulo,duracion));
            return true;
        } else {
            return false;
        }
    }
    public boolean addToplayList (int num, LinkedList<Cancion> play){
        ListIterator<Cancion> it = play.listIterator();
        if (num >= 0 && num < canciones.size()) {
            it.add(canciones.get(num));
            return true;
        }
        return false;
    }
    public boolean addToPlayList (String titulo, LinkedList<Cancion> play){
        ListIterator<Cancion> it = play.listIterator();
        if (findSong(titulo) >= 0 && findSong(titulo) < canciones.size()) {
            it.add(canciones.get(findSong(titulo)));
            return true;
        }
        return false;
    }
}

class main {
    static Scanner sc = new Scanner(System.in);
    static int op;
    static LinkedList<Album> Albums = new LinkedList<>();
    static LinkedList<Cancion> playlist = new LinkedList<>();
    public static void main (String[] args) {
        Album album1 = new Album("Perreoland", "Papi Gavi");
        Album album2 = new Album("Chaquetero", "Dani");
        album1.addNewSong("Un perreo duro", 2.37);
        album1.addNewSong("La pescadilla", 3.25);
        album1.addNewSong("El carnicero", 2.21);
        album2.addNewSong("Ser un chaquetero", 3.33);
        album2.addNewSong("La amistad no importa", 2.31);
        album2.addNewSong("Traicion", 3.51);
        Albums.add(album1);
        Albums.add(album2);
        album1.addToPlayList("La pescadilla", playlist);
        album2.addToplayList(2, playlist);
        album1.addToplayList(2, playlist);
        album2.addToPlayList("Ser un chaquetero", playlist);
        printList(playlist);
        play(playlist);

    }
    public static void play (LinkedList<Cancion> can3){
        ListIterator<Cancion> it = can3.listIterator();
        boolean cont = true;
        if (can3.isEmpty()){
            System.out.println("No tienes canciones.");
            return;
        }else {
            System.out.println("Escuchando " + it.next());
            MenuPrin();
        }
        boolean haciaAdelante = true;
        try {
            while (cont) {
                System.out.println("¿Que quiere hacer? ");
                op = sc.nextInt();
                switch (op) {
                    case 0:
                        System.out.println("Hasta la proxima.");
                        cont = false;
                        break;
                    case 1:
                        if (!haciaAdelante) {
                            if (it.hasNext())
                                it.next();
                            haciaAdelante = true;
                        }
                        if (it.hasNext()) {
                            System.out.println("Escuchando " + it.next());
                        } else {
                            System.out.println("Ya no hay más canciones");
                            it.previous();
                            haciaAdelante = false;
                        }
                        break;
                    case 2:
                        if (haciaAdelante) {
                            if (it.hasPrevious())
                                it.previous();
                            haciaAdelante = false;
                        }
                        if (it.hasPrevious()) {
                            System.out.println("Escuchando " + it.previous());
                        } else {
                            System.out.println("Primera canción");
                            it.next();
                            haciaAdelante = true;
                        }
                        break;
                    case 3:
                        if(!haciaAdelante) {
                            it.next();
                            System.out.println("Escuchando: " + it.previous());
                        }else {
                            it.previous();
                            System.out.println("Escuchando: " + it.next());
                        }
                        break;
                    case 4:
                        printList(playlist);
                        break;
                    case 5:
                        MenuPrin();
                        break;
                    case 6:
                        if(!haciaAdelante) {
                            it.next();
                            System.out.println(it.previous().getTitulo() + " esta cancion a sido eliminada.");
                            it.remove();
                            if(it.hasNext()){
                                System.out.println("Escuchando " + it.next());
                            }else {
                                System.out.println("Escuchando " + it.previous());
                            }
                            break;
                        }else {
                            it.previous();
                            System.out.println(it.next().getTitulo() + " esta cancion a sido eliminada.");
                            it.remove();
                            if(it.hasNext()){
                                System.out.println("Escuchando " + it.next());
                            }else {
                                System.out.println("Escuchando " + it.previous());
                            }
                            break;
                        }
                    default:
                        System.out.println("No puedes poner ese número.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("No puedes poner letras");
        } catch (NoSuchElementException e){
            System.out.println("No hay más canciones.");
        }
    }
    public static void MenuPrin () {
        System.out.println("0. Para salir de la lista de reproducción.");
        System.out.println("1. Reproducir siguiente canción en la lista.");
        System.out.println("2. Reproducir la canción previa de la lista.");
        System.out.println("3. Reproducir la canción actual.");
        System.out.println("4. Imprimir la lista de canciones en la playlist.");
        System.out.println("5. Volver a imprimir el menu.");
        System.out.println("6. Eliminar canción.");
    }
    public static void printList (LinkedList<Cancion> can2){
        Iterator<Cancion> it = can2.iterator();
        while (it.hasNext()){
            System.out.println("Nodo: " + it.next());
        }
        System.out.println("-----------------------------------------");
    }
}