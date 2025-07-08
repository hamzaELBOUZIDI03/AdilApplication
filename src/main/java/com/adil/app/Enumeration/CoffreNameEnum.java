package com.adil.app.Enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffreNameEnum {

    CREDIT_NAME_TABLE("السلف"),
    ADIL_NAME_TABLE("الكوفر عادل"),
    KHALIT_NAME_TABLE("الخليط"),
    LKBIR_NAME_TABLE("الكوفر لكبير"),
    ADIL2_NAME_TABLE("عادل 2"),
    ADIL3_NAME_TABLE("عادل 3");

    private final String coffreName;

}
