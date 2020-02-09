import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Getter
@AllArgsConstructor
public class BibliotekaCD {
    private String name;
    private List<PlytaCD> listaCD = new ArrayList<>();

    public void addCD(PlytaCD cd) {
        long count = listaCD.stream()
                .filter(e -> e.getTytul().equals(cd.getTytul())).count();

        if (count == 0) {
            listaCD.add(cd);
        } else {
            System.out.println("Plyta jest juz na liscie");
        }
    }

    public void removeCD(String title) {
        Optional<PlytaCD> cd = listaCD.stream()
                .filter(e -> e.getTytul().equals(title))
                .findAny();
        if (cd.isPresent()) {
            listaCD.remove(cd.get());
        }
    }

    public static void zapiszNaDysk(BibliotekaCD bibliotekaCD, String nazwaPliku)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(nazwaPliku));
        String nazwabiblioteki = bibliotekaCD.getName();
        List<PlytaCD> listaCD = bibliotekaCD.getListaCD();
        writer.write(nazwabiblioteki);
        writer.write("\n");

        for (PlytaCD cd : listaCD) {
            writer.write(cd.getTytul());
            writer.write("; ");
            writer.write(cd.getAutor());
            writer.write("; ");
            writer.write(String.valueOf(cd.getWolneMiejsce()));
            writer.write("; ");
            writer.write(String.valueOf(cd.getZajeteMiejsce()));
            writer.write("# ");

            for (Utwor u : cd.getUtwory()) {
                writer.write(u.getNazwaUtworu());
                writer.write("; ");
                writer.write(u.getAutorUtworu());
                writer.write("; ");
                writer.write(String.valueOf(u.getDlugoscUtworu()));
                writer.write("@ ");

            }
            writer.write("\n");
        }
        writer.close();

    }
    public static  BibliotekaCD odczytZdysku(String zapisanaBiblioteka) throws FileNotFoundException {
        File file = new File("zapisanaBiblioteka");
        Scanner sc = new Scanner(file);
    boolean czyistnieje=false;
    BibliotekaCD bibliotekaCD=null;
        while (sc.hasNextLine()){
           String linia = sc.nextLine();

           if(!czyistnieje){
               bibliotekaCD = new BibliotekaCD(linia,new ArrayList<>());
               czyistnieje = true;
           }else{
               String [] poPodziale = linia.split("#");
               String plytaCdtest = poPodziale[0].trim();
               System.out.println(poPodziale[1]);
               String [] plytacdDane =plytaCdtest.split("; ");

               PlytaCD plytaCD = new PlytaCD(Double.valueOf(plytacdDane[3]),
                       Double.valueOf(plytacdDane[2]),
                       plytacdDane[1],plytacdDane[0],new ArrayList<>();
               bibliotekaCD.addCD(plytaCD);
           }
        }


return bibliotekaCD;
    }


}