[English](README.md) | **简体中文**

# ![image](https://raw.githubusercontent.com/Airsaid/AndroidLocalizePlugin/85cf5020832523ea333ad09286af55880460457a/src/main/resources/META-INF/pluginIcon.svg) AndroidLocalizePlugin
[![Plugin Version](https://img.shields.io/jetbrains/plugin/v/11174)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)
[![Plugin Rating](https://img.shields.io/jetbrains/plugin/r/rating/11174)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)
[![Build](https://github.com/Airsaid/AndroidLocalizePlugin/workflows/Build/badge.svg)](https://github.com/Airsaid/AndroidLocalizePlugin/actions/workflows/build.yml)

[Website](https://plugins.jetbrains.com/plugin/11174-androidlocalize) | [GitHub](https://github.com/Airsaid/AndroidLocalizePlugin) | [Issues](https://github.com/Airsaid/AndroidLocalizePlugin/issues) | [Reviews](https://plugins.jetbrains.com/plugin/11174-androidlocalize/reviews)

Android 本地化插件，支持多种语言和翻译器。基于 IntelliJ Platform SDK 开发，采用模块化设计，提供灵活的翻译服务集成方案。

# 功能特性

## 核心功能
- 多翻译器支持：
  - Google 翻译（免费版和 API 版）
  - 微软翻译
  - 百度翻译
  - 有道翻译
  - 阿里翻译
  - DeepL 翻译（免费版和专业版）
  - OpenAI ChatGPT 翻译
  - 字节跳动翻译
- 支持 100+ 种语言
- 一键生成所有翻译文件
- 支持不翻译已存在的字符串
- 支持不翻译指定文本
- 支持翻译缓存
- 支持设置翻译间隔时间

## 技术特性
- 模块化架构设计
- 插件热插拔支持
- 异步任务处理
- 多级缓存机制
- 可扩展的翻译服务接口
- 安全的配置管理
- 完整的错误处理

# 架构设计

## 核心模块
- `translate`: 翻译核心模块
  - 支持多种翻译服务
  - 可扩展的翻译接口
  - 翻译结果缓存
- `services`: 服务层
  - 翻译服务管理
  - 资源文件处理
  - 配置管理
- `task`: 任务处理
  - 异步翻译任务
  - 进度管理
  - 取消支持
- `action`: 用户交互
  - 菜单项
  - 对话框
  - 设置界面
- `config`: 配置管理
  - 持久化存储
  - 安全配置
- `utils`: 工具类
  - 文本处理
  - 文件操作
  - 通用工具

## 设计模式
- 策略模式：翻译器实现
- 单例模式：服务类
- 观察者模式：任务回调
- 工厂模式：对象创建

# 使用指南

## 基本使用
1. 选择 `values/strings.xml` 文件（或 values 目录下的任何资源文件）
2. 右键选择："Translate to Other Languages"
3. 勾选需要翻译的语言
4. 点击 OK 开始翻译

## 高级配置
1. 翻译器设置
   - 选择默认翻译器
   - 配置 API 密钥
   - 设置翻译参数
2. 缓存设置
   - 启用/禁用缓存
   - 设置缓存大小
   - 配置缓存策略
3. 性能设置
   - 翻译间隔时间
   - 并发任务数
   - 超时设置

# 开发指南

## 环境要求
- JDK 11+
- IntelliJ IDEA 2020.3+
- Gradle 7.0+

## 添加新的翻译器
1. 实现 `AbstractTranslator` 接口
2. 使用 `@AutoService` 注解注册
3. 实现必要的翻译方法
4. 添加配置支持

## 构建和测试
```bash
# 构建插件
./gradlew build

# 运行测试
./gradlew test

# 构建发布版本
./gradlew buildPlugin
```

# 预览
![image](preview/preview.gif)
![image](preview/settings.png)

# 安装
[![Install Plugin](preview/install.png)](https://plugins.jetbrains.com/plugin/11174-androidlocalize)

# 常见问题

## 翻译相关
- 问题：如何忽略不让其翻译？

    回答：可以使用 [translatable 或 xliff:g](https://developer.android.com/guide/topics/resources/localization#managing-strings) 标签。示例：
    ```
    <string name="app_name" translatable="false">HelloAndroid</string>
    <string name="star_rating">Check out our 5<xliff:g id="star">\u2605</xliff:g></string>
    <string name="app_home_url">Visit us at <xliff:g id="application_homepage">https://github.com/Airsaid/AndroidLocalizePlugin</xliff:g></string>
    <string name="prod_name">Learn more at <xliff:g id="game_group">Muggle Game Studio</xliff:g></string>
    ```
  **注意：一行展示，中间不要有多余的换行和空格。**

## 错误处理
- 问题：Translation failure: java.net.HttpRetryException: cannot retry due to redirection, in streaming mode

  回答：如果你使用的是默认的翻译引擎（Google），那么你可以在设置页面尝试切换到其他引擎，并使用自己的账号进行翻译。因为默认的翻译引擎并不稳定。

# 更新日志
[更新日志](CHANGELOG.md)

# 支持和捐赠

您可以通过执行以下任意操作来贡献和支持此项目：

- 在 GitHub 上 Star 该项目
- 反馈问题
- 提交 PR
- 提出您的想法或建议
- 将插件分享给您的朋友和同事
- 如果您喜欢这个插件，请考虑捐赠以维持该插件和后续的更新：

  <table>
    <thead align="center">
      <tr>
        <th><a href="https://opencollective.com/androidlocalizeplugin" target="_blank">Open Collective</a></th>
        <th><a href="https://pay.weixin.qq.com/index.php/public/wechatpay_en" target="_blank">微信支付</a></th>
        <th><a href="https://global.alipay.com" target="_blank">支付宝</a></th>
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

**感谢您的支持！**

# 赞助商
[![Development powered by JetBrains](https://pic.stackoverflow.wiki/uploadImages/111/201/226/60/2021/06/20/18/45/3aba65f5-1231-4c9a-817f-83cd5a29fd0c.svg)](https://jb.gg/OpenSourc)

# 许可证
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
