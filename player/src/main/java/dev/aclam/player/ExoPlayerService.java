package dev.aclam.mediaplayer2;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.concurrent.TimeUnit;

/**
 * Android framework {@link Service} implementation of {@link PlayerService}
 */
public class ExoPlayerService extends Service implements PlayerService {

    private static final String TAG = ExoPlayerService.class.getName();

    private PlaybackStateListener playbackStateListener;
    private SimpleExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;

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
    public void onCreate() {
        super.onCreate();
        setupPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cleanupPlayer();
    }

    @Override
    public void close() {
        stopForeground(true);
        Intent stopServiceIntent = new Intent(this, ExoPlayerService.class);
        stopService(stopServiceIntent);
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
    public void forward() {
        forward(10, TimeUnit.SECONDS);
    }

    @Override
    public void forward(int amount, TimeUnit timeUnit) {
        long currentPosition = player.getCurrentPosition();
        player.seekTo(currentWindow, currentPosition + timeUnit.toMillis(amount));
    }

    @Override
    public void replay() {
        replay(10, TimeUnit.SECONDS);
    }

    @Override
    public void replay(int amount, TimeUnit timeUnit) {
        long currentPosition = player.getCurrentPosition();
        player.seekTo(currentWindow, currentPosition - timeUnit.toMillis(amount));
    }

    @Override
    public void next() {
        player.next();
    }

    @Override
    public void previous() {
        player.previous();
    }

    /**
     * Synchronously builds a {@link ExoPlayer} instance, should be called when service is
     * created.
     */
    private void setupPlayer() {
        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxAudioBitrate(128000));
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        }

        playbackStateListener = new PlaybackStateListener();
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.addListener(playbackStateListener);
    }

    /**
     * Synchronously cleans up a {@link ExoPlayer} instance, should be called when service is
     * destroyed.
     */
    private void cleanupPlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.removeListener(playbackStateListener);
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        // TODO what is a userAgent in this context?
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "toHear");
        DashMediaSource.Factory mediaSourceFactory = new DashMediaSource.Factory(dataSourceFactory);
        return mediaSourceFactory.createMediaSource(uri);
    }

    private static class PlaybackStateListener implements Player.EventListener {

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d(TAG, "changed state to " + stateString
                    + " playWhenReady: " + playWhenReady);
        }
    }

    public class PlayerServiceBinder extends Binder {
        PlayerService getService() {
            return ExoPlayerService.this;
        }
    }
}
