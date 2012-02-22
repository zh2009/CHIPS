package com.chips;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public abstract class HomeBarActivity extends Activity {
    public void goHomeClicked(View view) {
        Intent applicationHubActivityIntent 
            = new Intent(this, ApplicationHubActivity.class);
        applicationHubActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(applicationHubActivityIntent);
    }
}
