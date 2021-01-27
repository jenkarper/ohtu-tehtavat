package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private Storage varasto;
    private Bank pankki;
    private Ostoskori ostoskori;
    private String kaupanTili;
    private ReferenceGenerator viitegeneraattori;

    @Autowired
    public Kauppa(Storage varasto, Bank pankki, ReferenceGenerator viitegeneraattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        kaupanTili = "33333-44455";
        this.viitegeneraattori = viitegeneraattori;
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
