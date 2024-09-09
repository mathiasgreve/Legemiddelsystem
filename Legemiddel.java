
//klasse Legemiddel det ikke skal være mulig å opprette instans av
public abstract class Legemiddel implements Comparable<Legemiddel> {

    // instansvariabler - alle public final, unntatt 'pris'
    public final String navn;
    public int id;
    protected int pris;
    public final double mengdeVirkestoff;
    protected static int antLegemidler = 0;

    // konstruktør
    public Legemiddel(String navn, int pris, double mengdeVirkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.mengdeVirkestoff = mengdeVirkestoff;
        id = antLegemidler++;

    }

    // metode returnerer prisen
    public int hentPris() {
        return pris;
    }

    // returnerer navn på legemiddel
    public String hentNavn() {
        return navn;
    }

    // metode oppdaterer til ny pris
    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        // GAMMEL, OMFATTENDE UTSKRIFT
        // return "\nInformasjon om legemiddelet: " + navn + "\nID: " + id + "\nPris: "
        // + pris
        // + "\nMengde virkestoff (mg): " + mengdeVirkestoff;

        // NY, KORTFATTET UTSKRIFT
        return "" + navn;
    }

    public int hentId() {
        return id;
    }

    @Override
    public int compareTo(Legemiddel lm) {
        if (id < lm.id)
            return -1;
        if (id > lm.id)
            return 1;
        return 0;
    }

}