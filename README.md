**简体中文** | [English](README.md)

# ![image](https://raw.githubusercontent.com/Airsaid/AndroidLocalizePlugin/85cf5020832523ea333ad09286af55880460457a/src/main/resources/META-INF/pluginIcon.svg) AndroidLocalizePlugin
[![Plugin Version](https://img.shields.io/jetbrains/plugin/v/11174)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)
[![Plugin Rating](https://img.shields.io/jetbrains/plugin/r/rating/11174)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)
[![Build](https://github.com/Airsaid/AndroidLocalizePlugin/workflows/Build/badge.svg)](https://github.com/Airsaid/AndroidLocalizePlugin/actions/workflows/build.yml)

[Website](https://plugins.jetbrains.com/plugin/11174-androidlocalize) | [GitHub](https://github.com/Airsaid/AndroidLocalizePlugin) | [Issues](https://github.com/Airsaid/AndroidLocalizePlugin/issues) | [Reviews](https://plugins.jetbrains.com/plugin/11174-androidlocalize/reviews)

<!-- Plugin description -->
Android localization plugin supporting multiple languages and translators. Developed based on IntelliJ Platform SDK, featuring a modular design and flexible translation service integration.

# Features

## Core Features
- Multiple translator support:
  - Google Translate (Free and API versions)
  - Microsoft Translator
  - Baidu Translator
  - Youdao Translator
  - Alibaba Translator
  - DeepL Translator (Free and Pro versions)
  - OpenAI ChatGPT Translator
  - ByteDance Translator
- Support for 100+ languages
- One-click generation of all translation files
- Support for skipping existing strings
- Support for excluding specific text
- Translation caching support
- Configurable translation interval
<!-- Plugin description end -->

# Architecture Design

## Core Modules
- `translate`: Translation core module
  - Multiple translation service support
  - Extensible translation interface
  - Translation result caching
- `services`: Service layer
  - Translation service management
  - Resource file processing
  - Configuration management
- `task`: Task processing
  - Asynchronous translation tasks
  - Progress management
  - Cancellation support
- `action`: User interaction
  - Menu items
  - Dialogs
  - Settings interface
- `config`: Configuration management
  - Persistent storage
  - Secure configuration
- `utils`: Utility classes
  - Text processing
  - File operations
  - Common utilities

## Design Patterns
- Strategy Pattern: Translator implementation
- Singleton Pattern: Service classes
- Observer Pattern: Task callbacks
- Factory Pattern: Object creation

# User Guide

## Basic Usage
1. Select `values/strings.xml` file (or any resource file in the values directory)
2. Right-click and select "Translate to Other Languages"
3. Check the languages you want to translate to
4. Click OK to start translation

## Advanced Configuration
1. Translator Settings
   - Select default translator
   - Configure API keys
   - Set translation parameters
2. Cache Settings
   - Enable/disable cache
   - Set cache size
   - Configure cache strategy
3. Performance Settings
   - Translation interval
   - Concurrent task count
   - Timeout settings

# Development Guide

## Requirements
- JDK 11+
- IntelliJ IDEA 2020.3+
- Gradle 7.0+

## Adding New Translators
1. Implement the `AbstractTranslator` interface
2. Register using `@AutoService` annotation
3. Implement necessary translation methods
4. Add configuration support

## Building and Testing
```bash
# Build plugin
./gradlew build

# Run tests
./gradlew test

# Build release version
./gradlew buildPlugin
```

# Preview
![image](preview/preview.gif)
![image](preview/settings.png)

# Installation
[![Install Plugin](preview/install.png)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)

# FAQ

## Translation Related
- Q: How to exclude text from translation?

    A: You can use [translatable or xliff:g](https://developer.android.com/guide/topics/resources/localization#managing-strings) tags. Example:
    ```
    <string name="app_name" translatable="false">HelloAndroid</string>
    <string name="star_rating">Check out our 5<xliff:g id="star">\u2605</xliff:g></string>
    <string name="app_home_url">Visit us at <xliff:g id="application_homepage">https://github.com/Airsaid/AndroidLocalizePlugin</xliff:g></string>
    <string name="prod_name">Learn more at <xliff:g id="game_group">Muggle Game Studio</xliff:g></string>
    ```
  **Note: Display in one line, no extra line breaks or spaces in between.**

## Error Handling
- Q: Translation failure: java.net.HttpRetryException: cannot retry due to redirection, in streaming mode

   A: If you're using the default translation engine (Google), try switching to another engine in the settings page and use your own account for translation. The default translation engine is not stable.

# Changelog
[Changelog](CHANGELOG.md)

# Support and Donation

You can contribute and support this project by:

- Star the project on GitHub
- Report issues
- Submit PRs
- Share your ideas and suggestions
- Share the plugin with your friends and colleagues
- If you like this plugin, please consider donating to maintain the plugin and future updates:

  <table>
    <thead align="center">
      <tr>
        <th><a href="https://opencollective.com/androidlocalizeplugin" target="_blank">Open Collective</a></th>
        <th><a href="https://pay.weixin.qq.com/index.php/public/wechatpay_en" target="_blank">WeChat Pay</a></th>
        <th><a href="https://global.alipay.com" target="_blank">Alipay</a></th>
      </tr>
    </thead>
    <tr align="center">
      <td>
        <a href="https://opencollective.com/androidlocalizeplugin/donate" target="_blank">
          <img src="https://raw.githubusercontent.com/Airsaid/Resources/master/Images/opencollective-logo.png" width=298 alt="Donate To Our Collective">
        </a>
      </td>
      <td>
        <a href="https://pay.weixin.qq.com/index.php/public/wechatpay_en" target="_blank">
          <img src="https://raw.githubusercontent.com/Airsaid/Resources/master/Images/AndroidLocalizePlugin_WeChatPay.jpg" alt="WeChat Play">
        </a>
      </td>
      <td>
        <a href="https://global.alipay.com" target="_blank">
          <img src="https://raw.githubusercontent.com/Airsaid/Resources/master/Images/AndroidLocalizePlugin_Alipay.jpg" alt="Alipay">
        </a>
      </td>
    </tr>
  </table>

**Thank you for your support!**

# Sponsors
[![Development powered by JetBrains](https://pic.stackoverflow.wiki/uploadImages/111/201/226/60/2021/06/20/18/45/3aba65f5-1231-4c9a-817f-83cd5a29fd0c.svg)](https://jb.gg/OpenSourc)

# License
```
Copyright 2018 Airsaid. https://github.com/airsaid

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
