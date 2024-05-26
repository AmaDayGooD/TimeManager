package com.example.timemanager.di;

import com.example.timemanager.data.local_data_base.DataBaseDao;
import com.example.timemanager.data.local_data_base.Settings;
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter;
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter_MembersInjector;
import com.example.timemanager.ui.screens.awards.AwardsPresenter;
import com.example.timemanager.ui.screens.awards.AwardsPresenter_MembersInjector;
import com.example.timemanager.ui.screens.create_task.CreateTaskPresenter;
import com.example.timemanager.ui.screens.create_task.CreateTaskPresenter_MembersInjector;
import com.example.timemanager.ui.screens.list_task.TasksPresenter;
import com.example.timemanager.ui.screens.list_task.TasksPresenter_MembersInjector;
import com.example.timemanager.ui.screens.my_awards.MyAwardsPresenter;
import com.example.timemanager.ui.screens.my_awards.MyAwardsPresenter_MembersInjector;
import com.example.timemanager.ui.screens.my_children.MyChildrenPresenter;
import com.example.timemanager.ui.screens.my_children.MyChildrenPresenter_MembersInjector;
import com.example.timemanager.ui.screens.my_task.MyTaskPresenter;
import com.example.timemanager.ui.screens.my_task.MyTaskPresenter_MembersInjector;
import com.example.timemanager.ui.screens.profile.ProfilePresenter;
import com.example.timemanager.ui.screens.profile.ProfilePresenter_MembersInjector;
import com.example.timemanager.ui.screens.registration.RegistrationPresenter;
import com.example.timemanager.ui.screens.registration.RegistrationPresenter_MembersInjector;
import com.example.timemanager.ui.screens.users_top.UsersTopPresenter;
import com.example.timemanager.ui.screens.users_top.UsersTopPresenter_MembersInjector;
import com.squareup.moshi.Moshi;
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

    private MoshiModule moshiModule;

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

    public Builder moshiModule(MoshiModule moshiModule) {
      this.moshiModule = Preconditions.checkNotNull(moshiModule);
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
      if (moshiModule == null) {
        this.moshiModule = new MoshiModule();
      }
      if (settingsModule == null) {
        this.settingsModule = new SettingsModule();
      }
      return new AppComponentImpl(contextModules, roomModules, netWorkModules, moshiModule, settingsModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final SettingsModule settingsModule;

    private final ContextModules contextModules;

    private final MoshiModule moshiModule;

    private final RoomModules roomModules;

    private final NetWorkModules netWorkModules;

    private final AppComponentImpl appComponentImpl = this;

    private AppComponentImpl(ContextModules contextModulesParam, RoomModules roomModulesParam,
        NetWorkModules netWorkModulesParam, MoshiModule moshiModuleParam,
        SettingsModule settingsModuleParam) {
      this.settingsModule = settingsModuleParam;
      this.contextModules = contextModulesParam;
      this.moshiModule = moshiModuleParam;
      this.roomModules = roomModulesParam;
      this.netWorkModules = netWorkModulesParam;

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
    public void inject(CreateTaskPresenter presenter) {
      injectCreateTaskPresenter(presenter);
    }

    @Override
    public void inject(AwardsPresenter presenter) {
      injectAwardsPresenter(presenter);
    }

    @Override
    public void inject(MyAwardsPresenter presenter) {
      injectMyAwardsPresenter(presenter);
    }

    @Override
    public void inject(UsersTopPresenter presenter) {
      injectUsersTopPresenter(presenter);
    }

    @Override
    public Moshi getMoshi() {
      return MoshiModule_ProvideMoshiFactory.provideMoshi(moshiModule);
    }

    @Override
    public DataBaseDao getRoom() {
      return RoomModules_ProvideDataBaseFactory.provideDataBase(roomModules, ContextModules_ProvideContextFactory.provideContext(contextModules));
    }

    @Override
    public Retrofit getRetrofit() {
      return NetWorkModules_ProvideRetrofitFactory.provideRetrofit(netWorkModules, MoshiModule_ProvideMoshiFactory.provideMoshi(moshiModule));
    }

    private AuthorizationPresenter injectAuthorizationPresenter(AuthorizationPresenter instance) {
      AuthorizationPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      AuthorizationPresenter_MembersInjector.injectDataBase(instance, getRoom());
      AuthorizationPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private RegistrationPresenter injectRegistrationPresenter(RegistrationPresenter instance) {
      RegistrationPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      RegistrationPresenter_MembersInjector.injectDataBase(instance, getRoom());
      RegistrationPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private ProfilePresenter injectProfilePresenter(ProfilePresenter instance) {
      ProfilePresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      ProfilePresenter_MembersInjector.injectDataBase(instance, getRoom());
      ProfilePresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private TasksPresenter injectTasksPresenter(TasksPresenter instance) {
      TasksPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      TasksPresenter_MembersInjector.injectDataBase(instance, getRoom());
      TasksPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private MyChildrenPresenter injectMyChildrenPresenter(MyChildrenPresenter instance) {
      MyChildrenPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      MyChildrenPresenter_MembersInjector.injectDataBase(instance, getRoom());
      MyChildrenPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private MyTaskPresenter injectMyTaskPresenter(MyTaskPresenter instance) {
      MyTaskPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      MyTaskPresenter_MembersInjector.injectDataBase(instance, getRoom());
      MyTaskPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private CreateTaskPresenter injectCreateTaskPresenter(CreateTaskPresenter instance) {
      CreateTaskPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      CreateTaskPresenter_MembersInjector.injectDataBase(instance, getRoom());
      CreateTaskPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private AwardsPresenter injectAwardsPresenter(AwardsPresenter instance) {
      AwardsPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      AwardsPresenter_MembersInjector.injectDataBase(instance, getRoom());
      AwardsPresenter_MembersInjector.injectSettings(instance, settings());
      AwardsPresenter_MembersInjector.injectMoshi(instance, MoshiModule_ProvideMoshiFactory.provideMoshi(moshiModule));
      return instance;
    }

    private MyAwardsPresenter injectMyAwardsPresenter(MyAwardsPresenter instance) {
      MyAwardsPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      MyAwardsPresenter_MembersInjector.injectDataBase(instance, getRoom());
      MyAwardsPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }

    private UsersTopPresenter injectUsersTopPresenter(UsersTopPresenter instance) {
      UsersTopPresenter_MembersInjector.injectRetrofit(instance, getRetrofit());
      UsersTopPresenter_MembersInjector.injectDataBase(instance, getRoom());
      UsersTopPresenter_MembersInjector.injectSettings(instance, settings());
      return instance;
    }
  }
}
