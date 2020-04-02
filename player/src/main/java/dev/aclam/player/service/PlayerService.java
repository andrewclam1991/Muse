package dev.aclam.player.service;

import java.util.concurrent.TimeUnit;

public interface PlayerService {
    /**
     * Closes this instance of {@link PlayerService}
     */
    void close();

    /**
     * Commands {@link PlayerService} to play or resume media playback
     */
    void play();

    /**
     * Commands {@link PlayerService} to pause media playback
     */
    void pause();

    /**
     * Plays the next media source, if available
     */
    void next();

    /**
     * Plays the previous media source, if available
     */
    void previous();

    /**
     * Forward playback by 10 seconds, or until the end of media playback,
     * whichever is earlier.
     */
    void forward();

    /**
     * Replay media by 10 seconds, or until the start of media playback
     */
    void replay();

    /**
     * Set the playback forward by the given amount of time, capped until the end of media
     * playback.
     *
     * @param amount amount of time
     * @param timeUnit a {@link TimeUnit}
     */
    void forward(int amount, TimeUnit timeUnit);

    /**
     * Set the playback back by the given amount of time, capped until the start of
     * media playback.
     *
     * @param amount amount of time
     * @param timeUnit a {@link TimeUnit}
     */
    void replay(int amount, TimeUnit timeUnit);
}
