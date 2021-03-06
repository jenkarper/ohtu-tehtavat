//
// Written as group assignment by Mary Ehrsted, Jenny Perttola, Salla Salokanto 6.3.2021
//
package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {

            tulostaPelivaihtoehdot();

            String kayttajanValinta = scanner.nextLine();

            if (kayttajanValinta.endsWith("a")) {
                kaksinpeli(scanner);
            } else if (kayttajanValinta.endsWith("b")) {
                tekoalypeli(scanner);
            } else if (kayttajanValinta.endsWith("c")) {
                pahaTekoalypeli(scanner);
            } else {
                System.out.println("Ohjelma sulkeutuu.");
                break;
            }
        }
    }

    public static void tulostaPelivaihtoehdot() {
        System.out.println("\nValitse pelataanko\n"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan\n"
                + "\nMuilla valinnoilla lopetetaan.");
    }

    public static void kaksinpeli(Scanner scanner) {
        System.out.println("Peli loppuu, kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s.\n");
        KiviPaperiSakset kaksinpeli = new KPSPelaajaVsPelaaja(Peliparametrit.luoKaksinpeli(), scanner);
        kaksinpeli.pelaa();
    }

    public static void tekoalypeli(Scanner scanner) {
        System.out.println("Peli loppuu, kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s.");
        KiviPaperiSakset tekoalypeli = new KPSTekoaly(Peliparametrit.luoTekoalypeli(new Tekoaly()), scanner);
        tekoalypeli.pelaa();
    }

    public static void pahaTekoalypeli(Scanner scanner) {
        System.out.println("Peli loppuu, kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s.");
        KiviPaperiSakset pahaYksinpeli = new KPSParempiTekoaly(Peliparametrit.luoPahaTekoalypeli(new TekoalyParannettu(20)), scanner);
        pahaYksinpeli.pelaa();
    }
}
