package com.techieonthenet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pie {
    private String name;
    private Integer y;

    public Pie(String s, Integer v) {
        this.name = s;
        this.y = v;
    }
    // Add/generate c'tors/getters/setters/equals/hashCode.
}