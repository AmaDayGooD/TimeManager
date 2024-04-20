package com.example.timemanager.ui.screens.profile;

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
public final class ProfilePresenter_MembersInjector implements MembersInjector<ProfilePresenter> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<DataBaseDao> dataBaseProvider;

  private final Provider<Settings> settingsProvider;

  public ProfilePresenter_MembersInjector(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    this.retrofitProvider = retrofitProvider;
    this.dataBaseProvider = dataBaseProvider;
    this.settingsProvider = settingsProvider;
  }

  public static MembersInjector<ProfilePresenter> create(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    return new ProfilePresenter_MembersInjector(retrofitProvider, dataBaseProvider, settingsProvider);
  }

  @Override
  public void injectMembers(ProfilePresenter instance) {
    injectRetrofit(instance, retrofitProvider.get());
    injectDataBase(instance, dataBaseProvider.get());
    injectSettings(instance, settingsProvider.get());
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.profile.ProfilePresenter.retrofit")
  public static void injectRetrofit(ProfilePresenter instance, Retrofit retrofit) {
    instance.retrofit = retrofit;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.profile.ProfilePresenter.dataBase")
  public static void injectDataBase(ProfilePresenter instance, DataBaseDao dataBase) {
    instance.dataBase = dataBase;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.profile.ProfilePresenter.settings")
  public static void injectSettings(ProfilePresenter instance, Settings settings) {
    instance.settings = settings;
  }
}
