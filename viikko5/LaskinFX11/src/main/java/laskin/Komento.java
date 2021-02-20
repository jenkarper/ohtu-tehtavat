package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    protected int edellinenTulos;

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();

    public void peru() {
        sovellus.setTulos(edellinenTulos);
        asetaTuloskentta();
        tarkistaNollatulos();
        undo.disableProperty().set(true);
    }

    protected void tarkistaNollatulos() {
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }

    protected void asetaTuloskentta() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
}
