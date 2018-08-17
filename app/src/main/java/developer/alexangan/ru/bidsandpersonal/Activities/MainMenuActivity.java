package developer.alexangan.ru.bidsandpersonal.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants;
import developer.alexangan.ru.bidsandpersonal.Models.LoginCredentials;
import developer.alexangan.ru.bidsandpersonal.Models.ProfileInfoItem;
import developer.alexangan.ru.bidsandpersonal.R;
import developer.alexangan.ru.bidsandpersonal.Utils.ViewUtils;
import io.realm.Realm;
import io.realm.RealmResults;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.mSettings;

public class MainMenuActivity extends Activity implements View.OnClickListener, View.OnTouchListener
{
    private ProfileInfoItem profileInfoItem;

    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        TextView tvAgentName = (TextView) findViewById(R.id.tvAgentName);

/*        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ProfileInfoItem profileInfoItem = realm.where(ProfileInfoItem.class).findFirst();



        if (profileInfoItem != null)
        {
            realm.copyFromRealm(profileInfoItem);
            this.profileInfoItem = profileInfoItem;

            String agentName = this.profileInfoItem.getName() + " " + this.profileInfoItem.getSurname();*/

            String agentName = mSettings.getString("login", "");
            agentName = agentName.toUpperCase();
            tvAgentName.setText(agentName);

/*        }
        realm.commitTransaction();*/

        FrameLayout flClients = (FrameLayout) findViewById(R.id.flClients);
        FrameLayout flNews = (FrameLayout) findViewById(R.id.flNews);
        FrameLayout flBids = (FrameLayout) findViewById(R.id.flBids);
        FrameLayout flMessages = (FrameLayout) findViewById(R.id.flMessages);

        flClients.setOnClickListener(this);
        flNews.setOnClickListener(this);
        flBids.setOnClickListener(this);
        flMessages.setOnClickListener(this);

        flClients.setOnTouchListener(this);
        flNews.setOnTouchListener(this);
        flBids.setOnTouchListener(this);
        flMessages.setOnTouchListener(this);

        //realm.close();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (GlobalConstants.logoutInProgress)
        {
            GlobalConstants.logoutInProgress = false;

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults<ProfileInfoItem> profileInfoItems = realm.where(ProfileInfoItem.class).findAll();
            profileInfoItems.deleteAllFromRealm();

            RealmResults<LoginCredentials> loginCredentialses = realm.where(LoginCredentials.class).findAll();
            loginCredentialses.deleteAllFromRealm();

            realm.commitTransaction();
            realm.close();

            this.finish();
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.flClients)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));

            Intent registerIntent = new Intent(MainMenuActivity.this, StaffActivity.class);
            startActivity(registerIntent);

            //ViewUtils.showToastMessage(this, "");
        }

        if (view.getId() == R.id.flBids)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));
            Intent registerIntent = new Intent(MainMenuActivity.this, BidsActivity.class);
            startActivity(registerIntent);
        }

        if (view.getId() == R.id.flMessages)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));
            ViewUtils.showToastMessage(this, "Сообщения");
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        if (view.getId() == R.id.flClients)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        if (view.getId() == R.id.flNews)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        if (view.getId() == R.id.flBids)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        if (view.getId() == R.id.flMessages)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        return false;
    }
}
