package pl.foodfront.communication;

/**
 * Created by user on 2016-01-29.
 */
enum eFunctions {

    LOGIN("login"),             // funkcja logowania
    GET_SPOTS("getSpots"),      // funkcja pobierania listy lokali
    GET_SPOT("getSpot");        // funkcja pobierania informacji o konkretnym lokalu

    private String name = "";

    eFunctions(final String name) { this.name = name; }

    public String toString() { return name; }
}
