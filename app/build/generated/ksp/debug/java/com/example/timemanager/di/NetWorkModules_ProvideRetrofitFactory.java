package com.example.timemanager.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class NetWorkModules_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final NetWorkModules module;

  public NetWorkModules_ProvideRetrofitFactory(NetWorkModules module) {
    this.module = module;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(module);
  }

  public static NetWorkModules_ProvideRetrofitFactory create(NetWorkModules module) {
    return new NetWorkModules_ProvideRetrofitFactory(module);
  }

  public static Retrofit provideRetrofit(NetWorkModules instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideRetrofit());
  }
}
