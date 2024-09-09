import java.lang.Math;

public class TestLegemiddel {

    private static void sjekk(String hva, boolean test) {
        if (!test) {
            System.out.println("Testen av " + hva + " feilet");
            System.exit(1);
        }
    }

    private static boolean TestLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        return legemiddel.id == forventetLegemiddelId;
    }

    private static void testRiktigLegemiddelId() {
        Vanlig vanlig = new Vanlig("Ibux", 25, 2);
        System.out.println(vanlig);
        Vanedannende vane = new Vanedannende("Otrivin", 20, 2, 2);
        System.out.println(vane);
        Narkotisk nark = new Narkotisk("Benzodiazepim", 80, 12, 9);
        System.out.println(nark);
        sjekk("om det første opprettede legemiddelet får ID = 0", TestLegemiddelId(vanlig, vanlig.hentId()));
        sjekk("om det andre opprettede legemiddelet får ID = 1", TestLegemiddelId(vane, vane.hentId()));
        sjekk("om det tredje opprettede legemiddelet får ID = 2", TestLegemiddelId(nark, nark.hentId()));
        System.out.println("\nID-setting for legemidler, alt riktig!");
    }

    private static void testEndringAvPris() {
        Narkotisk nark = new Narkotisk("Valium", 50, 10, 7);
        int nyPris = (int) Math.random();
        nark.settNyPris(nyPris);
        sjekk("ny prissetting av legemiddel med metoden 'Legemiddel.nyPris()'", nark.hentPris() == nyPris);
        System.out.println("Ny prissetting av legemiddel med metoden 'nyPris()' er riktig implementert!");
    }

    public static void main(String[] args) {
        testRiktigLegemiddelId();
        testEndringAvPris();

    }

}
