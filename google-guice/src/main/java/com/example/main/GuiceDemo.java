package com.example.main;

import com.example.module.AppModule;
import com.example.requests.SquareRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceDemo {

    public static void main(String[] args) {
        //DrawShape d = new DrawSquare();
        Injector  injector = Guice.createInjector(new AppModule());
        //DrawShape d = injector.getInstance(DrawShape.class);
        //SquareRequest request = new SquareRequest(d);
        SquareRequest request = injector.getInstance(SquareRequest.class);
        request.makeRequest();
    }

}
