public class TestResepter {
    // oppretter noen legemidler
    static Narkotisk nark = new Narkotisk("Valium", 50, 10, 7);
    static Narkotisk nark1 = new Narkotisk("Benzodiazepim", 80, 12, 9);
    static Vanedannende vane = new Vanedannende("Otrivin", 200, 2, 2);

    // oppretter lege
    static Lege lege = new Lege("Martine");

    // oppretter noen resepter
    static ReseptMilitaer mil = new ReseptMilitaer(nark, lege, 11);
    static ReseptP_resept pres = new ReseptP_resept(vane, lege, 6, 5);
    static ReseptP_resept pres1 = new ReseptP_resept(nark, lege, 6, 5);
    static ReseptBlaa blaa = new ReseptBlaa(nark1, lege, 2, 1);

    private static void sjekk(String hva, boolean test) {
        if (!test) {
            System.out.println("\nTesten av " + hva + " feilet");
            System.exit(1);
        }
    }

    private static void testBrukResept() {
        System.out.println(mil);
        int reit = mil.hentReit();
        mil.bruk();
        sjekk("'bruk()', som skal redusere reit for hver gang resepten brukes", reit == (mil.hentReit() + 1));
        System.out.println("\nBruk av resept reduserer reit korrekt! :)");
    }

    private static void testRiktigPris() {
        sjekk("riktig pris paa militaerresept", (mil.prisAaBetale() == 0));
        System.out.println("Test for pris paa militaer-resept var vellykket");
        sjekk("riktig pris paa P-resept", (pres.prisAaBetale() == (pres.hentLegemiddel().hentPris() - 108)));
        sjekk("riktig pris paa P-resept", (pres1.prisAaBetale() == 0));
        System.out.println("Test for pris paa P-resept var vellykket");
        sjekk("riktig pris paa blaa resept",
                (blaa.prisAaBetale() == (int) Math.round(blaa.hentLegemiddel().hentPris() * 0.25)));
        System.out.println("Test for pris paa blaa resept var vellykket");
    }

    private static void testFarge() {
        sjekk("riktig farge", mil.farge() == "hvit");
        System.out.println("Militærresept har riktig farge");
        sjekk("riktig farge", pres.farge() == "hvit");
        System.out.println("P-resept har riktig farge");
        sjekk("riktig farge", blaa.farge() == "blaa");
        System.out.println("Blaa resept har riktig farge");
    }

    public static void main(String[] args) {
        testBrukResept();
        testRiktigPris();
        testFarge();

    }

}
