# GuardianCycle: SOS and Emergency Alert Application

GuardianCycle is an advanced SOS and emergency alert application designed to ensure safety in critical situations. This application integrates emergency contacts, location services, shake detection, and more to provide real-time assistance. It combines robust Kotlin-based architecture with an intuitive UI, making it suitable for both end-users and developers.

---

## Features
- **Emergency SOS Trigger:** Initiate SOS mode using shake gestures or a dedicated button.
- **Shake Detection:** Dynamically configurable sensitivity for shake triggers.
- **Emergency Contacts:** Send alerts with location to predefined contacts.
- **Location Tracking:** Real-time tracking during emergencies.
- **Audio Recording:** Automatically records audio during SOS mode.
- **Fake Call Generation:** Fakes up an incoming call so that the other people may think that the person is talking to someone on call.

---

## Installation

### Prerequisites
1. **Android Studio**: Version 2022.1 or later.
2. **Gradle**: Use the version specified in the `build.gradle` file.
3. **Android Device/Emulator**: Running Android 8.0 (API 26) or later.

### Steps to Launch the Project

#### 1. Clone the Repository
```bash
$ git clone https://github.com/username/guardiancycle.git
$ cd guardiancycle
```

#### 2. Open in Android Studio
- Open Android Studio.
- Navigate to **File > Open** and select the cloned repository folder.
- Wait for Gradle to sync.

#### 3. Set Up Dependencies
Ensure the required libraries and plugins are configured in the `build.gradle` files.
Run:
```bash
$ ./gradlew build
```

#### 4. Configure Permissions
Ensure the following permissions are added in the `AndroidManifest.xml` file for location, SMS, and audio recording:
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

#### 5. Build and Run
- Connect your Android device or start an emulator.
- Click **Run > Run App** or use `Shift + F10`.

---

## Usage Instructions

### Launching the Application
1. Open the app on your Android device.
2. Grant the requested permissions for optimal functionality.

### Key Features
#### **Trigger SOS Mode**
- **Shake Trigger**: Shake your device to initiate SOS.
  - Configure sensitivity in **Settings > Shake Detection**.
- **Button Trigger**: Use the on-screen SOS button to activate emergency mode.

#### **Send Alerts**
- Predefine emergency contacts under **Settings > Emergency Contacts**.
- SOS alerts include:
  - Predefined message.
  - Real-time location.

#### **Audio Recording**
- Automatic audio recording begins during SOS.
  - Files are stored in `Android/data/com.guardiancycle/files/`.

#### **Activate Siren**
- Siren plays during SOS mode to attract attention.

---

## Known Limitations
1. **Permissions**: Denied permissions may hinder functionality.
2. **Battery Usage**: Location tracking and audio recording may drain the battery.
3. **Device Compatibility**: Older devices may experience performance issues.

---

## Future Improvements
1. **Cloud Backup**: Sync emergency data to the cloud.
2. **Custom Themes**: Advanced personalization for the UI.
3. **Multi-language Support**: Expand app accessibility.

---

## Contributing
We welcome contributions to improve GuardianCycle. Follow these steps:
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Create a pull request.

---

## License
GuardianCycle is licensed under the [MIT License](LICENSE).

---

## Contact
For questions or support, please contact:
- **Email**: gdgpriyanshughosh@gmail.com
- **GitHub**: [GuardianCycle Repository](https://github.com/Priyanshu-Ghosh-Unplugged/guardiancycle)

