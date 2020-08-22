package br.com.projetofinal.centraldeerros.enums;

public enum Level {

    ERROR("ERROR"), WARNING("WARNING"), DEBUG("DEBUG");

    private String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static Level find(String value) {
        for (Level level : Level.values()) {
            if(value.equalsIgnoreCase((level.level)))
                return level;
        }
        return null;
    }

}
