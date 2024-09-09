interface Liste <E> extends Iterable<E> {
    int stoerrelse();    // returnerer ant elementer i lista
    void leggTil(E x);  // legger til nytt element sist i lista
    E hent();    // returnerer referanse til element i pos
    E fjern();          // fjerner og returnerer element i pos
}