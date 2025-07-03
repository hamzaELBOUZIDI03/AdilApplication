package com.adil.app.Enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffreNameEnum {

    CREDIT_NAME_TABLE("السلف"),
    ADIL_NAME_TABLE("الكوفر عادل"),
    KHALIT_NAME_TABLE("الخليط"),
    LKBIR_NAME_TABLE("الكوفر لكبير");

    private final String coffreName;

}
