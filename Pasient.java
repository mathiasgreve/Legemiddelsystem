class Pasient {
    String navn;
    String fodselsNr;
    int id;
    IndeksertListe<Resept> reseptListe = new IndeksertListe<Resept>();
    static int antPasienter = 0;

    public Pasient(String navn, String fodselsNr) {
        this.navn = navn;
        this.fodselsNr = fodselsNr;
        id = antPasienter++;
    }

    public void leggTilResept(Resept resept) {
        reseptListe.leggTil(resept);
    }

    public String hentNavn() {
        return navn;
    }

    public int hentId() {
        return id;
    }

    @Override
    public String toString() {
        String str = "";
        str = navn + " (fnr: " + fodselsNr + ")";
        return str;
    }

    public IndeksertListe<Resept> hentResepter() {
        return reseptListe;
    }

    public void skrivUtResepter() {
        for (Resept r : reseptListe) {
            System.out.println(r.hentId() + ": " + r.hentLegemiddel().hentNavn());
        }
    }

}