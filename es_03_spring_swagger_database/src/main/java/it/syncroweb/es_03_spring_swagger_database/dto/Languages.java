package it.syncroweb.es_03_spring_swagger_database.dto;

public enum Languages {

    ES ("Spanish"),
    DE ("German"),
    FR("France"),
    IT("Italian"),
    ZH_HANS("Chinese (Simplified)"),
    ZH_HANT("Chinese (Traditional)");
    private final String name;

    Languages(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getNameOrDefault(String languageCode) {
        try {
            return Languages.valueOf(languageCode.toUpperCase()).getName();
        } catch (IllegalArgumentException e) {
            return "English";
        }
    }
}
