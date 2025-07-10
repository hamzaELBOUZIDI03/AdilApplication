package com.adil.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "lkbir")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Lkbir extends SharedField {

}
