package com.example.module;

import com.example.annotations.InjectColor;
import com.example.requests.SquareRequest;
import com.example.services.DrawShape;
import com.example.services.DrawSquare;
import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DrawShape.class).to(DrawSquare.class).in(Scopes.SINGLETON);
        bind(String.class).annotatedWith(InjectColor.class).toInstance("RED");
        bind(Integer.class).toInstance(40);
        bind(SquareRequest.class).in(Scopes.SINGLETON);
    }
}
