package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin on oltava vähtinään 0!");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon on oltava vähintään 0!");
        }
        alkiot = new int[kapasiteetti];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (haeIndeksi(luku) > -1) {
            return false;
        }
        alkiot[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == alkiot.length) {
            kasvata();
        }
        return true;
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (alkiot[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public int haeIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (alkiot[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int poistettavanIndeksi = haeIndeksi(luku);
        if (poistettavanIndeksi == -1) {
            return false;
        }
        alkiot[poistettavanIndeksi] = 0;
        if (poistettavanIndeksi < alkioidenLkm) {
            tiivista(poistettavanIndeksi);
        }
        alkioidenLkm--;
        return true;
    }

    private void kasvata() {
        int[] uusiTaulukko = new int[alkiot.length + kasvatuskoko];
        kopioi(alkiot, uusiTaulukko);
        alkiot = uusiTaulukko;
    }

    private void kopioi(int[] vanha, int[] uusi) {
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = vanha[i];
        }
    }

    private void tiivista(int indeksi) {
        for (int i = indeksi; i < alkioidenLkm - 1; i++) {
            int tyhjaPaikka = alkiot[i];
            alkiot[i] = alkiot[i + 1];
            alkiot[i + 1] = tyhjaPaikka;
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }
    
    @Override
    public String toString() {
        StringBuilder joukkoMerkkijonona = new StringBuilder();
        joukkoMerkkijonona.append("{");
        if (alkioidenLkm == 0) {
            return joukkoMerkkijonona.append("}").toString();
        }
        for (int i = 0; i < alkioidenLkm-1; i++) {
            joukkoMerkkijonona.append(alkiot[i]).append(", ");
        }
        joukkoMerkkijonona.append(alkiot[alkioidenLkm-1]).append("}");
        return joukkoMerkkijonona.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        kopioi(alkiot, taulu);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko ensimmainen, IntJoukko toinen) {
        IntJoukko yhdiste = new IntJoukko();
        int[] ensimmaisenAlkiot = ensimmainen.toIntArray();
        int[] toisenAlkiot = toinen.toIntArray();
        
        for (int i = 0; i < ensimmaisenAlkiot.length; i++) {
            yhdiste.lisaa(ensimmaisenAlkiot[i]);
        }
        for (int i = 0; i < toisenAlkiot.length; i++) {
            yhdiste.lisaa(toisenAlkiot[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko ensimmainen, IntJoukko toinen) {
        IntJoukko leikkaus = new IntJoukko();
        int[] ensimmaisenAlkiot = ensimmainen.toIntArray();
        
        for (int i = 0; i < ensimmaisenAlkiot.length; i++) {
            int alkio = ensimmaisenAlkiot[i];
            if (toinen.kuuluuJoukkoon(alkio)) {
                leikkaus.lisaa(alkio);
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko ensimmainen, IntJoukko toinen) {
        IntJoukko erotus = new IntJoukko();
        int[] ensimmaisenAlkiot = ensimmainen.toIntArray();
        int[] toisenAlkiot = toinen.toIntArray();
        
        for (int i = 0; i < ensimmaisenAlkiot.length; i++) {
            erotus.lisaa(ensimmaisenAlkiot[i]);
        }
        for (int i = 0; i < toisenAlkiot.length; i++) {
            erotus.poista(toisenAlkiot[i]);
        }
        return erotus;
    }
}