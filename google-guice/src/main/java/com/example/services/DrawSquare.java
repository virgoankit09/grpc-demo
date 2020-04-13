package com.example.services;

import com.example.annotations.InjectColor;
import com.google.inject.Inject;

public class DrawSquare implements DrawShape {

    private String color;
    private Integer edge;

    @Inject
    public DrawSquare(@InjectColor String color, Integer edge) {
        this.color = color;
        this.edge = edge;
    }

    public void draw() {
        System.out.println("Drawing Square. Color:"+color+". Edge:"+edge);
    }
}
