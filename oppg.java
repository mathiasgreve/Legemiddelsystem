public class oppg {
    public static void main(String[] args) {
        
	//oppg
	IndeksertListe<String> lis = new IndeksertListe<>();
	lis.leggTil("A");
	lis.leggTil("B");
	lis.leggTil("C");
    
    System.out.println(lis);

    lis.fjern();
    System.out.println(lis);
    lis.sett(1,"D");
    System.out.println(lis);



    }
}
