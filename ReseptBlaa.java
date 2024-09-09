import java.lang.Math;

public class ReseptBlaa extends Resept {
    String farge = "blaa";

    // rabatt
    private double rabattProsent = 0.25;

    public ReseptBlaa(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) { // bytter ut pasientID
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale() {
        return (int) Math.round(legemiddel.hentPris() * rabattProsent);
    }

}