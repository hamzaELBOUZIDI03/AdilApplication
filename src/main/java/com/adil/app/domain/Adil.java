package com.adil.app.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "adil")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Adil extends SharedField {

}
