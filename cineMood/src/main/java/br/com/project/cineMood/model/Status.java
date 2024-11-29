package br.com.project.cineMood.model;

public enum Status {
    Assistido,
    Assistindo,
    Quero_Assistir;

    public static Status fromString(String status) {
        for (Status s : Status.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null; // Ou lançar uma exceção, se preferir
    }
}


