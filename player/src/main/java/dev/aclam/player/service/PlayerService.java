package dev.aclam.player.service;

import java.util.concurrent.TimeUnit;

public interface PlayerService {
    /**
     * Closes this instance of {@link PlayerService}
     */
    void close();

    /**
     * Commands {@link PlayerService} to play or resume last known media
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
     * Forward the media playback by 10 seconds
     */
    void forward();

    /**
     * Replay the media playback by 10 seconds
     */
    void replay();

    /**
     * Forward the current media playback by the given amount of time,
     * the amount of time forwarded will be capped at the last position
     * of media playback.
     *
     * @param amount amount of time
     * @param timeUnit a {@link TimeUnit}
     */
    void forward(int amount, TimeUnit timeUnit);

    /**
     * Replay the current media playback by the given amount of time,
     * the amount of time rewind will be capped at the first position
     * of media playback.
     *
     * @param amount amount of time
     * @param timeUnit a {@link TimeUnit}
     */
    void replay(int amount, TimeUnit timeUnit);
}
