package com.github.billman64.daggerpractice;

import javax.inject.Singleton;

import dagger.Component;


@Singleton          // Annotated component acts as a bridge between dependency SharedPrefModule and MainActivity via injection interface.
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {
    void inject(MainActivity activity);
}
