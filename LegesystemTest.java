public class LegesystemTest {
    public static void main(String[] args) {
        Legesystem l = new Legesystem();
        l.lesInnTekstfil("legedata.txt");
        l.loekke();
        // l.skrivUtPasienter();
        // l.leggTilPasient("Merethe Pettersen", "22435533");
        // l.skrivUtPasienter();

        // for(Pasient i:pasientliste){System.out.println(i);}
        // for(Legemiddel i : legemidler){System.out.println(i);}
        // System.out.println(legemidler.stoerrelse());
        // System.out.println("\nSISTE TEST\n");
        // for(Lege i :legeliste){
        // System.out.println(i);
        // System.out.println("\n"+i.hentNavn() + " sine resepter:\n"
        // +i.hentResepter());}
        // System.out.println();


        // System.out.println("Info om pasienter\n");
        // skrivUtPasienter(pasientliste);
        // System.out.println("Info om leger\n");
        // skrivUtLeger(legeliste);
        // System.out.println("Info om legemidler\n");
        // skrivUtLegemidler(legemidler);
        // System.out.println(pasientliste.hent(1).hentResepter());
    }
}
