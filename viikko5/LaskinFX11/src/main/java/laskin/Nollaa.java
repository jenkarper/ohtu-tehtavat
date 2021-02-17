package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private int edellinenTulos;
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();
        
        sovellus.nollaa();
        asetaTuloskentta();
        tarkistaNollatulos();
    }

    @Override
    public void peru() {
        sovellus.setTulos(edellinenTulos);
        asetaTuloskentta();
        tarkistaNollatulos();
        undo.disableProperty().set(true);
    }
    
}
