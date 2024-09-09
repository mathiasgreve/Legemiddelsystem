public class Lege implements Comparable<Lege> {
    protected String navn;
    IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    public Lege(String navn) {
        this.navn = navn;
    }

    // returnerer legens navn
    public String hentNavn() {
        return navn;
    }

    // returnerer indeksertliste av resepter legen har skrevet ut
    public IndeksertListe<Resept> hentResepter() {
        return utskrevneResepter;
    }

    // skrive hvitResept
    ReseptHvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        ReseptHvit hvitResept = new ReseptHvit(legemiddel, this, pasient, reit);

        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(hvitResept.hentLege(), legemiddel);
        }

        utskrevneResepter.leggTil(hvitResept);
        pasient.leggTilResept(hvitResept);

        return hvitResept;
    }

    // skrive Militaerresept
    ReseptMilitaer skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        ReseptMilitaer milResept = new ReseptMilitaer(legemiddel, this, pasient);

        if (!(milResept.hentLege().hentNavn().equals(navn)) || legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(milResept.hentLege(), legemiddel);
        }

        utskrevneResepter.leggTil(milResept);
        pasient.leggTilResept(milResept);

        return milResept;
    }

    // skrive P-resept
    ReseptP_resept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        ReseptP_resept pResept = new ReseptP_resept(legemiddel, this, pasient, reit);

        if (pResept.hentLege().hentNavn() != navn || legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(pResept.hentLege(), legemiddel);
        }

        utskrevneResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);

        return pResept;
    }

    // skrive blaa resept
    ReseptBlaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        ReseptBlaa blaaResept = new ReseptBlaa(legemiddel, this, pasient, reit);

        if ((legemiddel instanceof Narkotisk && !(this instanceof Spesialist))) {
            throw new UlovligUtskrift(blaaResept.hentLege(), legemiddel);
        }

        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);

        return blaaResept;
    }

    @Override
    public String toString() {
        return "Navn: " + navn;
    }

    @Override
    public int compareTo(Lege l) {
        if (navn.compareTo(l.navn) < 0)
            return -1;
        if (navn.compareTo(l.navn) > 0)
            return 1;
        return 0;
    }

}