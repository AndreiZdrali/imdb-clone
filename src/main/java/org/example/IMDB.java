package org.example;

public class IMDB {
    // TODO: Sa adaug membrii clasei dupa ce ii implementez

    private static IMDB instance = null;

    private IMDB() {
        // CONSTRUCTOR
    }

    public static IMDB getInstance() {
        if (instance == null)
            instance = new IMDB();
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
