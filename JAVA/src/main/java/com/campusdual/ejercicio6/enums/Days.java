package com.campusdual.ejercicio6.enums;

public enum Days {
    L(1, "Lunes"),
    M(2, "Martes"),
    X(3, "Miercoles"),
    J(4, "Jueves"),
    V(5, "Viernes"),
    S(6, "Sabado"),
    D(7, "Domingo");

    private final Integer position;
    private final String name;

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    Days(Integer position, String name) {
        this.position = position;
        this.name = name;
    }

    public static Days getByPosition(Integer position) {
        for (Days day:values()) {
            if (day.position == position) {
                return day;
            }
        }
        return null;
    }


}
