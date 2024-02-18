# EdgeTrack Android SDK

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

The EdgeTrack Android SDK allows you to collect performance metrics of machine learning models on edge devices and send them to the EdgeTrack platform.

## Our Vision

Machine Learning engineers and scientists are often faced with the challenge of deploying their models on edge devices. The performance of machine learning models on edge devices is crucial for the success of edge computing. EdgeTrack aims to provide a platform for collecting and analyzing performance metrics of machine learning models on edge devices. Our goal is to provide a platform that allows developers to collect and analyze performance metrics of machine learning models on edge devices, and to use this information to improve the performance of their models.
We wanted to answer the following questions:
- How is our model performing on X device with Y OS version?
- What is the average inference time of our model on edge devices?
- How does the performance change over time?
- How does the performance change with different model versions?
- How does the performance change with different device models?
- How much resources are used by our model on edge devices?

It is easy to find answer to those questions when machine learning models are deployed on cloud servers. Because, the environment is controlled.
However, it is challenging to collect and analyze performance metrics of machine learning models on edge devices. 
EdgeTrack aims to provide a platform for collecting and analyzing performance metrics of machine learning models on edge devices.

## Features

- Collect performance metrics of machine learning models on edge devices.
- Easy integration with Android applications.
- Batched and aggregated metrics sending to the EdgeTrack platform.
- Support for various Android devices and operating systems.

## Installation

Add the EdgeTrack Android SDK to your project by including the following dependency in your `build.gradle` file:

```groovy
implementation ("com.inferencetime.edgetrack:edgetrack-android-sdk:1.0.0")
```
## Usage
1. Initialize the SDK with your API key and session UUID.
2. Integrate SDK into your Android application.
3. Collect performance metrics and send them to the EdgeTrack platform.

### Initialize the SDK
```kotlin
import com.inferencetime.edgetrack.EdgeTrack
et = EdgeTrack(
            apiKey = "YOUR_API_KEY",
            sessionUuid = "Session123",
            context = this
        )
```
Session UUID is a unique identifier for the session. It can be generated using UUID.randomUUID().toString().
### Collect performance metrics and send them to the EdgeTrack platform
```kotlin
et.collectMetrics(
    EdgeTrackMetrics(
        modelName = "my_test_model",
        modelVersion = "1.0",
        inferenceTime = randomInference,
        modelAccuracy = 0.95f
    )
)
```
 - modelName: Name of the machine learning model.
 - modelVersion: Version of the machine learning model.
 - inferenceTime: Inference time of the machine learning model.
 - modelAccuracy: Accuracy of the machine learning model. (Optional)

### Other data we collect
- Device information: Device model, OS version, and SDK version.
- Hardware information: CPU, GPU, RAM, and available memory.

