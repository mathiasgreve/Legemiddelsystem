public class Spesialist extends Lege implements Godkjenningsfritak {
    protected String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode() {
        return kontrollkode;
    }

    // skrive blaa resept
    @Override
    ReseptBlaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        ReseptBlaa blaaResept = new ReseptBlaa(legemiddel, this, pasient, reit);
        
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);

        return blaaResept;
    }

    @Override
    public String toString() {
        return super.toString() + " - Kontrollkode for spesialistlege: " + kontrollkode;
    }
}
