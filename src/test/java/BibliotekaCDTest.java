import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BibliotekaCDTest {
    @Test
    public void shouldAddCD() {
        //given
        PlytaCD cd = new PlytaCD(500, 300
                , "Aaa", "Bbb", new ArrayList<>());
        BibliotekaCD biblioteka = new BibliotekaCD("Biblioteka", new ArrayList<>());
        //when
        biblioteka.addCD(cd);
        //than
        assertTrue(biblioteka.getListaCD().contains(cd));
    }

    @Test
    public void shouldAddOneCD() {
        //given
        PlytaCD cd1 = new PlytaCD(500, 300
                , "Aaa", "Bbb", new ArrayList<>());
        PlytaCD cd2 = new PlytaCD(500, 300
                , "Aaa", "Ccc", new ArrayList<>());
        BibliotekaCD biblioteka = new BibliotekaCD("Biblioteka", new ArrayList<>());
        //when
        biblioteka.addCD(cd1);
        biblioteka.addCD(cd2);
        //than
        assertEquals(1, biblioteka.getListaCD().size());
    }

    @Test
    public void shouldRemoveCD() {
        //given
        PlytaCD cd1 = new PlytaCD(500, 300
                , "Aaa", "Bbb", new ArrayList<>());
        BibliotekaCD biblioteka = new BibliotekaCD("Biblioteka", new ArrayList<>());
        biblioteka.addCD(cd1);
        assertEquals(1, biblioteka.getListaCD().size());
        //when
        biblioteka.removeCD("Aaa");
        //than
        assertTrue(biblioteka.getListaCD().isEmpty());
    }

    @Test
    public void shouldWriteToFile() throws IOException {
        //given
        PlytaCD cd1 = new PlytaCD(500, 300
                , "Aaa", "Bbb", new ArrayList<>());
        PlytaCD cd2 = new PlytaCD(500, 300
                , "ccc", "Bbb", new ArrayList<>());
        BibliotekaCD biblioteka = new BibliotekaCD("Biblioteka", new ArrayList<>());
        biblioteka.addCD(cd1);
        biblioteka.addCD(cd2);
        Utwor utwor1 = new Utwor("nazwa1", "autor1", 3.4);
        Utwor utwor2 = new Utwor("nazwa2", "autor2", 3.0);
        Utwor utwor3 = new Utwor("nazwa3", "autor3", 2.0);
        Utwor utwor4 = new Utwor("nazwa4", "autor4", 2.0);
        Utwor utwor5 = new Utwor("nazwa5", "autor5", 2.0);
        cd1.dodajUtwor(utwor1);
        cd1.dodajUtwor(utwor2);
        cd2.dodajUtwor(utwor3);
        cd2.dodajUtwor(utwor4);
        cd1.dodajUtwor(utwor5);
        //when
        BibliotekaCD.zapiszNaDysk(biblioteka, "zapisanaBiblioteka");
        //then
    }

    @Test
    public void testodczytuzPLiku() throws FileNotFoundException {
        //given
        String nazwapliku="zapisanaBiblioteka";
        //when
        BibliotekaCD result =BibliotekaCD.odczytZdysku(nazwapliku);

        //then
        Assert.assertEquals("Biblioteka",result.getName());
        Assert.assertEquals(2,result.getListaCD().size());
        Assert.assertEquals(2,result.getListaCD().get(0).getUtwory().size());
        Assert.assertEquals(1,result.getListaCD().get(1).getUtwory().size());

    }

}