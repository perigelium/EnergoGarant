package developer.alexangan.ru.bidsandpersonal.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import developer.alexangan.ru.bidsandpersonal.Fragments.FragLogin;
import developer.alexangan.ru.bidsandpersonal.Interfaces.LoginCommunicator;
import developer.alexangan.ru.bidsandpersonal.R;
import developer.alexangan.ru.bidsandpersonal.ViewOverrides.BaseActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.APP_PREFERENCES;
import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.mSettings;

public class LoginActivity extends BaseActivity implements LoginCommunicator
{
    private FragmentManager mFragmentManager;

    //FragAppGuide fragAppGuide;
    FragLogin fragLogin;
    private boolean firstAppStart;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (getIntent().getBooleanExtra("Exit app", false))
        {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_window_container);

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.rootLayout);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        firstAppStart = mSettings.getBoolean("firstAppStart", true);

        //fragAppGuide = new FragAppGuide();
        fragLogin = new FragLogin();

        mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

/*        if (firstAppStart)
        {
            mFragmentTransaction.add(R.id.loginFragContainer, fragAppGuide);
            mSettings.edit().putBoolean("firstAppStart", false).apply();
        }
        else*/
        {
            mFragmentTransaction.add(R.id.loginFragContainer, fragLogin);
            mFragmentTransaction.addToBackStack(null);
        }
        mFragmentTransaction.commit();

        attachKeyboardListeners();
    }

    @Override
    public void onLoginSucceeded()
    {
        Intent registerIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void popFragmentBackStack()
    {
        mFragmentManager.popBackStack();
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        // do things when keyboard is shown

        fragLogin.setFocusOnEnterButton();
    }

    @Override
    protected void onHideKeyboard() {
        // do things when keyboard is hidden
        //bottomContainer.setVisibility(View.VISIBLE);

        //ViewUtils.showToastMessage(this, "keyboard is hidden");
    }
}




