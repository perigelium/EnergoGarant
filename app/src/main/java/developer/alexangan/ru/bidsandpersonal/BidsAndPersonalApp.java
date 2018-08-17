package developer.alexangan.ru.bidsandpersonal;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BidsAndPersonalApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SF_UI_Text_Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        //GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
    }
}
