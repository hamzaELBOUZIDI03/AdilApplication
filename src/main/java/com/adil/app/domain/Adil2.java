package com.adil.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "adil_2")
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class Adil2 extends SharedField {

}
