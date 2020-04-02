package dev.aclam.mediaplayer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent playerServiceIntent = new Intent(this, ExoPlayerService.class);
        startService(playerServiceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent playerServiceIntent = new Intent(this, ExoPlayerService.class);
        stopService(playerServiceIntent);
    }
}
