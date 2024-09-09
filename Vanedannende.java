public class Vanedannende extends Legemiddel {
    public final int styrkeVanedannende;

    public Vanedannende(String navn, int pris, double mengdeVirkestoff, int styrke) {
        super(navn, pris, mengdeVirkestoff);
        styrkeVanedannende = styrke;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}