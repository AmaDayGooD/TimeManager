package com.example.timemanager.data;

import com.example.timemanager.data.local_data_base.DataBaseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlin.coroutines.CoroutineContext;
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
public final class Repository_Factory implements Factory<Repository> {
  private final Provider<CoroutineContext> coroutineContextProvider;

  private final Provider<Retrofit> retrofitProvider;

  private final Provider<DataBaseDao> dataBaseDaoProvider;

  public Repository_Factory(Provider<CoroutineContext> coroutineContextProvider,
      Provider<Retrofit> retrofitProvider, Provider<DataBaseDao> dataBaseDaoProvider) {
    this.coroutineContextProvider = coroutineContextProvider;
    this.retrofitProvider = retrofitProvider;
    this.dataBaseDaoProvider = dataBaseDaoProvider;
  }

  @Override
  public Repository get() {
    return newInstance(coroutineContextProvider.get(), retrofitProvider.get(), dataBaseDaoProvider.get());
  }

  public static Repository_Factory create(Provider<CoroutineContext> coroutineContextProvider,
      Provider<Retrofit> retrofitProvider, Provider<DataBaseDao> dataBaseDaoProvider) {
    return new Repository_Factory(coroutineContextProvider, retrofitProvider, dataBaseDaoProvider);
  }

  public static Repository newInstance(CoroutineContext coroutineContext, Retrofit retrofit,
      DataBaseDao dataBaseDao) {
    return new Repository(coroutineContext, retrofit, dataBaseDao);
  }
}
