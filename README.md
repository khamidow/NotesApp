<div align="center">

<br/>

# 📝 NotesApp

### A modern, Firebase-powered note-taking app for Android

<br/>

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Firebase](https://img.shields.io/badge/Firebase-Enabled-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com/)

<br/>

> **Capture ideas instantly. Access them anywhere. Never lose a thought again.**

<br/>

</div>

---

## ✨ Features

| Feature | Description |
|---|---|
| 📱 **Intuitive UI** | Clean, distraction-free interface for effortless note creation |
| ☁️ **Cloud Sync** | Notes backed up and synced in real-time via Firebase |
| 🔒 **Reliable & Crash-Free** | Firebase Crashlytics monitors and reports any app issues |
| ⚡ **Fast & Lightweight** | Built with performance-first architecture |
| 🎨 **Material Design** | Follows Google's Material Design guidelines for a polished feel |
| 🔁 **Persistent Storage** | Your notes are always there when you need them |

---

## 🛠️ Tech Stack

This project is built with a modern, production-grade Android tech stack:

```
NotesApp
├── Language         →  Kotlin (100%)
├── Platform         →  Android (Native)
├── Build System     →  Gradle (Kotlin DSL / .kts)
├── Cloud Backend    →  Firebase
│   ├── Google Services (Authentication / Realtime)
│   └── Firebase Crashlytics (crash monitoring)
└── IDE              →  Android Studio
```

### Core Technologies

- **[Kotlin](https://kotlinlang.org/)** — Expressive, safe, and concise. The modern language of Android development.
- **[Firebase](https://firebase.google.com/)** — Google's backend platform powering real-time data sync and authentication.
- **[Firebase Crashlytics](https://firebase.google.com/products/crashlytics)** — Real-time crash reporting for production stability.
- **[Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)** — Type-safe, IDE-friendly build configuration.

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- Android SDK **API 24+**
- A [Firebase](https://firebase.google.com/) project with Google Services configured

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/khamidow/NotesApp.git
cd NotesApp
```

**2. Connect Firebase**

- Go to [Firebase Console](https://console.firebase.google.com/) and create a project (or use an existing one)
- Download the `google-services.json` file
- Place it inside the `app/` directory:
  ```
  NotesApp/
  └── app/
      └── google-services.json   ← put it here
  ```

**3. Build & Run**
```bash
# Open in Android Studio, then click Run ▶
# Or via terminal:
./gradlew assembleDebug
```

> ⚠️ The app requires a valid `google-services.json` to compile. Without it, the build will fail at the Firebase Gradle plugin step.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. **Fork** the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a **Pull Request**

---

## 📬 Contact

**Khamidow** — [@khamidow](https://github.com/khamidow)

Project Link: [https://github.com/khamidow/NotesApp](https://github.com/khamidow/NotesApp)

---

## 📄 License

Distributed under the MIT License. See [`LICENSE`](./LICENSE) for more information.

---

<div align="center">

Made with ❤️ by [github.com/khamidow](https://github.com/khamidow)

⭐ **If you found this project helpful, please give it a star!** ⭐

</div>
