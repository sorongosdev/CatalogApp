# ☕️ 소카모 - 소라의 카페 모음집

## 📖 1. 프로젝트 소개

- 카페 카탈로그(이미지, 제목, 내용)를 불러오는 간단한 앱입니다.
- Spring Boot, MVVM, Hilt를 적용해보기 위해 제작했습니다.

## 👤 2. 역할

- 프론트엔드: Compose로 UI 구성, Retrofit으로 통신
- 백엔드: Spring Boot로 백엔드를 구성
- 배포: ngrok을 통해 로컬 서버를 배포

## 🛠️ 3. 개발 환경

### 🔍 1) 프레임워크 및 언어
- Front-end: Kotlin (2.0.21)
- Back-end: ngrok

### 🔧 2) 개발 도구
- Android Studio: Meerkat | 2024.3.1
- Gradle: 8.9
- Android Gradle Plugin: 8.7.3
- JDK: 17 (JetBrains Runtime)

### 📱 3) 테스트 환경
- Android 에뮬레이터: API 레벨 36 (Android 16)
- Android 실제 기기: Galaxy Note 9 API 레벨 29 (Android 10)

### 📚 4) 주요 라이브러리 및 API
```
retrofit = "2.11.0"
retrofit-gson = "2.11.0"

coil = "3.1.0"

hilt = "2.49"
hiltExt = "1.2.0"
ksp = "2.0.21-1.0.27"
```

### 🔖 5) 버전 및 이슈 관리
- Git: 2.39.5

## ▶️ 4. 프로젝트 실행 방법

### ⬇️ 1) 필수 설정 사항

- Android Studio (최신 버전)
- Java JDK (Java 8 이상)
- Android SDK (minSdk 24, targetSdk 34, compileSdk 34)
- Python (Python 3.12 - Chaquopy 사용)

### ⿻ 2) 프로젝트 클론 및 설정

- 프로젝트 클론
```bash
git clone https://github.com/sorongosdev/CatalogApp.git
```

- 의존성 설치
```bash
# Mac
./gradlew --refresh-dependencies

# Window
gradlew.bat --refresh-dependencies
```

### 🌐 3) 앱 빌드
```bash
./gradlew build
```

## 📁 5. 프로젝트 구조

<img width="213" alt="스크린샷 2025-04-08 오후 5 28 41" src="https://github.com/user-attachments/assets/e6e42446-ffb2-4202-9d36-28b3bf97e988" />

## 📅 6. 개발 기간
- 전체 프로젝트 기간: 2025.01 ~ 2025.04 (총 3개월)

## 📄 7. 동작 화면
| 스크롤1 | 스크롤2 |
|:---:|:---:|
|<img src="https://velog.velcdn.com/images/sorongos/post/b20fc0d0-a8a2-4a57-80a6-4dd6b18a6a1a/image.png" width="300">|<img src="https://velog.velcdn.com/images/sorongos/post/a78faebd-d2df-44c7-9b6c-ff9211da64cf/image.png" width="300">|
