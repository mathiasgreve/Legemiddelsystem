public class ReseptMilitaer extends ReseptHvit {

    public ReseptMilitaer(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {// bytter ut pasientID
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale() {
        return 0;
    }

}