public class ReseptHvit extends Resept {
    String farge = "hvit";

    public ReseptHvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) { // bytter ut pasientID
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

}