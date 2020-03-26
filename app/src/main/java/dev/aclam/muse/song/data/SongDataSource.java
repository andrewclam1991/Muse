package dev.aclam.muse.song.data;

import java.util.List;

import dev.aclam.muse.song.Song;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface SongDataSource {
    Completable addSong(Song song);

    Single<Song> getSong(String uuid);

    Maybe<List<Song>> getSongsByTitle(String title);

    Completable updateSong(Song song);

    Completable removeSong(Song song);

    Completable removeAllSongs();
}
