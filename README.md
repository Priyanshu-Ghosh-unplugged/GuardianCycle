GuardianCycle: SOS and Emergency Alert Application
GuardianCycle is an advanced SOS and emergency alert application designed to ensure safety in critical situations. This application integrates emergency contacts, location services, shake detection, and more to provide real-time assistance. It combines robust Kotlin-based architecture with an intuitive UI, making it suitable for both end-users and developers.

Features
Emergency SOS Trigger: Initiate SOS mode using shake gestures or a dedicated button.
Shake Detection: Dynamically configurable sensitivity for shake triggers.
Emergency Contacts: Send alerts with location to predefined contacts.
Location Tracking: Real-time tracking during emergencies.
Audio Recording: Automatically records audio during SOS mode.
Siren Activation: Activates a siren to draw attention.
Installation
Prerequisites
Android Studio: Version 2022.1 or later.
Gradle: Use the version specified in the build.gradle file.
Android Device/Emulator: Running Android 8.0 (API 26) or later.
Steps to Launch the Project
1. Clone the Repository
$ git clone https://github.com/username/guardiancycle.git
$ cd guardiancycle
2. Open in Android Studio
Open Android Studio.
Navigate to File > Open and select the cloned repository folder.
Wait for Gradle to sync.
3. Set Up Dependencies
Ensure the required libraries and plugins are configured in the build.gradle files. Run:

$ ./gradlew build
4. Configure Permissions
Ensure the following permissions are added in the AndroidManifest.xml file for location, SMS, and audio recording:

<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
5. Build and Run
Connect your Android device or start an emulator.
Click Run > Run App or use Shift + F10.
Usage Instructions
Launching the Application
Open the app on your Android device.
Grant the requested permissions for optimal functionality.
Key Features
Trigger SOS Mode
Shake Trigger: Shake your device to initiate SOS.
Configure sensitivity in Settings > Shake Detection.
Button Trigger: Use the on-screen SOS button to activate emergency mode.
Send Alerts
Predefine emergency contacts under Settings > Emergency Contacts.
SOS alerts include:
Predefined message.
Real-time location.
Audio Recording
Automatic audio recording begins during SOS.
Files are stored in Android/data/com.guardiancycle/files/.
Activate Siren
Siren plays during SOS mode to attract attention.
Known Limitations
Permissions: Denied permissions may hinder functionality.
Battery Usage: Location tracking and audio recording may drain the battery.
Device Compatibility: Older devices may experience performance issues.
Future Improvements
Cloud Backup: Sync emergency data to the cloud.
Custom Themes: Advanced personalization for the UI.
Multi-language Support: Expand app accessibility.
Contributing
We welcome contributions to improve GuardianCycle. Follow these steps:

Fork the repository.
Create a feature branch.
Commit your changes.
Create a pull request.
License
GuardianCycle is licensed under the MIT License.

Contact
For questions or support, please contact:

Email: gdgpriyanshughosh@gmail.com
GitHub: GuardianCycle Repository
