package com.hexaware.vag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import com.hexaware.vag.entity.Artwork;
import com.hexaware.vag.util.DBConnection;

public class VirtualArtGalleryImplemenataion implements IVirtualArtGallery{
	
	private Connection connection;
	
	public VirtualArtGalleryImplemenataion() throws ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

	@Override
	public boolean addArtwork(Artwork artwork) {
		// TODO Auto-generated method stub
		try {
            String query = "INSERT INTO Artwork (title, description, creationDate, medium, imageURL, artistId) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setString(3, artwork.getCreationDate());
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageUrl());
            ps.setInt(6, artwork.getArtistId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}	

	@Override
	public boolean updateArtwork(Artwork artwork) {
		// TODO Auto-generated method stub
		 try {
	            String query = "UPDATE Artwork SET title = ?, description = ?, creationDate = ?, medium = ?, imageURL = ? WHERE artworkID = ?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, artwork.getTitle());
	            ps.setString(2, artwork.getDescription());
	            ps.setString(3, artwork.getCreationDate());
	            ps.setString(4, artwork.getMedium());
	            ps.setString(5, artwork.getImageUrl());
	            ps.setInt(6, artwork.getArtistId());
	            return ps.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public boolean removeArtwork(int artworkID) {
		// TODO Auto-generated method stub
		try {
            String query = "DELETE FROM Artwork WHERE artworkID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, artworkID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public Artwork getArtworkById(int artworkID) {
		// TODO Auto-generated method stub
		 try {
	            String query = "SELECT * FROM Artwork WHERE artworkID = ?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, artworkID);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                return new Artwork(rs.getInt("artworkID"), rs.getString("title"), rs.getString("description"), rs.getString("creationDate"), rs.getString("medium"), rs.getString("imageURL"), rs.getInt("artistID"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	}

	@Override
	public List<Artwork> searchArtworks(String keyword) {
		// TODO Auto-generated method stub
		List<Artwork> artworks = new ArrayList<>();
		try {
            String query = "SELECT * FROM Artwork WHERE title LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                artworks.add(new Artwork(rs.getInt("artworkID"), rs.getString("title"), rs.getString("description"), rs.getString("creationDate"), rs.getString("medium"), rs.getString("imageURL"), rs.getInt("artistID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return artworks;
	}

	@Override
	public boolean addArtworkToFavorite(int userId, int artworkId) {
		// TODO Auto-generated method stub
		try {
            String query = "INSERT INTO User_Favorite_Artwork (userID, artworkID) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean removeArtworkFromFavorite(int userId, int artworkId) {
		// TODO Auto-generated method stub
		try {
            String query = "DELETE FROM User_Favorite_Artwork WHERE userID = ? AND artworkID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<Artwork> getUserFavoriteArtworks(int userId) {
		// TODO Auto-generated method stub
		List<Artwork> artworks = new ArrayList<>();
        try {
            String query = "SELECT a.* FROM Artwork a JOIN User_Favorite_Artwork ufa ON a.artworkID = ufa.artworkID WHERE ufa.userID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                artworks.add(new Artwork(rs.getInt("artworkID"), rs.getString("title"), rs.getString("description"), rs.getString("creationDate"), rs.getString("medium"), rs.getString("imageURL"), rs.getInt("artistID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
		return null;
	}

}
