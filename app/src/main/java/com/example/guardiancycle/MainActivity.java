package com.example.guardiancycle;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Sensor variables
    private SensorManager sensorManager;
    private float acelVal; // current acceleration value and gravity
    private float acelLast; // last acceleration value and gravity
    private float shake; // acceleration apart from gravity

    // Location variables
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    // Audio recording variables
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    // Firebase variables
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("locations");

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

        // Initialize location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // 10 seconds
        locationRequest.setFastestInterval(5000); // 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // SOS button setup
        Button sosButton = findViewById(R.id.sosButton);
        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerSOS();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        acelLast = acelVal;
        acelVal = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = acelVal - acelLast;
        shake = shake * 0.9f + delta;

        if (shake > 12) {
            // Shake detected, trigger SOS
            triggerSOS();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    private void triggerSOS() {
        // 1. Send SMS with Location
        sendEmergencySMS();
        // 2. Start Location Tracking
        startLocationUpdates();
        // 3. Start Audio Recording
        startAudioRecording();
        // 4. Activate Siren
        playSiren();
        Toast.makeText(this, "SOS Triggered!", Toast.LENGTH_SHORT).show();
    }

    // Method to save emergency contact
    private void saveEmergencyContact(String contactName, String contactNumber) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(contactName, contactNumber);
        editor.apply();
    }

    // Method to retrieve emergency contact
    private String getEmergencyContact(String contactName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(contactName, null);
    }

    // Start location updates
    private void startLocationUpdates() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Use the location data (latitude, longitude)
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    // Update location in the database
                    updateLocationInDatabase(latitude, longitude);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    // Stop location updates
    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    // Method to update location in the database
    private void updateLocationInDatabase(double latitude, double longitude) {
        String userId = FirebaseAuth.getInstance().getCurrentUser() != null ?
                FirebaseAuth.getInstance().getCurrentUser().getUid() : null;

        if (userId != null) {
            LocationData locationData = new LocationData(latitude, longitude);
            databaseReference.child(userId).setValue(locationData);
        } else {
            System.out.println("User is not authenticated.");
        }
    }

    // Method to start audio recording
    private void startAudioRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(getExternalFilesDir(null).getAbsolutePath() + "/audio.3gp"); // Set your file path

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to stop audio recording
    private void stopAudioRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    // Method to play siren
    private void playSiren() {
        mediaPlayer = MediaPlayer.create(this, R.raw.siren_sound); // Add your siren sound file in res/raw
        mediaPlayer.start();
    }

    // Method to stop siren
    private void stopSiren() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Method to send emergency SMS (implement this method)
    private void sendEmergencySMS() {
        // Implement SMS sending logic here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
        stopAudioRecording();
        stopSiren();
    }

    // Inner class to represent location data
    public static class LocationData {
        public double latitude;
        public double longitude;

        public LocationData() {}

        public LocationData(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
