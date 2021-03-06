package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {

    protected Peliparametrit parametrit;
    protected final Scanner scanner;

    public KiviPaperiSakset(Peliparametrit parametrit, Scanner scanner) {
        this.parametrit = parametrit;
        this.scanner = scanner;
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        System.out.println("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = ensimmaisenSiirto();
        System.out.println("Toisen pelaajan siirto: ");
        String tokanSiirto = toisenSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println("");

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = ensimmaisenSiirto();
            if (!onkoOkSiirto(ekanSiirto)) {
                break;
            }

            System.out.println("Toisen pelaajan siirto: ");
            tokanSiirto = toisenSiirto();
            if (!onkoOkSiirto(tokanSiirto)) {
                break;
            }
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected String ensimmaisenSiirto() {
        return scanner.nextLine();
    }

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    abstract protected String toisenSiirto();
}
