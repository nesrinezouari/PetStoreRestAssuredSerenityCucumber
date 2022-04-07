package models.pet;

import java.util.List;
import java.util.Objects;

import io.restassured.response.Response;

public class Pets {
    private List<Pet> pets;

    public Pets(Response petsResponse) {
    }

    public Pets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pets pets = (Pets) o;
        return Objects.equals(getPets(), pets.pets);
    }

}
