package com.hexaware.vag.main;

import com.hexaware.vag.dao.*;
import com.hexaware.vag.entity.*;
import java.util.List;
import java.util.Scanner;


public class MainModule {

    public static void main(String[] args) throws ClassNotFoundException {
        VirtualArtGalleryImplemenataion gallery = new VirtualArtGalleryImplemenataion();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Virtual Art Gallery");
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Artwork");
            System.out.println("2. Update Artwork");
            System.out.println("3. Remove Artwork");
            System.out.println("4. Search Artworks");
            System.out.println("5. Add Artwork to Favorites");
            System.out.println("6. View Favorite Artworks");
            System.out.println("7. Remove Artwork from Favorites");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addArtwork(gallery, scanner);
                    break;
                case 2:
                    updateArtwork(gallery, scanner);
                    break;
                case 3:
                    removeArtwork(gallery, scanner);
                    break;
                case 4:
                    searchArtworks(gallery, scanner);
                    break;
                case 5:
                    addArtworkToFavorites(gallery, scanner);
                    break;
                case 6:
                    viewFavoriteArtworks(gallery, scanner);
                    break;
                case 7:
                    removeArtworkFromFavorites(gallery, scanner);
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting the Virtual Art Gallery. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // 1. Add Artwork Method
    private static void addArtwork(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.println("Enter artwork details:");
        scanner.nextLine(); // Consume the leftover newline
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Creation Date: ");
        String creationDate = scanner.nextLine();
        System.out.print("Medium: ");
        String medium = scanner.nextLine();
        System.out.print("Image URL: ");
        String imageURL = scanner.nextLine();
        System.out.print("Artist ID: ");
        int artistID = scanner.nextInt();

        Artwork artwork = new Artwork(0, title, description, creationDate, medium, imageURL, artistID);
        if (gallery.addArtwork(artwork)) {
            System.out.println("Artwork added successfully.");
        } else {
            System.out.println("Failed to add artwork.");
        }
    }

    // 2. Update Artwork Method
    private static void updateArtwork(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter the Artwork ID to update: ");
        int artworkID = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
        Artwork existingArtwork = gallery.getArtworkById(artworkID);

        if (existingArtwork != null) {
            System.out.println("Enter new details (leave blank to keep current value):");
            System.out.print("Title (" + existingArtwork.getTitle() + "): ");
            String title = scanner.nextLine();
            System.out.print("Description (" + existingArtwork.getDescription() + "): ");
            String description = scanner.nextLine();
            System.out.print("Creation Date (" + existingArtwork.getCreationDate() + "): ");
            String creationDate = scanner.nextLine();
            System.out.print("Medium (" + existingArtwork.getMedium() + "): ");
            String medium = scanner.nextLine();
            System.out.print("Image URL (" + existingArtwork.getImageUrl() + "): ");
            String imageURL = scanner.nextLine();

            // Update values only if input is not empty
            if (!title.isEmpty()) existingArtwork.setTitle(title);
            if (!description.isEmpty()) existingArtwork.setDescription(description);
            if (!creationDate.isEmpty()) existingArtwork.setCreationDate(creationDate);
            if (!medium.isEmpty()) existingArtwork.setMedium(medium);
            if (!imageURL.isEmpty()) existingArtwork.setImageUrl(imageURL);

            if (gallery.updateArtwork(existingArtwork)) {
                System.out.println("Artwork updated successfully.");
            } else {
                System.out.println("Failed to update artwork.");
            }
        } else {
            System.out.println("Artwork not found.");
        }
    }

    // 3. Remove Artwork Method
    private static void removeArtwork(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter the Artwork ID to remove: ");
        int artworkID = scanner.nextInt();
        if (gallery.removeArtwork(artworkID)) {
            System.out.println("Artwork removed successfully.");
        } else {
            System.out.println("Failed to remove artwork or artwork not found.");
        }
    }

    // 4. Search Artworks Method
    private static void searchArtworks(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter keyword to search artworks: ");
        scanner.nextLine(); // Consume the leftover newline
        String keyword = scanner.nextLine();
        List<Artwork> artworks = gallery.searchArtworks(keyword);
        if (!artworks.isEmpty()) {
            System.out.println("Search Results:");
            for (Artwork artwork : artworks) {
                System.out.println("ID: " + artwork.getArtworkId() + ", Title: " + artwork.getTitle() + ", Artist ID: " + artwork.getArtistId());
            }
        } else {
            System.out.println("No artworks found for the keyword: " + keyword);
        }
    }

    // 5. Add Artwork to Favorites Method
    private static void addArtworkToFavorites(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        System.out.print("Enter Artwork ID to add to favorites: ");
        int artworkID = scanner.nextInt();
        if (gallery.addArtworkToFavorite(userID, artworkID)) {
            System.out.println("Artwork added to favorites.");
        } else {
            System.out.println("Failed to add artwork to favorites.");
        }
    }

    // 6. View Favorite Artworks Method
    private static void viewFavoriteArtworks(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter User ID to view favorites: ");
        int userID = scanner.nextInt();
        List<Artwork> favoriteArtworks = gallery.getUserFavoriteArtworks(userID);
        if (!favoriteArtworks.isEmpty()) {
            System.out.println("Favorite Artworks:");
            for (Artwork artwork : favoriteArtworks) {
                System.out.println("ID: " + artwork.getArtworkId() + ", Title: " + artwork.getTitle() + ", Artist ID: " + artwork.getArtistId());
            }
        } else {
            System.out.println("No favorite artworks found for this user.");
        }
    }

    // 7. Remove Artwork from Favorites Method
    private static void removeArtworkFromFavorites(VirtualArtGalleryImplemenataion gallery, Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        System.out.print("Enter Artwork ID to remove from favorites: ");
        int artworkID = scanner.nextInt();
        if (gallery.removeArtworkFromFavorite(userID, artworkID)) {
            System.out.println("Artwork removed from favorites.");
        } else {
            System.out.println("Failed to remove artwork from favorites.");
        }
    }
}

