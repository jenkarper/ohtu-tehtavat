package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    public KPSTekoaly(Peliparametrit parametrit, Scanner scanner) {
        super(parametrit, scanner);
    }

    @Override
    protected String toisenSiirto() {
        String siirto = super.parametrit.getTekoaly().annaSiirto();
        System.out.println(siirto);
        return siirto;
    }
}