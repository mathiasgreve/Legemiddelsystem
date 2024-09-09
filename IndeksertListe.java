class IndeksertListe<E> extends Lenkeliste<E> {

    public void leggTil(int pos, E x) throws UgyldigListeindeks { // sett in x i lista i posisjon pos og pos er mindre
                                                                  // enn eller lik listelengden og større enneller lik 0
        // eldre elementer 'forskyves til høyre'

        if (pos < 0 || pos > stoerrelse()) { // kaster UgyldigListeIndeks-exception dersom posisjonen som gis ikke
                                             // korresponderer med en lovlig posisjon
            throw new UgyldigListeindeks(pos); // indeks pos
        }

        Node nyNode = new Node(x);
        int teller = 0;
        Node peker = start;
        // itererer gjennom lista fra starten
        while (teller <= stoerrelse()) { // saa lenge telleren er mindre eller lik antall elementer i lista:
            if (teller == pos) { // naar iterasjonen har kommet til posisjonen man oensker aa sette det nye
                                 // elementet paa:
                // ulike implementeringer av innsetting avhengig av posisjon
                if (stoerrelse() == 0) { // hvis det er foerste elementet som legges til i lista
                    start = nyNode;
                    slutt = nyNode;
                    return;
                }
                if (pos == stoerrelse()) { // hvis det legges til bakerst i lista
                    nyNode.forrige = slutt;
                    slutt.neste = nyNode;
                    slutt = nyNode;
                    return;
                }
                if (pos == 0) {// hvis det legges til foerst i lista
                    nyNode.neste = start;
                    start.forrige = nyNode;
                    start = nyNode;
                    return;
                }
                if (pos <= stoerrelse() && pos >= 0) { // hvis det legges mellom allerede eksisterende lementer i lista
                    nyNode.neste = peker;
                    nyNode.forrige = peker.forrige;
                    nyNode.forrige.neste = nyNode;
                    nyNode.neste.forrige = nyNode;
                    return;
                }
            } else {
                peker = peker.neste;
                teller++;
            }

        }

    }

    public void sett(int pos, E x) throws UgyldigListeindeks { // erstatter elementet i pos med x.
        if (pos < 0 || pos >= stoerrelse()) { // kaster UgyldigListeIndeks-exception dersom posisjonen som gis ikke
                                              // korresponderer med et element i lista
            throw new UgyldigListeindeks(pos); // indeks pos
        }

        Node peker = start;

        for (int i = 0; i < stoerrelse(); i++) { // itererer gjennom lista til det treffer riktig posisjon
            if (pos == i) {
                peker.data = x; // ved riktig posisjon settes inn ny data
            }
            peker = peker.neste;

        }

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

    public E fjern(int pos) { // fjerner element i posisjonen pos og returnerer det
        if (pos < 0 || pos >= stoerrelse()) { // kaster UgyldigListeIndeks-exception dersom posisjonen som gis ikke
                                              // korresponderer med et element i lista
            throw new UgyldigListeindeks(pos); // indeks pos
        }

        Node peker = start;

        for (int i = 0; i < stoerrelse(); i++) { // itererer gjennom lista
            if (i == pos) { // naar korrekt posisjon treffes
                if (pos == 0) { // hvis posisjonen til elementet som skal fjernes er det foerste elementet i
                                // lista
                    start.neste.forrige = null;
                    start = peker.neste;
                    return peker.data;
                }
                if (pos == stoerrelse() - 1) { // hvis posisjonen til elementet som skal fjernes er det siste i lista
                    slutt.forrige.neste = null;
                    slutt = peker.forrige;
                    return peker.data;

                }
                if (pos < stoerrelse() - 1 && pos > 0) { // hvis elementet som skal fjernes befinner seg mellom to andre
                                                         // elementer
                    peker.forrige.neste = peker.neste;
                    peker.neste.forrige = peker.forrige;
                    return peker.data;
                }
            }
            peker = peker.neste;
        }

        return null;
    }
}