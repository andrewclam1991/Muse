package dev.aclam.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;

public class ExoPlayerService extends Service implements PlayerService {

    private SimpleExoPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // stop foreground service
        return null;
    }

    @Override
    public void onRebind(Intent intent) {
        // stop foreground service
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // start foreground service
        return super.onUnbind(intent);
    }

    @Override
    public void play() {
        player.setPlayWhenReady(true);
    }

    @Override
    public void pause() {
        player.stop();
    }

    @Override
    public void stop() {
        player.stop(true);
    }

    @Override
    public void next() {
        player.next();
    }

    @Override
    public void previous() {
        player.previous();
    }

    @Override
    public void seekTo(int position) {
        player.seekTo(0, position);
    }

    /**
     * Initializes a
     */
    private void initializePlayer() {
        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd());
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        }
    }

    public class PlayerServiceBinder extends Binder {
        PlayerService getService() {
            return ExoPlayerService.this;
        }
    }
}
