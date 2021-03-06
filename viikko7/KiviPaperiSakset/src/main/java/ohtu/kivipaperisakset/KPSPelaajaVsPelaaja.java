package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    public KPSPelaajaVsPelaaja(Peliparametrit parametrit, Scanner scanner) {
        super(parametrit, scanner);
    }

    @Override
    protected String toisenSiirto() {
        return scanner.nextLine();
    }
}