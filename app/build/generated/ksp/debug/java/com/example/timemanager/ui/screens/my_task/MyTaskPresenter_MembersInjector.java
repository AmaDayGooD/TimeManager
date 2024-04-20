package com.example.timemanager.ui.screens.my_task;

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
public final class MyTaskPresenter_MembersInjector implements MembersInjector<MyTaskPresenter> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<DataBaseDao> dataBaseProvider;

  private final Provider<Settings> settingsProvider;

  public MyTaskPresenter_MembersInjector(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    this.retrofitProvider = retrofitProvider;
    this.dataBaseProvider = dataBaseProvider;
    this.settingsProvider = settingsProvider;
  }

  public static MembersInjector<MyTaskPresenter> create(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    return new MyTaskPresenter_MembersInjector(retrofitProvider, dataBaseProvider, settingsProvider);
  }

  @Override
  public void injectMembers(MyTaskPresenter instance) {
    injectRetrofit(instance, retrofitProvider.get());
    injectDataBase(instance, dataBaseProvider.get());
    injectSettings(instance, settingsProvider.get());
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.my_task.MyTaskPresenter.retrofit")
  public static void injectRetrofit(MyTaskPresenter instance, Retrofit retrofit) {
    instance.retrofit = retrofit;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.my_task.MyTaskPresenter.dataBase")
  public static void injectDataBase(MyTaskPresenter instance, DataBaseDao dataBase) {
    instance.dataBase = dataBase;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.my_task.MyTaskPresenter.settings")
  public static void injectSettings(MyTaskPresenter instance, Settings settings) {
    instance.settings = settings;
  }
}
