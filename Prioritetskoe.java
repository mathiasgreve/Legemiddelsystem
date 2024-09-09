class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) { // setter inn elementer i sortert rekkefolge
        // minst til storst, med minst forst i lista
        Node peker = start;
        Node nyNode = new Node(x);

        if (stoerrelse() == 0) { // om lista er tom legges bare elementet inn
            super.leggTil(x);
            return;
        }

        for (int i = 0; i < stoerrelse(); i++) { // gaar gjennom lista for aa finne elementer som er mindre, begynner
                                                 // paa starten
            if (x.compareTo(peker.data) < 0) { // hvis elementet som skal legges inn er mindre enn pekerens:
                if (i == 0) { // hvis pekeren er det forste elementet i lista tar det nye elementet over
                              // foersteplassen
                    nyNode.neste = start;
                    start.forrige = nyNode;
                    start = nyNode;
                    return;
                }

                if (i < stoerrelse() && i > 0) { // hvis pekeren hverken er det foerste eller siste elementet settes det
                                                 // nye elementet inn foran
                    nyNode.neste = peker;
                    nyNode.forrige = peker.forrige;
                    nyNode.forrige.neste = nyNode;
                    nyNode.neste.forrige = nyNode;
                    return;
                }
                return;
            }
            peker = peker.neste;
        }
        super.leggTil(x); // legges sist om alle elementene er mindre enn det som skal settes inn

    }

    public E hent(int pos) {
        if (pos < 0 || pos >= stoerrelse()) {// kaster UgyldigListeIndeks-exception dersom posisjonen som gis ikke
                                             // korresponderer med et element i lista
            throw new UgyldigListeindeks(pos); // indeks pos
        }

        Node peker = start;

        for (int i = 0; i < stoerrelse(); i++) { // itererer gjennom lista til det treffer riktig posisjon
            if (i == pos) { // ved riktig posisjon returneres riktig data
                return peker.data;
            }
            peker = peker.neste;

        }
        return null;

    }

}
