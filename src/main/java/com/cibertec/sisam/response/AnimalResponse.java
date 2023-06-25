package com.cibertec.sisam.response;

import com.cibertec.sisam.model.Animal;
import lombok.Data;

import java.util.List;

@Data
public class AnimalResponse {

    List<Animal> animales;
}
