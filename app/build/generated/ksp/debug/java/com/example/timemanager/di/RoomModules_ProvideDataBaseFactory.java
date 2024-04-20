package com.example.timemanager.di;

import android.content.Context;
import com.example.timemanager.data.local_data_base.DataBaseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RoomModules_ProvideDataBaseFactory implements Factory<DataBaseDao> {
  private final RoomModules module;

  private final Provider<Context> contextProvider;

  public RoomModules_ProvideDataBaseFactory(RoomModules module, Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public DataBaseDao get() {
    return provideDataBase(module, contextProvider.get());
  }

  public static RoomModules_ProvideDataBaseFactory create(RoomModules module,
      Provider<Context> contextProvider) {
    return new RoomModules_ProvideDataBaseFactory(module, contextProvider);
  }

  public static DataBaseDao provideDataBase(RoomModules instance, Context context) {
    return Preconditions.checkNotNullFromProvides(instance.provideDataBase(context));
  }
}
