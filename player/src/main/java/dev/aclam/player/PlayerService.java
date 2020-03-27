package dev.aclam.player;

public interface PlayerService {
    void play();
    void pause();
    void stop();
    void next();
    void previous();
    void seekTo(int position);
}
