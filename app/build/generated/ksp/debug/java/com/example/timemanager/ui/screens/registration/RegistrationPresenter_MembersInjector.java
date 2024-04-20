package com.example.timemanager.ui.screens.registration;

import com.example.timemanager.data.local_data_base.DataBaseDao;
import com.example.timemanager.data.local_data_base.Settings;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class RegistrationPresenter_MembersInjector implements MembersInjector<RegistrationPresenter> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<DataBaseDao> dataBaseProvider;

  private final Provider<Settings> settingsProvider;

  public RegistrationPresenter_MembersInjector(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    this.retrofitProvider = retrofitProvider;
    this.dataBaseProvider = dataBaseProvider;
    this.settingsProvider = settingsProvider;
  }

  public static MembersInjector<RegistrationPresenter> create(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    return new RegistrationPresenter_MembersInjector(retrofitProvider, dataBaseProvider, settingsProvider);
  }

  @Override
  public void injectMembers(RegistrationPresenter instance) {
    injectRetrofit(instance, retrofitProvider.get());
    injectDataBase(instance, dataBaseProvider.get());
    injectSettings(instance, settingsProvider.get());
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.registration.RegistrationPresenter.retrofit")
  public static void injectRetrofit(RegistrationPresenter instance, Retrofit retrofit) {
    instance.retrofit = retrofit;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.registration.RegistrationPresenter.dataBase")
  public static void injectDataBase(RegistrationPresenter instance, DataBaseDao dataBase) {
    instance.dataBase = dataBase;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.registration.RegistrationPresenter.settings")
  public static void injectSettings(RegistrationPresenter instance, Settings settings) {
    instance.settings = settings;
  }
}
