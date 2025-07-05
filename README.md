# 🎮 Angry Birds - LibGDX Edition

<div align="center">

![Angry Birds Logo](assets/libgdx.png)

**A faithful recreation of the classic Angry Birds game built with LibGDX**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![LibGDX](https://img.shields.io/badge/LibGDX-1.12.1-blue.svg)](https://libgdx.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Web%20%7C%20Desktop-lightgrey.svg)]()

[🎯 Play Online](https://sparkly-bombolone-f54a52.netlify.app/) • [📖 Documentation](#documentation) • [🔧 Build](#building-from-source) • [🤝 Contributing](#contributing)

</div>

---

## 🌟 Features

### 🎯 Core Gameplay
- **Physics-Based Slingshot Mechanics** - Realistic projectile physics powered by Box2D
- **Multiple Bird Types** with unique abilities:
  - 🔴 **Red Bird** - Standard projectile with balanced damage
  - ⚫ **Black Bird** - Explosive impact with area damage
  - 🟡 **Yellow Bird** - Speed boost mid-flight for precision shots
- **Three Challenging Levels** with increasing difficulty
- **Dynamic Block Destruction** with realistic physics

### 🏗️ Block Types & Materials
- **🪵 Wood Blocks** - Light and easily destructible
- **🧱 Stone Blocks** - Heavy and durable, requires strategic hits
- **💎 Glass Blocks** - Fragile but can cause chain reactions

### 🐷 Pig Varieties
- **Normal Pig** - Basic enemy with standard health
- **Crown Pig** - Tougher variant requiring multiple hits
- **Princess Pig** - Special pig with unique behavior

### 🎮 Game Features
- **Pause & Resume** functionality
- **Save & Load Game** system for progress persistence
- **Level Selection** screen with progress tracking
- **Game Over & Victory** screens with retry options
- **Responsive UI** optimized for both desktop and web

---

## 🚀 Quick Start

### 🎮 Play Online
Visit our live deployment: **[Play Angry Birds Now!](https://sparkly-bombolone-f54a52.netlify.app)**

### 💻 Run Locally

#### Prerequisites
- Java 17 or higher
- Git

#### Installation
```bash
# Clone the repository
git clone https://github.com/re-fract/CSE201-Angry-Birds-Project.git
cd CSE201-Angry-Birds-Project

# Run desktop version
./gradlew lwjgl3:run

# Or on Windows
gradlew.bat lwjgl3:run
```

#### Web Development
```bash
# Start development server
./gradlew html:superDev

# Build for production
./gradlew html:dist
```

---

## 🎯 How to Play

### 🎮 Basic Controls
- **🖱️ Mouse/Touch**: Aim and shoot birds
- **⏸️ Pause**: Access pause menu during gameplay
- **🏠 Home**: Return to main menu
- **🔄 Retry**: Restart current level

### 🏆 Objective
Destroy all pigs using the limited number of birds available. Each level requires different strategies:

1. **🎯 Aim Carefully** - Consider trajectory and physics
2. **💥 Use Bird Abilities** - Each bird type has unique properties
3. **🧠 Strategic Thinking** - Sometimes indirect shots work better
4. **⚡ Chain Reactions** - Use falling blocks to your advantage

### 🌟 Scoring
- **Direct Hits** on pigs award points
- **Block Destruction** provides bonus points
- **Remaining Birds** multiply your final score

---

## 🛠️ Building from Source

### 📋 Requirements
- **Java Development Kit (JDK) 17+**
- **Gradle** (included via wrapper)
- **Git**

### 🔧 Build Commands

```bash
# Desktop JAR
./gradlew lwjgl3:dist

# Web build
./gradlew html:dist

# Clean build
./gradlew clean build

# Run tests
./gradlew test
```

### 📦 Project Structure
```
📁 CSE201-Angry-Birds-Project/
├── 📁 core/                 # Core game logic
│   └── 📁 src/main/java/com/game/angrybirds/
│       ├── 📁 bird/         # Bird classes and abilities
│       ├── 📁 block/        # Block types and physics
│       ├── 📁 pig/          # Pig variants
│       └── 📁 screens/      # Game screens and UI
├── 📁 lwjgl3/               # Desktop launcher
├── 📁 html/                 # Web/GWT configuration
├── 📁 assets/               # Game assets (sprites, sounds)
└── 📁 gradle/               # Build configuration
```

---

## 🎨 Technical Details

### 🔧 Built With
- **[LibGDX](https://libgdx.com/)** - Cross-platform game development framework
- **[Box2D](https://box2d.org/)** - 2D physics engine for realistic mechanics
- **[GWT](http://www.gwtproject.org/)** - Java to JavaScript compilation for web
- **[LWJGL3](https://www.lwjgl.org/)** - Desktop OpenGL bindings

### 🎯 Key Features Implementation
- **Physics Simulation**: Box2D integration for realistic projectile physics
- **Collision Detection**: Precise collision handling for birds, blocks, and pigs
- **Particle Systems**: Dynamic destruction effects and visual feedback
- **State Management**: Robust game state handling and persistence
- **Responsive Design**: Adaptive UI for multiple screen sizes and platforms

### 🌐 Web Optimization
- **Viewport Scaling**: Proper coordinate system handling for web deployment
- **Touch Controls**: Mobile-friendly input system
- **Asset Loading**: Optimized resource management for web browsers
- **Performance**: Smooth 60fps gameplay across platforms

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### 🐛 Bug Reports
- Use the [Issues](https://github.com/re-fract/CSE201-Angry-Birds-Project/issues) tab
- Provide detailed reproduction steps
- Include screenshots if applicable

### 💡 Feature Requests
- Check existing issues first
- Describe the feature in detail
- Explain the use case and benefits

### 🔧 Development
1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### 📝 Code Style
- Follow Java naming conventions
- Add comments for complex logic
- Include unit tests for new features
- Update documentation as needed

---

## 📊 Project Stats

<div align="center">

![Lines of Code](https://img.shields.io/badge/Lines%20of%20Code-5000+-brightgreen)
![Files](https://img.shields.io/badge/Files-100+-blue)
![Classes](https://img.shields.io/badge/Classes-25+-orange)
![Commits](https://img.shields.io/github/commit-activity/m/re-fract/CSE201-Angry-Birds-Project)

</div>

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- **Rovio Entertainment** - Original Angry Birds creators for inspiration
- **LibGDX Community** - Amazing framework and documentation
- **Box2D** - Excellent physics engine
- **Contributors** - Everyone who helped make this project better

---

## 📞 Contact & Support

<div align="center">

**Built with ❤️ for CSE201 Course Project**

[![GitHub](https://img.shields.io/badge/GitHub-re--fract-181717?logo=github)](https://github.com/re-fract)
[![Email](https://img.shields.io/badge/Email-Contact-red?logo=gmail)](mailto:your-email@example.com)

⭐ **Star this repository if you enjoyed the game!** ⭐

</div>

---

<div align="center">

**[🎮 Play Now](https://sparkly-bombolone-f54a52.netlify.app)** | **[📖 Documentation](#documentation)** | **[🔧 Build Guide](#building-from-source)** | **[🤝 Contribute](#contributing)**

</div>
