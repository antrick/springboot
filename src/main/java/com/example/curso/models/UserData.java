package com.example.curso.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class UserData {
    public String name;
    public int age;
    @JsonIgnore //evitar que se envie en la estructura json el atributo, en este caso country
    public String country;
    @JsonProperty("ciudad") // renombra una propiedad dentro del json que devuelve de city pasa a llamarse ciudad
    public String city;

//    @JsonValue
//    public String info(){ // jsonvalue reemplaza todas las anotaciones y regresa lo que ingreses dentro de esta funcion en este caso una cadena
//        return "El usuario es: "+name+" de edad: "+age+" y vive en: "+city+" - "+country;
//    }

    @JsonGetter("info")
    public String info(){ // JsonGetter agrega una nueva llave a la estructura json que devuelve es decir regresara todos los atributos mas el extra llamado info
        return "El usuario es: "+name+" de edad: "+age+" y vive en: "+city+" - "+country;
    }

    public UserData(String name, int age, String country, String city){
        this.name = name;
        this.age = age;
        this.country = country;
        this.city = city;
    }
}
