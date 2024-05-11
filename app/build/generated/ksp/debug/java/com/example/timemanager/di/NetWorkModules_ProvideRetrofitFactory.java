package com.example.timemanager.di;

import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
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

  private final Provider<Moshi> moshiProvider;

  public NetWorkModules_ProvideRetrofitFactory(NetWorkModules module,
      Provider<Moshi> moshiProvider) {
    this.module = module;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(module, moshiProvider.get());
  }

  public static NetWorkModules_ProvideRetrofitFactory create(NetWorkModules module,
      Provider<Moshi> moshiProvider) {
    return new NetWorkModules_ProvideRetrofitFactory(module, moshiProvider);
  }

  public static Retrofit provideRetrofit(NetWorkModules instance, Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(instance.provideRetrofit(moshi));
  }
}
