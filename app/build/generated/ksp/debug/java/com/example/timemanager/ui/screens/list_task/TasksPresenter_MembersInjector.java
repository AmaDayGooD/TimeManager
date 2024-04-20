package com.example.timemanager.ui.screens.list_task;

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
public final class TasksPresenter_MembersInjector implements MembersInjector<TasksPresenter> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<DataBaseDao> dataBaseProvider;

  private final Provider<Settings> settingsProvider;

  public TasksPresenter_MembersInjector(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    this.retrofitProvider = retrofitProvider;
    this.dataBaseProvider = dataBaseProvider;
    this.settingsProvider = settingsProvider;
  }

  public static MembersInjector<TasksPresenter> create(Provider<Retrofit> retrofitProvider,
      Provider<DataBaseDao> dataBaseProvider, Provider<Settings> settingsProvider) {
    return new TasksPresenter_MembersInjector(retrofitProvider, dataBaseProvider, settingsProvider);
  }

  @Override
  public void injectMembers(TasksPresenter instance) {
    injectRetrofit(instance, retrofitProvider.get());
    injectDataBase(instance, dataBaseProvider.get());
    injectSettings(instance, settingsProvider.get());
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.list_task.TasksPresenter.retrofit")
  public static void injectRetrofit(TasksPresenter instance, Retrofit retrofit) {
    instance.retrofit = retrofit;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.list_task.TasksPresenter.dataBase")
  public static void injectDataBase(TasksPresenter instance, DataBaseDao dataBase) {
    instance.dataBase = dataBase;
  }

  @InjectedFieldSignature("com.example.timemanager.ui.screens.list_task.TasksPresenter.settings")
  public static void injectSettings(TasksPresenter instance, Settings settings) {
    instance.settings = settings;
  }
}
