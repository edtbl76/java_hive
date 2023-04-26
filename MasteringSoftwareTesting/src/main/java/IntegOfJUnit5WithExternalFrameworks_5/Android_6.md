# Android
- developed by a startup named Android
- acquired and championed by Google in 2005


- as of 2017, Android + iOS account for 99%+ of
smartphone sales.

---
## Android in a nutshell
- Linux based software stack divided into several layers

### Android Layers
![AndroidLayers](AndroidLayers)

#### Linux Kernel
- foundation of Android platform
- contains all low-level device drivers for various
hardware components of an Android device
  
#### HAL (Hardware Abstraction Layer) 
- provides standard interfaces that expose HW
capabilities to the higher-level Java API
framework
  
#### ART (Android Runtime) 
- runtime environment for **.dex* files
    - (bytecode format designed for minimal 
      memory footprint)

#### Native Libraries (C/C++)
- native libs written in C/C++
    - EX: OpenGL ES for high-perf 2D/3D graphics
    processing
      
#### Java API Framework
- entire feature set of Android is available for
devs through APIs written in Java
    - (Building Blocks for creating Android APS)
    

- EXAMPLES
    - View System (for App UIs)
    - Resource Manager (for I18N, graphics, layouts)
    - Notification Manager (custom alerts in status bar)
    - Activity Manager (manages apps lifecycle) 
    - Content Provider (enables an app to access data from 
      other apps, such as Contacts, etc.)
      
#### Apps
- Android comes w/ set of core apps
    - Phone, Contacts, Browser, etc. 
- other apps can be downloaded and installed from 
Google Play (nee Android Market) o
  

### Android Releases

| Version | Codename | API Level | Linux Kernel | Release Date | 
| --- | --- | --- | --- | --- | 
| 1.5 | Cupcake | 3 | 2.6.27 | 20090430 |
| 1.6 | Donut | 4 | 2.6.29 | 20090915 | 
| 2.0 - 2.1 | Eclair | 5 - 7 | 2.6.29 | 20091026 | 
| 2.2 - 2.2.3 | Froyo | 8 | 2.6.32 | 20100520 |
| 2.3 - 2.3.7  | Gingerbread | 9 - 10 | 2.6.35 | 20101206 |
| 3.0 - 3.2.6 | Honeycomb | 11 - 13 | 2.6.36 | 20110222 |
| 4.0 - 4.0.4 | Ice Cream Sandwich | 14 - 15 | 3.0.1 | 20111018 | 
| 4.1 - 4.3.1 | Jelly Bean | 16 - 18 | 3.0.31, 3.0.21, 3.4.0 | 20120709 |
| 4.4 - 4.4.4 | KitKat | 19 - 20 | 3.10 | 20131031 |
| 5.0 - 5.1.1 | Lollipop | 21 -22 | 3.16.1 | 20141112 |
| 6.0 - 6.0.1 | Marshmallow | 23 | 3.18.10 | 20151005 |
| 7.0 - 7.1.2 | Nougat | 24 - 25 | 4.4.1 | 20160822 | 
| 8.0 - 8.1 | Oreo |  26 - 27 | | 20170821 |
| 9 | Pie | 28 | | 20180806 | 
| 10 | Android 10 | 29 | | 20190903 |
| 11 | Android 11 | 30 | | 20200908 | 


### Android Development 
- apps written in java
- SDK compiles java code w/ data and resource files into 
  - *Android Package* (**.apk*) files
    

- apk files can be installed into any Android powered Devices
  - smartphones, tablets, smart TVs, smartwatches, etc. 


#### Android Studio
- "official" IDE for Android Studio
  - based on Intellij IDEA
  - build process managed by Gradle build system
  
#### Android SDK
- contains all packages and tools required to develop Android apps
- **SDK Manager** allows to download/install SDK for different versions of
Android (from Android Release table)
  

#### AVD (Android Virtual Device)
- emulator that allows developers to model an actual device
- download/install different emulated Android virtual devices in one of 4 categories
  - phones
  - tables
  - TB
  - wears
  
---
## Gradle plugin for JUnit5 in Android Projects
- Gradle plugin, *android-junit5*

### build.gradle (Gradle)

  
    buildscript {
        dependencies {
            classpath "de.mannodermaus.gradle.plugins:android-junit5:1.0.0"
        }
    }

    apply plugin: "com.android.application"
    apply plugin: "de.mannodermaus.android-junit5"

    dependencies {
        testCompile junitJupiter()
    }

- the "apply plugin" statements allow us to extend the project capabilities in order to
use the Gradle plugin.
  - the plugin configures the **junitPlatform** task
    - automatically attaches Jupiter and Vintage engines during test exec phase. 

---