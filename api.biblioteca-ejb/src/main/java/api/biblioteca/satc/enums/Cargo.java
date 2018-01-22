package api.biblioteca.satc.enums;

public enum Cargo {

    DIRETOR("DIRETOR"), BIBLIOTECARIO("BIBLIOTECARIO"), ATENDENTE("ATENDENTE");

    private String cargo;

    Cargo(String cargo) {
        this.cargo = cargo;
    }
    public String getValue() {
        return cargo;
    }
    public static Cargo byValue(String cargo) throws IllegalArgumentException{
        for (Cargo c : Cargo.values()) {
            if (c.getValue().equals(cargo)) { return c; }
        }
        throw new IllegalArgumentException("Cargo ".concat( cargo ).concat(" não conhecido!"));
    }
}
