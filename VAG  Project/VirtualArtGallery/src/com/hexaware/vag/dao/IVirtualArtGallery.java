package com.hexaware.vag.dao;

import com.hexaware.vag.entity.Artwork;
import java.util.List;

public interface IVirtualArtGallery {

    // Artwork Management
    boolean addArtwork(Artwork artwork);
    boolean updateArtwork(Artwork artwork);
    boolean removeArtwork(int artworkID);
    Artwork getArtworkById(int artworkID);
    List<Artwork> searchArtworks(String keyword);

    // User Favorites
    boolean addArtworkToFavorite(int userId, int artworkId);
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    List<Artwork> getUserFavoriteArtworks(int userId);
}