package dev.aclam.player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import dev.aclam.player.service.ExoPlayerService;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent playerServiceIntent = new Intent(this, ExoPlayerService.class);
        startService(playerServiceIntent);
    }
}
