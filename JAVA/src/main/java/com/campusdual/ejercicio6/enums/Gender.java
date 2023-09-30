package com.campusdual.ejercicio6.enums;

public enum Gender {
    FEMALE,
    MALE;

    public static Gender getByString(String genderName) {
        if ("m".equalsIgnoreCase(genderName.trim()) || "mujer".equalsIgnoreCase(genderName.trim())
        || "FEMALE".equalsIgnoreCase(genderName.trim())) {
            return Gender.FEMALE;
        } else if ("h".equalsIgnoreCase(genderName.trim()) || "hombre".equalsIgnoreCase(genderName.trim())
                || "MALE".equalsIgnoreCase(genderName.trim())) {
            return Gender.MALE;
        } else {
            return null;
        }
    }
}
