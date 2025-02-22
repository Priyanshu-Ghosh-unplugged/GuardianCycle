package com.example.guardiancycle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Class to manage location updates
public class LocationManager {

    // Declare variables
    private DatabaseReference databaseReference;

    // Constructor
    public LocationManager() {
        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("locations");
    }

    // Update location in the database
    public void updateLocationInDatabase(double latitude, double longitude) {
        String userId = FirebaseAuth.getInstance().getCurrentUser() != null ?
                FirebaseAuth.getInstance().getCurrentUser().getUid() : null;

        if (userId != null) {
            LocationData locationData = new LocationData(latitude, longitude);
            databaseReference.child(userId).setValue(locationData)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Location updated successfully
                        } else {
                            // Handle failure
                        }
                    });
        } else {
            // Handle the case where the user is not authenticated
            System.out.println("User is not authenticated.");
        }
    }

    // Inner class to represent location data
    public static class LocationData {
        public double latitude;
        public double longitude;

        // Default constructor required for calls to DataSnapshot.getValue(LocationData.class)
        public LocationData() {}

        // Constructor to initialize latitude and longitude
        public LocationData(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
