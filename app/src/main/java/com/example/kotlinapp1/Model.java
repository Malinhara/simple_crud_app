package com.example.kotlinapp1;

public class Model {

    private int id;
    private String name;
    private String Deec;





    public Model(int id, String name, String deec) {
        this.id = id;
        this.name = name;
        Deec = deec;
    }



    public Model(int id) {
        this.id=id;
    }



    public Model() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeec() {
        return Deec;
    }

    public void setDeec(String deec) {
        Deec = deec;
    }
}

