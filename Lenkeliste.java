import java.util.Iterator;

 class Lenkeliste<E> implements Liste<E> { //NB!! skal være abstrakt
    protected Node start = null;
    protected Node slutt = null; // NB undervises uke 7
    
    //indre klasse Node
    protected class Node { 
        Node neste = null;
        Node forrige = null; // NB undervises uke 7
        E data = null;
        public Node(E element) {
            data = element;
        }
    }

    //Indre klasse Iterator
    class LenkelisteIterator implements Iterator<E> { //trenger ikke typeparameter??
        private Node peker = start;
        
        @Override
        public boolean hasNext(){
            return peker != null;
        };

        @Override
        public E next(){
            E verdiHoldtAv = peker.data;
            peker = peker.neste;
            return verdiHoldtAv;
        };

    }

    @Override 
    public Iterator<E> iterator(){
        return new LenkelisteIterator();
    };


    @Override
    public int stoerrelse() { // teller antall elementer i lista
        int teller = 0;
        Node peker = start;
        while (peker != null) { // naar pekeren ikke er 'null' oekes antallet elementer med én
            teller ++;
            peker = peker.neste;
        }
        return teller;
    }

    @Override
    public void leggTil(E element) { // nye elementer legges til sist i listen
        Node nyNode = new Node(element);
        if(slutt == null) { // dersom lista er tom legges det til et element som pekes paa av baade start- og sluttreferansen
            start = nyNode;
            slutt = nyNode;
            return;
        }
        slutt.neste = nyNode; // dersom lista ikke er tom legges nytt element til sist
        nyNode.forrige = slutt;
        slutt = nyNode;
    }

    @Override
    public E hent() throws UgyldigListeindeks { // returnerer det foerste elementet i lista
        if(stoerrelse() == 0) { // kaster UgyldigListeIndeks-exception dersom lista er tom
            throw new UgyldigListeindeks(0);
        }
        
        if(start != null) { //om ikke startpekeren er tom returneres dataen fra det foerste elementet
            return start.data;
        } 
        else {return null;} 
    }

    @Override
    public E fjern() throws UgyldigListeindeks { // returnerer data fra evnetuelt foerste elemenet og tas saa ut
        if(stoerrelse()==0) { // kaster UgyldigListeIndeks-exception dersom lista er tom
            throw new UgyldigListeindeks(-1); //indeks -1 
        }
        
        if (start != null) { //hvis det er ett eller flere elementer i lista:
            E elementBeholder = start.data; //henter data fra foerste element

            //fjerner foerste element
            if (stoerrelse() > 1) { //hvis det er flere enn ett element settes start-elemntet til det neste i lista
                start.neste.forrige = null;
                start = start.neste;
            } else if (stoerrelse() == 1) { //om det kun er ett element settes start- og sluttreferansene til 'null'
                start = null;
                slutt = null;
            }

            return elementBeholder;
        }
        return null;
    }

    @Override
    public String toString() {
        String tekst = "";
        Node peker = start;
        while(peker != null) {
            tekst += "\nNode-element: " + peker.data;
            peker = peker.neste; 
        }
        return tekst;
    }

}