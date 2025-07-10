package com.adil.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "adil_3")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Adil3 extends SharedField {

}
