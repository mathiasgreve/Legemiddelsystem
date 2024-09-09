public class Narkotisk extends Legemiddel {
    public final int styrke;

    public Narkotisk(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        // return super.toString() +"\nStyrke: " + styrke;
        return super.toString();
    }
}