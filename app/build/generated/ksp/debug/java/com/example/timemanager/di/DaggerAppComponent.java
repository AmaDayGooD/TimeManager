package com.example.timemanager.di;

import com.example.timemanager.data.local_data_base.DataBaseDao;
import com.example.timemanager.data.local_data_base.Settings;
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter;
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter_MembersInjector;
import com.example.timemanager.ui.screens.list_task.TasksPresenter;
import com.example.timemanager.ui.screens.list_task.TasksPresenter_MembersInjector;
import com.example.timemanager.ui.screens.my_children.MyChildrenPresenter;
import com.example.timemanager.ui.screens.my_children.MyChildrenPresenter_MembersInjector;
import com.example.timemanager.ui.screens.my_task.MyTaskPresenter;
import com.example.timemanager.ui.screens.my_task.MyTaskPresenter_MembersInjector;
import com.example.timemanager.ui.screens.profile.ProfilePresenter;
import com.example.timemanager.ui.screens.profile.ProfilePresenter_MembersInjector;
import com.example.timemanager.ui.screens.registration.RegistrationPresenter;
import com.example.timemanager.ui.screens.registration.RegistrationPresenter_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ContextModules contextModules;

    private RoomModules roomModules;

    private NetWorkModules netWorkModules;

    private SettingsModule settingsModule;

    private Builder() {
    }

    public Builder contextModules(ContextModules contextModules) {
      this.contextModules = Preconditions.checkNotNull(contextModules);
      return this;
    }

    public Builder roomModules(RoomModules roomModules) {
      this.roomModules = Preconditions.checkNotNull(roomModules);
      return this;
    }

    public Builder netWorkModules(NetWorkModules netWorkModules) {
      this.netWorkModules = Preconditions.checkNotNull(netWorkModules);
      return this;
    }

    public Builder settingsModule(SettingsModule settingsModule) {
      this.settingsModule = Preconditions.checkNotNull(settingsModule);
      return this;
    }

    public AppComponent build() {
      Preconditions.checkBuilderRequirement(contextModules, ContextModules.class);
      if (roomModules == null) {
        this.roomModules = new RoomModules();
      }
      if (netWorkModules == null) {
        this.netWorkModules = new NetWorkModules();
      }
      if (settingsModule == null) {
        this.settingsModule = new SettingsModule();
      }
      return new AppComponentImpl(contextModules, roomModules, netWorkModules, settingsModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final NetWorkModules netWorkModules;

    private final SettingsModule settingsModule;

    private final ContextModules contextModules;

    private final RoomModules roomModules;

    private final AppComponentImpl appComponentImpl = this;

    private AppComponentImpl(ContextModules contextModulesParam, RoomModules roomModulesParam,
        NetWorkModules netWorkModulesParam, SettingsModule settingsModuleParam) {
      this.netWorkModules = netWorkModulesParam;
      this.settingsModule = settingsModuleParam;
      this.contextModules = contextModulesParam;
      this.roomModules = roomModulesParam;

    }

    private Settings settings() {
      return SettingsModule_ProvideSettingsFactory.provideSettings(settingsModule, ContextModules_ProvideContextFactory.provideContext(contextModules));
    }

    @Override
    public void inject(AuthorizationPresenter presenter) {
      injectAuthorizationPresenter(presenter);
    }

    @Override
    public void inject(RegistrationPresenter presenter) {
      injectRegistrationPresenter(presenter);
    }

    @Override
    public void inject(ProfilePresenter presenter) {
      injectProfilePresenter(presenter);
    }

    @Override
    public void inject(TasksPresenter presenter) {
      injectTasksPresenter(presenter);
    }

    @Override
    public void inject(MyChildrenPresenter presenter) {
      injectMyChildrenPresenter(presenter);
    }

    @Override
    public void inject(MyTaskPresenter presenter) {
      injectMyTaskPresenter(presenter);
    }

    @Override
    public DataBaseDao getRoom() {
      return RoomModules_ProvideDataBaseFactory.provideDataBase(roomModules, ContextModules_ProvideContextFactory.provideContext(contextModules));
    }

    @Override
    public Retrofit getRetrofit() {
      return NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules);
    }

    private AuthorizationPresenter injectAuthorizationPresenter(AuthorizationPresenter instance) {
      AuthorizationPresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      AuthorizationPresenter_MembersInjector.injectDataBase(instance, getRoom());
      AuthorizationPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private RegistrationPresenter injectRegistrationPresenter(RegistrationPresenter instance) {
      RegistrationPresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      RegistrationPresenter_MembersInjector.injectDataBase(instance, getRoom());
      RegistrationPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private ProfilePresenter injectProfilePresenter(ProfilePresenter instance) {
      ProfilePresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      ProfilePresenter_MembersInjector.injectDataBase(instance, getRoom());
      ProfilePresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private TasksPresenter injectTasksPresenter(TasksPresenter instance) {
      TasksPresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      TasksPresenter_MembersInjector.injectDataBase(instance, getRoom());
      TasksPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private MyChildrenPresenter injectMyChildrenPresenter(MyChildrenPresenter instance) {
      MyChildrenPresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      MyChildrenPresenter_MembersInjector.injectDataBase(instance, getRoom());
      MyChildrenPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private MyTaskPresenter injectMyTaskPresenter(MyTaskPresenter instance) {
      MyTaskPresenter_MembersInjector.injectRetrofit(instance, NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules));
      MyTaskPresenter_MembersInjector.injectDataBase(instance, getRoom());
      MyTaskPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }
  }
}
