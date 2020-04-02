package dev.aclam.tohear.song.data;

import java.util.List;

import dev.aclam.tohear.song.Song;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

/**
 * Data access layer API for {@link Song}
 */
public interface SongDataSource {
    Completable addSong(Song song);

    Single<Song> getSong(String uuid);

    Maybe<List<Song>> getSongsByTitle(String title);

    Completable updateSong(Song song);

    Completable removeSong(Song song);

    Completable removeAllSongs();
}
