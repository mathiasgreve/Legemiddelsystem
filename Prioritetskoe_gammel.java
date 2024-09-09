class Prioritetskoe_gammel<E extends Comparable<E>> extends Lenkeliste<E> {

  @Override
  //setter inn elementer i sortert rekkefølge
  //lavest prioritet nærmest 0, høyest prioritet lengst fra 0
  public void leggTil(E x) {
      //hvis tom
      if (stoerrelse() == 0) {
          super.leggTil(x);
          return;
      }
      //hvis ikke tom
      Node nyNode=new Node(x);
      Node peker = start;
      for (int i = 0; i < stoerrelse() ; i++) {

        if (peker.data.compareTo(x) > 0) { 
            if(i==0){
                nyNode.neste = start;
                start.forrige=nyNode;
                start=nyNode;
                return;
            }
            if(i<stoerrelse() && i>0) {
                nyNode.neste = peker;
                nyNode.forrige=peker.forrige;
                nyNode.forrige.neste = nyNode;
                nyNode.neste.forrige = nyNode;
                return;
            }
          return;
        }
        //Huskeregel compareTo:
        //a.compareTo(b)
        //hvis a > b er true, returnerer a.compareTo(b) større enn null
        //hvis a < b er true, returnerer a.compareTo(b) mindre enn null
        peker=peker.neste;
      }
      //hvis ikke tom og ingen verdier er større
      super.leggTil(x);

  }
  public void leggTil(int pos, E x) throws UgyldigListeindeks { //sett in x i lista i posisjon pos og pos er mindre enn eller lik listelengden og større enneller lik 0
    // eldre elementer 'forskyves til høyre'
    
    if(pos<0 || pos>stoerrelse()) {
        throw new UgyldigListeindeks(pos);
    }
    
    Node nyNode = new Node(x);
    int teller = 0;
    Node peker = start;
    while(teller<=stoerrelse()) {
        if(teller == pos) {
            if(stoerrelse()==0) {
                // start = nyNode;
                // slutt = nyNode;
                super.leggTil(x);
                return;
            }
            if(pos == stoerrelse()) {
                // nyNode.forrige=slutt;
                // slutt.neste=nyNode;
                // slutt=nyNode;
                super.leggTil(x);
                return;
            }
            if(pos==0){
                nyNode.neste = start;
                start.forrige=nyNode;
                start=nyNode;
                return;
            }
            if(pos<=stoerrelse() && pos>=0) {
                nyNode.neste = peker;
                nyNode.forrige=peker.forrige;
                nyNode.forrige.neste = nyNode;
                nyNode.neste.forrige = nyNode;
                return;
            }
        } else {
            peker=peker.neste;
            teller++;
        }

    }

}
}