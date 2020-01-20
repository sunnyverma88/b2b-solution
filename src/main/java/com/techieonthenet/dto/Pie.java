package com.techieonthenet.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Pie.
 */
@Getter
@Setter
public class Pie {
    private String name;
    private Integer y;

    /**
     * Instantiates a new Pie.
     *
     * @param s the s
     * @param v the v
     */
    public Pie(String s, Integer v) {
        this.name = s;
        this.y = v;
    }
    // Add/generate c'tors/getters/setters/equals/hashCode.
}