
package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KiviPaperiSakset  {

    public KPSParempiTekoaly(Peliparametrit parametrit, Scanner scanner) {
        super(parametrit, scanner);
    }

    @Override
    protected String toisenSiirto() {
        String siirto = super.parametrit.getTekoalyParannettu().annaSiirto();
        System.out.println(siirto);
        return siirto;
    }
}