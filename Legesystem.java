import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {

    // HOVEDLISTER
    static IndeksertListe<Pasient> pasientliste = new IndeksertListe<>();
    static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    static Prioritetskoe<Lege> legeliste = new Prioritetskoe<>();
    static IndeksertListe<Resept> reseptliste = new IndeksertListe<>();

    // LESER INN FIL OG DATA FRA FILA
    public void lesInnTekstfil(String filnavn) {
        File fil = new File(filnavn);

        Scanner sc = null;
        try {
            sc = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen: " + fil.getName());
            System.exit(1);
        }

        while (sc.hasNextLine()) {
            if (sc.hasNext("#")) {
                sc.next();

                // finner pasienter
                if (sc.hasNext("Pasienter")) {
                    sc.nextLine();
                    String[] linje1;
                    while (!sc.hasNext("#")) {
                        linje1 = sc.nextLine().split(",");
                        pasientliste.leggTil(new Pasient(linje1[0], linje1[1]));
                    }

                }
            }

            // neste bolk
            if (sc.hasNext("#")) {
                sc.next();

                // finner legemidler
                if (sc.hasNext("Legemidler")) {
                    sc.nextLine();
                    String[] linje;
                    while (!sc.hasNext("#")) {
                        linje = sc.nextLine().split(",");
                        if (linje[1].strip().equals("narkotisk")) {
                            legemidler.leggTil(
                                    (Legemiddel) new Narkotisk(linje[0].strip(), Integer.parseInt(linje[2].strip()),
                                            Double.parseDouble(linje[3].strip()), Integer.parseInt(linje[4].strip())));
                        }
                        if (linje[1].strip().equals("vanedannende")) {
                            legemidler.leggTil(
                                    (Legemiddel) new Vanedannende(linje[0].strip(), Integer.parseInt(linje[2].strip()),
                                            Double.parseDouble(linje[3].strip()), Integer.parseInt(linje[4].strip())));

                        }
                        if (linje[1].strip().equals("vanlig")) {
                            legemidler.leggTil(
                                    (Legemiddel) new Vanlig(linje[0].strip(), Integer.parseInt(linje[2].strip()),
                                            Double.parseDouble(linje[3].strip())));

                        }

                    }

                }

            }

            // neste bolk
            if (sc.hasNext("#")) {
                sc.next();

                // finner leger
                if (sc.hasNext("Leger")) {
                    sc.nextLine();
                    String[] linje;
                    while (!sc.hasNext("#")) {
                        linje = sc.nextLine().split(",");
                        if (linje[1].strip().equals("0")) {
                            legeliste.leggTil(new Lege(linje[0].strip()));
                        } else if (!linje[1].strip().equals("0")) {
                            legeliste.leggTil(new Spesialist(linje[0].strip(), linje[1].strip()));
                        }
                    }
                }
            }

            // neste bolk
            if (sc.hasNext("#")) {
                sc.next();

                // finner resepter
                if (sc.hasNext("Resepter")) {
                    sc.nextLine(); // går til ny linje
                    String[] linje;
                    while (sc.hasNextLine()) {
                        linje = sc.nextLine().split(",");
                        if (linje[3].equals("hvit")) {// lager hvit resept
                            Lege lege = null;
                            for (Lege i : legeliste) {
                                if (i.hentNavn().equals(linje[1])) {
                                    lege = i;
                                    break;
                                }
                            }
                            Legemiddel l = legemidler.hent(Integer.parseInt(linje[0]));
                            Pasient p = pasientliste.hent(Integer.parseInt(linje[2]));
                            int reit = Integer.parseInt(linje[4]);
                            Resept r = null;
                            try {
                                r = lege.skrivHvitResept(l, p, reit);
                                reseptliste.leggTil(r);
                            } catch (UlovligUtskrift uu) {
                                System.out.println(uu);
                            }
                        }
                        if (linje[3].equals("blaa")) {// lager blaa resept
                            Lege lege = null;
                            for (Lege i : legeliste) {
                                if (i.hentNavn().equals(linje[1])) {
                                    lege = i;
                                    break;
                                }
                            }
                            Legemiddel l = legemidler.hent(Integer.parseInt(linje[0]));
                            Pasient p = pasientliste.hent(Integer.parseInt(linje[2]));
                            int reit = Integer.parseInt(linje[4]);
                            Resept r = null;
                            try {
                                r = lege.skrivBlaaResept(l, p, reit);
                                reseptliste.leggTil(r);
                            } catch (UlovligUtskrift uu) {
                                System.out.println(uu);
                            }
                        }
                        if (linje[3].equals("p")) {// lager presept
                            Lege lege = null;
                            for (Lege i : legeliste) {
                                if (i.hentNavn().equals(linje[1])) {
                                    lege = i;
                                    break;
                                }
                            }
                            Legemiddel l = legemidler.hent(Integer.parseInt(linje[0]));
                            Pasient p = pasientliste.hent(Integer.parseInt(linje[2]));
                            int reit = Integer.parseInt(linje[4]);
                            Resept r = null;
                            try {
                                r = lege.skrivPResept(l, p, reit);
                                reseptliste.leggTil(r);
                            } catch (UlovligUtskrift uu) {
                                System.out.println(uu);
                            }
                        }

                        if (linje[3].equals("militaer")) {// lager militaerresept
                            Lege lege = null;
                            for (Lege i : legeliste) {
                                if (i.hentNavn().equals(linje[1])) {
                                    lege = i;
                                    break;
                                }
                            }

                            Legemiddel l = legemidler.hent(Integer.parseInt(linje[0]));
                            Pasient p = pasientliste.hent(Integer.parseInt(linje[2]));
                            Resept r = null;
                            try {
                                r = lege.skrivMilResept(l, p);
                                reseptliste.leggTil(r);
                            } catch (UlovligUtskrift uu) {
                                System.out.println(uu);
                            }
                        }
                    }
                }
            }
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

        }
        sc.close();
    }

    // kommandoloekke hovedprogram
    public void loekke() {

        String svar = "";

        Scanner input = new Scanner(System.in);

        while (!svar.equals("6")) {
            System.out.println("\n--MENY--");
            System.out.println("Hva oensker du aa gjoere?");
            System.out.println(
                    "1: Se en fullstendig oversikt over pasienter, leger, legemidler og resepter");
            System.out.println("2: Opprette og legge til nye elementer i systemet");
            System.out.println("3: Bruke en gitt resept til lista til en pasient");
            System.out.println("4: Skrive ut ulike statistikker");
            System.out.println("5: Skrive all data til fil (NB IKKE implementert)");
            System.out.println("6: Avslutt");
            System.out.print("Skriv inn her: ");
            svar = input.next().strip();
            if (svar.equals("1")) {
                skrivUtOversikt();
            } else if (svar.equals("2")) {
                opprettNyeElementer();
            } else if (svar.equals("3")) {
                brukResept();
            } else if (svar.equals("4")) {
                skrivUtStats();
            }
            // else if(svar.equals("5")){
            // skrivTilFil();
            // }
            else if (svar.equals("6")) {
                System.out.println("Forlater legesystemet...");
            }

        }
        System.out.println("Takk for naa :))");
        input.close();

    }

    // OPPG E3 -- SKRIV UT FULLSTENDIG OVERSIKT OVER ALT
    public void skrivUtOversikt() {
        System.out.println("\n--Pasienter");
        skrivUtPasienter();
        System.out.println("\n--Leger");
        skrivUtLeger();
        System.out.println("\n--Legemidler");
        skrivUtLegemidler();
        System.out.println("\n--Resepter");
        skrivUtResepter();
    }

    // OPPG E3 STOTTEMETODER
    public void skrivUtPasienter() {
        for (Pasient i : pasientliste) {
            System.out.println(i.hentId() + ": " + i);
        }
    }

    public void skrivUtLeger() {
        for (Lege i : legeliste) {
            System.out.println(i.hentNavn());
        }
    }

    public void skrivUtLegerUnummerert() {
        for (Lege i : legeliste) {
            System.out.println(i);
        }
    }

    public void skrivUtSpesialister() {
        int teller = 0;
        for (Lege i : legeliste) {
            if (i instanceof Spesialist) {
                System.out.println(teller + ": " + i);
                teller++;
            }
        }
    }

    public void skrivUtSpesialisterUnummerert() {
        for (Lege i : legeliste) {
            if (i instanceof Spesialist) {
                System.out.println(i);
            }
        }
    }

    public void skrivUtLegemidler() {
        int teller = 0;
        for (Legemiddel i : legemidler) {
            System.out.println(teller + ": " + i.hentNavn());
            teller++;
        }
    }

    public void skrivUtResepter() {
        for (Resept i : reseptliste) {
            System.out.println(i);
        }
    }

    // OPPG E4 -- OPPRETTE OG LEGGE TIL NYE ELEMENTER I SYSTEMET
    public void opprettNyeElementer() {
        String[] alternativer = { "Legg til ny lege", "Legg til ny pasient", "Legg til ny resept",
                "Legg til nytt legemiddel" };

        String svar = "";
        Scanner input = new Scanner(System.in);
        while (!svar.equals("4")) {
            System.out.println("\n--Hva vil du opprette?--");

            for (int i = 0; i < alternativer.length; i++) {
                System.out.println(i + ": " + alternativer[i]);
            }
            System.out.println("4: Gaa tilbake");
            System.out.print("Skriv inn her: ");
            svar = input.nextLine().strip();

            if (svar.equals("0")) { // oppretter og legger til lege
                String[] legeNavn;
                String legeNavnRiktig = "";
                String spesialistKode;
                boolean legeLagtTil = false;

                System.out.print("Skriv inn NAVN paa lege: ");

                legeNavn = input.nextLine().strip().split(" ");

                for (int i = 0; i < legeNavn.length; i++) {
                    legeNavnRiktig += storForbokstav(legeNavn[i]) + " ";
                }

                System.out.println("Er legen som legges til spesialist?");
                System.out.print("Svar (ja/nei): ");
                svar = input.nextLine().strip().toLowerCase();
                if (svar.equals("ja")) {
                    System.out.print("Skriv inn spesialistens KONTROLLKODE: ");
                    spesialistKode = input.nextLine().strip();
                    leggTilSpesialist(legeNavnRiktig.strip(), spesialistKode);
                    legeLagtTil = true;

                } else if (svar.equals("nei")) {
                    leggTilLege(legeNavnRiktig.strip());
                    legeLagtTil = true;
                }

                if (legeLagtTil) {
                    System.out.println("\nLegen er lagt inn velykket!");
                } else {
                    System.out.println("\nLege ikke lagt inn...");
                }

            } else if (svar.equals("1")) { // oppretter og legger til pasient
                String navn;
                String fodNr;
                System.out.print("Skriv inn NAVN paa pasient: ");
                navn = input.nextLine().strip();
                System.out.print("Skriv inn FOEDSELSNUMMER paa pasient: ");
                fodNr = input.nextLine().strip();
                leggTilPasient(navn, fodNr);
            } else if (svar.equals("2")) { // oppretter og legger til ny resept via lege sin skrivResept()
                Integer resepttype;
                Integer idLegemiddel;
                Integer reit = 0;
                Integer idPasient;
                String navnLege;

                // finner pasient
                System.out.println("Hvilken pasient skal resepten gjelde for?");
                skrivUtPasienter();
                System.out.print("Skriv inn: ");
                idPasient = Integer.parseInt(input.nextLine().strip()); // pasientID

                // sjekk om navnet finnes i pasientlista
                Boolean fantP = false;
                for (Pasient i : pasientliste) {
                    if (idPasient.equals(i.hentId())) {
                        fantP = true;
                        break;
                    }
                }
                if (!fantP) {
                    System.out.println("Fant ikke pasienten");
                    return;
                }

                // finner legemiddel
                Legemiddel legemiddelpeker = null;
                System.out.println("\n--Hvilket legemiddel skal resepten gjelde for?--");
                skrivUtLegemidler();
                System.out.print("Skriv inn: ");
                idLegemiddel = Integer.parseInt(input.nextLine().strip()); // legemiddelID

                // sjekk om legemiddelet finnes i legemiddellista
                Boolean fantLL = false;
                for (Legemiddel ll : legemidler) {
                    if (idLegemiddel.equals(ll.hentId())) {
                        fantLL = true;
                        legemiddelpeker = ll;
                        break;
                    }
                }
                if (!fantLL) {
                    System.out.println("Fant ikke legemiddelet");
                    return;
                }

                // finner lege
                Lege legepeker = null;
                System.out.println("\n--Hvilken lege skal skrive ut resepten?--");

                if (legemiddelpeker instanceof Narkotisk) {
                    skrivUtSpesialisterUnummerert();
                } else {
                    skrivUtLegerUnummerert();
                }

                System.out.print("Skriv inn FULLT NAVN: ");
                navnLege = input.nextLine().strip().toLowerCase();

                // sjekk om navnet finnes i legelista og om vedkomne er spesialist

                Boolean fantL = false;
                for (Lege l : legeliste) {
                    if (navnLege.equals(l.hentNavn().toLowerCase())) {
                        fantL = true;
                        legepeker = l;
                        if ((legemiddelpeker instanceof Narkotisk) && !(legepeker instanceof Spesialist)) {
                            System.out.println("Legen er ikke spesialist...");
                            return;
                        }
                        break;
                    }
                }
                if (!fantL) {
                    System.out.println("\nFant ikke legen...");
                    return;
                }

                // hvis det er et narktisk stoff lages blaa resept
                if (legemiddelpeker instanceof Narkotisk) {
                    Resept r = null;
                    System.out.print("Skriv inn reit: ");
                    reit = Integer.parseInt(input.nextLine().strip());
                    Pasient p = pasientliste.hent(idPasient);
                    try {
                        r = legepeker.skrivBlaaResept(legemiddelpeker, p, reit);
                    } catch (UlovligUtskrift uu) {
                        System.out.println(uu);
                    }
                    reseptliste.leggTil(r);
                    System.out.println("Resept lagt til!");
                    return;
                }

                // finner resepttypen
                System.out.println("\n--Hvilken resepttype skal lages?--");

                String[] resepttyper = { "Hvit", "Blaa", "Militaer", "P-resept" };
                for (int i = 1; i <= resepttyper.length; i++) {
                    System.out.println(i + ": " + resepttyper[i - 1]);
                }

                System.out.print("Skriv inn: ");
                resepttype = Integer.parseInt(input.nextLine().strip().toLowerCase());
                if (resepttype > 4 || resepttype < 1) {
                    System.out.println("Ikke gyldig input");
                    return;
                }

                // finner reit om dne ikke er militearresept
                if (!(resepttype.equals(3))) {
                    System.out.print("Skriv inn reit: ");
                    reit = Integer.parseInt(input.nextLine().strip());
                }

                // lager hvit resept
                if (resepttype.equals(1)) {
                    String rt = "hvit";
                    leggTilResept(idLegemiddel, navnLege, idPasient, rt, reit);
                }
                // lager blaa resept
                else if (resepttype.equals(2)) {
                    String rt = "blaa";
                    leggTilResept(idLegemiddel, navnLege, idPasient, rt, reit);
                }
                // lager militaer resept
                else if (resepttype.equals(3)) {
                    String rt = "militaer";
                    leggTilResept(idLegemiddel, navnLege, idPasient, rt, 3);
                }
                // lager p resept
                else if (resepttype.equals(4)) {
                    String rt = "p";
                    leggTilResept(idLegemiddel, navnLege, idPasient, rt, reit);
                }

            } else if (svar.equals("3")) { // oppretter nytt legemiddel
                String legemiddelNavn;
                int legemiddelPris;
                double legemiddelVirkestoff;
                int legemiddelStyrke;
                Legemiddel legemiddelet = null;

                System.out.println("\n--Oppretter nytt legemiddel--");

                System.out.println("Hva slags type legemiddel skal opprettes?");
                Integer legemiddelSvar;
                String[] typer = { "Narkotisk", "Vanedannende", "Vanlig" };
                for (int i = 1; i <= typer.length; i++) {
                    System.out.println(i + ": " + typer[i - 1]);
                }
                System.out.print("Valg: ");
                legemiddelSvar = Integer.parseInt(input.nextLine().strip().toLowerCase());

                if (legemiddelSvar > 3 || legemiddelSvar < 1) {
                    System.out.println("Ugyldig input");
                    return;
                }

                System.out.print("Skriv inn navn: ");
                legemiddelNavn = input.nextLine().strip();
                System.out.print("Skriv inn pris: ");
                legemiddelPris = Integer.parseInt(input.nextLine().strip());
                System.out.print("Skriv inn mengde virkestoff (mg): ");
                legemiddelVirkestoff = Double.parseDouble(input.nextLine().strip());

                if (legemiddelSvar.equals(1)) {
                    System.out.print("Skriv inn legemiddelets styrke: ");
                    legemiddelStyrke = Integer.parseInt(input.nextLine().strip());
                    legemiddelet = new Narkotisk(legemiddelNavn, legemiddelPris, legemiddelVirkestoff,
                            legemiddelStyrke);
                    legemidler.leggTil(legemiddelet);
                } else if (legemiddelSvar.equals(2)) {
                    System.out.print("Skriv inn legemiddelets styrke: ");
                    legemiddelStyrke = Integer.parseInt(input.nextLine().strip());
                    legemiddelet = new Vanedannende(legemiddelNavn, legemiddelPris, legemiddelVirkestoff,
                            legemiddelStyrke);
                    legemidler.leggTil(legemiddelet);
                } else if (legemiddelSvar.equals(3)) {
                    legemiddelet = new Vanlig(legemiddelNavn, legemiddelPris, legemiddelVirkestoff);
                    legemidler.leggTil(legemiddelet);
                }

            } else if (svar.equals("4")) {
                return;
            }
        }

    }

    // OPPG E4 STOTTEMETODER
    // legg til pasient
    public void leggTilPasient(String navn, String fodNr) {
        Pasient p = new Pasient(navn, fodNr);
        pasientliste.leggTil(p);
    }

    // legge til Lege
    public void leggTilLege(String navn) {
        Lege l = new Lege(navn);
        legeliste.leggTil(l);
    }

    // legge til spesialist
    public void leggTilSpesialist(String navn, String kode) {
        Spesialist s = new Spesialist(navn, kode);
        legeliste.leggTil(s);
    }

    // legge til Resept
    public void leggTilResept(int legemiddelId, String navnLege, int idPasient, String resepttype, int reit) {
        if (resepttype.equals("hvit")) {// lager hvit resept
            Lege lege = null;
            for (Lege i : legeliste) {
                if (i.hentNavn().toLowerCase().equals(navnLege)) {
                    lege = i;
                    break;
                }
            }
            Legemiddel l = legemidler.hent(legemiddelId);
            Pasient p = pasientliste.hent(idPasient);
            Resept r = null;
            try {
                r = lege.skrivHvitResept(l, p, reit);
                reseptliste.leggTil(r);
            } catch (UlovligUtskrift uu) {
                System.out.println(uu);
            }
        }
        if (resepttype.equals("blaa")) {// lager blaa resept
            Lege lege = null;
            for (Lege i : legeliste) {
                if (i.hentNavn().toLowerCase().equals(navnLege)) {
                    lege = i;
                    break;
                }
            }
            Legemiddel l = legemidler.hent(legemiddelId);
            Pasient p = pasientliste.hent(idPasient);
            Resept r = null;
            try {
                r = lege.skrivBlaaResept(l, p, reit);
                reseptliste.leggTil(r);
            } catch (UlovligUtskrift uu) {
                System.out.println(uu);
            }
        }
        if (resepttype.equals("p")) {// lager presept
            Lege lege = null;
            for (Lege i : legeliste) {
                if (i.hentNavn().toLowerCase().equals(navnLege)) {
                    lege = i;
                    break;
                }
            }
            Legemiddel l = legemidler.hent(legemiddelId);
            Pasient p = pasientliste.hent(idPasient);
            Resept r = null;
            try {
                r = lege.skrivPResept(l, p, reit);
                reseptliste.leggTil(r);
            } catch (UlovligUtskrift uu) {
                System.out.println(uu);
            }
        }

        if (resepttype.equals("militaer")) {// lager militaerresept
            Lege lege = null;
            for (Lege i : legeliste) {
                if (i.hentNavn().toLowerCase().equals(navnLege)) {
                    lege = i;
                    break;
                }
            }

            Legemiddel l = legemidler.hent(legemiddelId);
            Pasient p = pasientliste.hent(idPasient);
            Resept r = null;
            try {
                r = lege.skrivMilResept(l, p);
                reseptliste.leggTil(r);
            } catch (UlovligUtskrift uu) {
                System.out.println(uu);
            }
        }
    }

    // stor forbokstav for korrekt innlesning
    public String storForbokstav(String s) {
        if (s == null || s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    // E5 BRUKE PASIENT SIN RESEPT
    public void brukResept() {
        Scanner input = new Scanner(System.in);

        Pasient pasientPeker = null;
        Resept reseptPeker = null;
        Integer svar;

        System.out.println("Hvilken pasient vil du se resepter for?");
        skrivUtPasienter();
        System.out.print("Svar her:");
        svar = Integer.parseInt(input.nextLine().strip());

        for (Pasient p : pasientliste) {
            if (svar.equals(p.hentId())) {
                System.out.println("Valgt pasient: " + p);
                pasientPeker = p;
                break;
            }
        }
        if (pasientPeker.equals(null)) {
            System.out.println("Pasient ikke funnet");
            return;
        }

        if (pasientPeker.hentResepter().stoerrelse() == 0) {
            System.out.println("Pasienten har foreloepig ingen resepter");
            return;
        }

        System.out.println("Hvilken resept vil du bruke?");

        for (Resept r : pasientPeker.hentResepter()) {
            System.out.println(r.hentId() + ": " + r.hentLegemiddel() + "(" + r.hentReit() + " reit)");
        }

        System.out.print("Svar her:");

        svar = Integer.parseInt(input.nextLine().strip());
        for (Resept r : reseptliste) {
            if (svar.equals(r.hentId())) {
                reseptPeker = r;
                break;
            }
        }

        if (reseptPeker.bruk()) {
            System.out.println("Brukte resept paa " + reseptPeker.hentLegemiddel() + ". Antall gjenvaerende reit: "
                    + reseptPeker.hentReit());
        } else if (!(reseptPeker.bruk())) {
            System.out.println(
                    "Kunne ike bruke resept paa " + reseptPeker.hentLegemiddel() + " (ingen gjenvaeerende resept)");
        }
        return;
    }

    // E6 SKRIVER UT STATISTIKK
    public void skrivUtStats() {
        Scanner input = new Scanner(System.in);

        // presenterer valgmuligheter for hvilken statistikk man vil se
        System.out.println("\n--Hva vil du se statistikk for?--");
        String[] valg = { "Totalt antall utskrevne resepter", "Statistikk om mulig misbruk av narkotiske legemidler" };
        Integer svar;
        for (int i = 1; i < 3; i++) {
            System.out.println(i + ": " + valg[i - 1]);
        }
        System.out.print("Skriv inn Her: ");
        svar = Integer.parseInt(input.nextLine().strip());

        // totalt antall utskrevne resepter
        if (svar.equals(1)) {
            Integer svarL;
            System.out.println("\n--Hvilke legemiddeltype vil du se antall utskrevne resepter for?--");

            String[] valgL = { "Narkotisk", "Vanedannende" };
            for (int i = 1; i < 3; i++) {
                System.out.println(i + ": " + valgL[i - 1]);
            }
            System.out.print("Skriv inn Her: ");
            svarL = Integer.parseInt(input.nextLine().strip());

            // totalt antall utskrevne resepter paa narkotiske legemidler
            if (svarL.equals(1)) {
                int teller = 0;
                for (Resept r : reseptliste) {
                    if (r.hentLegemiddel() instanceof Narkotisk) {
                        teller++;
                    }
                }
                System.out.println("Det er totalt skrevet ut " + teller + " resepter for narkotiske legemidler");

            }

            // totalt antall utskrevne respeter paa vandannende legemidler
            else if (svarL.equals(2)) {
                int teller = 0;
                for (Resept r : reseptliste) {
                    if (r.hentLegemiddel() instanceof Vanedannende) {
                        teller++;
                    }
                }
                System.out.println("Det er totalt skrevet ut " + teller + " resepter for vanedannende legemidler");

            }

            if (svarL < 1 || svarL > 2) {
                System.out.println("Ugyldig input");
            }

        }

        // stats om mulig misbruk av narkotika
        else if (svar.equals(2)) {
            // list opp NAVN paa alle LEGER som har skrevet ut minst én resept paa nark og
            // antall slike resepter per lege

            System.out.println("\n--Leger som har skrevet ut minst én resept paa narkotiske legemidler--");
            HashMap<Lege, Integer> legeAntNarkRes = new HashMap<>();
            int tellerLege;
            for (Lege l : legeliste) {

                tellerLege = 0;
                for (Resept r : l.hentResepter()) {
                    if (r.hentLegemiddel() instanceof Narkotisk) {
                        tellerLege++;

                    }
                }
                if (tellerLege > 0) {
                    legeAntNarkRes.put(l, tellerLege);
                }

            }
            legeAntNarkRes.forEach((key, value) -> System.out.println(key.hentNavn() + ": " + value + " resept(er)"));
            // list opp NAVN paa alle PASIENTER som har minst én gyldig resept paa
            // NARKLegemiddel og antall disse per pasient

            System.out.println("\n--Pasienter som har minst én resept paa narkotiske legemidler--");
            HashMap<Pasient, Integer> pasientAntNarkRes = new HashMap<>();
            int teller;
            for (Pasient p : pasientliste) {

                teller = 0;
                for (Resept r : p.hentResepter()) {
                    if (r.hentLegemiddel() instanceof Narkotisk && r.hentReit() > 0) {
                        teller++;

                    }
                }
                if (teller > 0) {
                    pasientAntNarkRes.put(p, teller);
                }

            }
            pasientAntNarkRes
                    .forEach((key, value) -> System.out.println(key.hentNavn() + ": " + value + " resept(er)"));

        }

        else if (svar < 1 || svar > 2) {
            System.out.println("Ugyldig input");
        }
    }
}
