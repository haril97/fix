package com.exam.android.fix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import static android.content.Context.MODE_PRIVATE;

ublic class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION = "com.exam.android.fix.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private final String COLOR_KEY = "color";

    private TextView mShowTextView;
    private int mColor;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.exam.android.fix";
    private ColorDrawable ContextCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        mShowTextView = findViewById(R.id.nmprofil);
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowTextView.setBackgroundColor(mColor);

    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void reset(View view){
        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }

    public void next(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
