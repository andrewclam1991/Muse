package dev.aclam.tohear.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import dev.aclam.player.PlayerActivity;
import dev.aclam.tohear.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent playerIntent = new Intent(this, PlayerActivity.class);
        startActivity(playerIntent);
    }
}
