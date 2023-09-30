package com.campusdual.ejemplos.alimentos;

public class Food {
    private Integer carbos;
    private Integer fats;
    private Integer proteins;

    public Food() {
        this.carbos = 0;
        this.fats = 0;
        this.proteins = 0;
    }

    public Food(Integer carbos, Integer fats, Integer proteins) {
        this.carbos = carbos;
        this.fats = fats;
        this.proteins = proteins;
    }

    public Integer getCalories(Integer weight) {
        return ((carbos*4) + (fats*9) + (proteins*4));
    }

    public Integer getCarbos() {
        return carbos;
    }

    public void setCarbos(Integer carbos) {
        this.carbos = carbos;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }
}
