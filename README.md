# KinoCopy

Мобильное Android-приложение для просмотра информации о фильмах.

## APK

Собранный APK-файл (debug) доступен здесь:

[kinocopy-v1.0.apk](./kinocopy-v1.0.apk)

> APK собран в debug-режиме — предназначен для тестирования и демонстрации. 
> Не содержит ProGuard/обфускации.

## Стек технологий

- Kotlin
- MVVM + Clean Architecture
- Jetpack Compose + Material 3
- Retrofit + Moshi
- Room + Flow
- Koin (Dependency Injection)
- ViewBinding (для части UI)

## Работа Snackbar

- errorMessageResId хранится в UI-состоянии, чтобы Snackbar переживал поворот экрана. В рамках ТЗ его action - единственный способ обновить данные после ошибки.