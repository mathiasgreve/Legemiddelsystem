public abstract class Resept implements Comparable<Resept> {
    protected int ID;
    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    private Pasient pasient;
    public int reit;
    protected static int antResepter = 0;
    String farge = "";

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        ID = antResepter++;
    }

    public int hentId() {
        return ID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientId() {
        return pasient.id; 
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        } else {
            return false;
        }
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    @Override
    public String toString() {
        // return "\nInformasjon om resepten til : " + pasient.navn + "\nLegemiddel: " +
        // hentLegemiddel().navn + "\nID på resepten: " + ID
        // + "\nUtskrivende lege: " + utskrivendeLege.hentNavn() + "\nAntall ganger den
        // kan brukes igjen: " + reit;
        return "Resept " + "paa " + legemiddel.hentNavn() + ", skrevet av " + utskrivendeLege.hentNavn() + " til "
                + pasient.hentNavn() + " (reit " + reit + ")";
    }

    @Override
    public int compareTo(Resept r) {
        if (ID < r.ID)
            return -1;
        if (ID > r.ID)
            return 1;
        return 0;
    }

}