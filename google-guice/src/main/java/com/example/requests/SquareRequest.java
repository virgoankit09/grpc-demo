package com.example.requests;

import com.example.services.DrawShape;
import com.google.inject.Inject;

public class SquareRequest {

    DrawShape shape;

    @Inject
    public SquareRequest(DrawShape shape) {
        this.shape = shape;
    }

    public void makeRequest() {
        shape.draw();
    }
}
