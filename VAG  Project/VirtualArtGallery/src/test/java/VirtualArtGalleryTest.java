package test.java;

import com.hexaware.vag.entity.Artwork;
import com.hexaware.vag.dao.VirtualArtGalleryImplemenataion;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VirtualArtGalleryTest {

    private VirtualArtGalleryImplemenataion gallery;

    @Before
    public void setUp() throws Exception {
        // Initialize the gallery before each test
        gallery = new VirtualArtGalleryImplemenataion();
    }

    @Test
    public void testAddArtwork() {
        // Create a new Artwork object with valid details
        Artwork artwork = new Artwork(0, "The Starry Night", "Famous painting by Van Gogh", "1889-10-20", "Oil on canvas", "imageURL", 1);

        // Call the method to add artwork and assert that it returns true (success)
        boolean isAdded = gallery.addArtwork(artwork);
        assertTrue("Artwork should be added successfully.", isAdded);
    }

    @Test
    public void testAddInvalidArtwork() {
        // Create an Artwork object with missing details (invalid artwork)
        Artwork invalidArtwork = new Artwork(0, "", "", "", "", "", 0);

        // Call the method to add invalid artwork and assert that it returns false (failure)
        boolean isAdded = gallery.addArtwork(invalidArtwork);
        assertFalse("Artwork with invalid details should not be added.", isAdded);
    }
    
    @Test
    public void testRemoveArtwork() {
        // Assume an artwork with ID 1 exists
        int artworkIdToRemove = 1;

        // Remove the artwork and assert that the method returns true (successful removal)
        boolean isRemoved = gallery.removeArtwork(artworkIdToRemove);
        assertTrue("Artwork should be removed successfully.", isRemoved);

        // Verify that the artwork no longer exists
        Artwork removedArtwork = gallery.getArtworkById(artworkIdToRemove);
        assertNull("Artwork should not be found after removal.", removedArtwork);
    }
    
    @Test
    public void testSearchArtworks() {
        // Assume you have added some artworks with the title "Starry Night"
        String keyword = "Starry Night";
        List<Artwork> searchResults = gallery.searchArtworks(keyword);
        
        // Verify that the search returns expected results
        assertFalse("Search should return artworks matching the keyword.", searchResults.isEmpty());
        for (Artwork artwork : searchResults) {
            assertTrue("All search results should match the keyword.", artwork.getTitle().contains(keyword));
        }
    }

}
