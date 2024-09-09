class Stabel<E> extends Lenkeliste<E> { // LIFO

    @Override
    public void leggTil(E x) { // nytt element legge forst i lista
        Node nyNode = new Node(x);
        if (slutt == null) { // hvis lista er tom legges elementet til i lista
            super.leggTil(x);
            return;
        }
        start.forrige = nyNode; // om lista IKKE er tom settes start referansen til det nye elementet og riktige
                                // referanser settes mellom det nye elementet og det neste
        nyNode.neste = start;
        start = nyNode;
    }

}