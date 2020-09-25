package com.github.billman64.daggerpractice;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module     // Dependency module marked for Dagger.
public class SharedPrefModule {
    private Context context;

    public SharedPrefModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides   // Method provided for injection.
    public Context provideContext(){
        return context;
    }

    @Singleton
    @Provides   // Method provided for injection.
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
