public class Hovedprogram {
    public static void main(String[] args) {
        Legesystem l = new Legesystem();
        l.lesInnTekstfil("legedata.txt");
        l.loekke();
    }
}
