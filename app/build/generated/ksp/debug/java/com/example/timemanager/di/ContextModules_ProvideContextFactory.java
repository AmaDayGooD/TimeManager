package com.example.timemanager.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ContextModules_ProvideContextFactory implements Factory<Context> {
  private final ContextModules module;

  public ContextModules_ProvideContextFactory(ContextModules module) {
    this.module = module;
  }

  @Override
  public Context get() {
    return provideContext(module);
  }

  public static ContextModules_ProvideContextFactory create(ContextModules module) {
    return new ContextModules_ProvideContextFactory(module);
  }

  public static Context provideContext(ContextModules instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideContext());
  }
}
