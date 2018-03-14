package edu.mdc.entec.north.arttracker.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArtPieceDao {
    @Query("select * from ArtPiece where name = :name")
    ArtPiece loadArtPieceByName(String name);

    @Query("SELECT * FROM ArtPiece")
    List<ArtPiece> findAllArtPiecesSync();

    @Insert(onConflict = IGNORE)
    void insertArtPiece(ArtPiece artPiece);

    @Update(onConflict = REPLACE)
    void updateArtPiece(ArtPiece artPiece);

    @Query("DELETE FROM ArtPiece")
    void deleteAll();

}
