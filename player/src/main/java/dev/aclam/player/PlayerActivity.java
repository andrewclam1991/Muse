package dev.aclam.player;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.google.android.exoplayer2.ui.PlayerView;

import dev.aclam.player.service.ExoPlayerService;
import dev.aclam.player.service.PlayerService;

public class PlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private PlayerService playerService;
    private final ServiceConnection playerServiceConnection;
    private boolean isPlayerServiceBound;

    public PlayerActivity(){
        playerServiceConnection = getPlayerServiceConnection();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent playerServiceIntent = new Intent(this, ExoPlayerService.class);
        startService(playerServiceIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ExoPlayerService.class);
        bindService(intent, playerServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUi();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(playerServiceConnection);
        isPlayerServiceBound = false;
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * Instantiates a {@link ServiceConnection} to {@link ExoPlayerService}
     * @return
     */
    private ServiceConnection getPlayerServiceConnection(){
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                ExoPlayerService.PlayerServiceBinder binder = (ExoPlayerService.PlayerServiceBinder) service;
                playerService = binder.getService();
                isPlayerServiceBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isPlayerServiceBound = false;
            }
        };
    }
}
