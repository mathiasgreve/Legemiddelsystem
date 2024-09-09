public class ReseptP_resept extends ReseptHvit {

    private static int rabattKroner = 108;

    public ReseptP_resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {//bytter ut pasientID 
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        int pris = legemiddel.hentPris();
        if (pris > 107) {
            pris = pris - rabattKroner;
            return pris;
        } else {
            return 0;
        }
    }
}