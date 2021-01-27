package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Ledger {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
