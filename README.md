# 💸 BankNote Project
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.9.x-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Android Platform](https://img.shields.io/badge/platform-Android-green.svg?logo=android)](https://developer.android.com)
[![Architecture](https://img.shields.io/badge/architecture-MVVM%20+%20Clean-orange.svg)]()
[![DI Framework](https://img.shields.io/badge/DI-Koin-red.svg?logo=koin)](https://insert-koin.io/)

Một ứng dụng Android hiện đại, được viết hoàn toàn bằng **Kotlin**, nhằm mục đích duyệt, tìm kiếm và quản lý bộ sưu tập các loại tiền giấy (banknotes). Dự án này được thiết kế và phát triển theo chuẩn kiến trúc và các best practices mới nhất của Android.
---
## 🚀 Giới thiệu (Introduction)
**BankNote Project** mang đến trải nghiệm mượt mà cho những người đam mê sưu tầm tiền giấy. Ứng dụng kết nối với API từ xa để hiển thị danh sách các loại tiền, cung cấp tính năng tìm kiếm mạnh mẽ, xem chi tiết từng tờ tiền và lưu trữ chúng vào bộ sưu tập cá nhân (Local Database).
## ✨ Tính năng chính (Key Features)
- **Màn hình Splash & Onboarding**: Chào mừng người dùng mới với giao diện đẹp mắt.
- **Duyệt tiền giấy (Home)**: Hiển thị danh sách các loại tiền giấy với cơ chế phân trang (Pagination).
- **Tìm kiếm (Search)**: Tìm kiếm tiền giấy theo tên một cách nhanh chóng.
- **Xem chi tiết (Detail)**: Hiển thị thông tin chi tiết và hình ảnh chất lượng cao của từng tờ tiền.
- **Bộ sưu tập cá nhân (Collection)**: Lưu trữ và quản lý các tờ tiền yêu thích bằng cơ sở dữ liệu nội bộ (Room Database).
---
## 🛠 Công nghệ & Kiến trúc (Tech Stack & Architecture)
Dự án áp dụng mô hình kiến trúc **MVVM (Model-View-ViewModel)** kết hợp tư tưởng **Clean Architecture**, phân chia rõ ràng các layer: **Domain**, **Data** và **UI**.
### 🔹 Ngôn ngữ & Nền tảng
- **[Kotlin](https://kotlinlang.org/)**: 100% Kotlin.
- **Android SDK**: `minSdk = 24`, `targetSdk = 36`.
### 🔹 Core & UI
- **[ViewBinding](https://developer.android.com/topic/libraries/view-binding)**: Liên kết giao diện an toàn, tránh NullPointerException.
- **[Material Design](https://material.io/develop/android)**: Các UI Components tiêu chuẩn.
- **[ViewPager2](https://developer.android.com/training/animation/screen-slide-2)**: Chuyển đổi màn hình mượt mà (có thể dùng trong Onboarding hoặc Image Slider).
- **Fragment & Activity KTX**: Hỗ trợ mở rộng cho vòng đời ứng dụng.
### 🔹 Xử lý Bất đồng bộ & Data Stream
- **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)**: Quản lý các tác vụ chạy ngầm một cách ngắn gọn, hiệu quả.
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) / ViewModel**: Quản lý trạng thái giao diện và vòng đời (Lifecycle-aware).
### 🔹 Network & Lưu trữ (Data Layer)
- **[Retrofit 2](https://square.github.io/retrofit/) & OkHttp3**: HTTP Client để gọi API (tải dữ liệu tiền giấy). Logging Interceptor được tích hợp để debug dễ dàng.
- **[Gson](https://github.com/google/gson)**: Chuyển đổi dữ liệu JSON.
- **[Room Database](https://developer.android.com/training/data-storage/room)**: ORM nội bộ, kết hợp với KSP (Kotlin Symbol Processing) để lưu trữ bộ sưu tập tiền giấy yêu thích (CollectionDao).
- **[Glide](https://github.com/bumptech/glide)**: Tải và hiển thị hình ảnh tối ưu.
### 🔹 Dependency Injection (DI)
- **[Koin](https://insert-koin.io/)**: Framework DI nhẹ nhàng, thuần Kotlin. Dự án phân chia thành các module rõ ràng: `AppModule`, `DatabaseModule`, `NetworkModule`, `RepositoryModule`, `ViewModelModule`.
---
## 📂 Cấu trúc thư mục (Folder Structure)
Dự án được tổ chức theo tính năng (Package by Feature) kết hợp phân tầng rõ ràng:
```text
com.example.banknoteproject
│
├── BanknoteApplication.kt   # Lớp Application, khởi tạo Koin (DI)
│
├── data/                    # Data Layer
│   ├── domain/              # Entities và định nghĩa Interfaces
│   ├── repository/          # Triển khai (Implementation) của Repository (Home, Search, Detail)
│   └── source/              # Các nguồn dữ liệu:
│       ├── local/           # Room DB (AppDatabase, CollectionDao)
│       └── remote/          # API services (BanknoteApi)
│
├── di/                      # Dependency Injection (Koin Modules)
│   └── module/              # Các module: Network, Database, ViewModel, Repository
│
├── ui/                      # Presentation Layer (UI & ViewModels)
│   ├── main/                # MainActivity (Single Activity Architecture)
│   ├── splash/              # Màn hình Splash
│   ├── onboarding/          # Hướng dẫn người dùng mới
│   ├── home/                # Danh sách tiền giấy chính
│   ├── search/              # Màn hình tìm kiếm
│   ├── detail/              # Chi tiết tờ tiền
│   └── collection/          # Bộ sưu tập đã lưu (Local)
│
└── utils/                   # Các hàm/lớp tiện ích và Constants chung
```
---
## 💻 Hướng dẫn cài đặt (Installation)
1. **Clone dự án**:
   ```bash
   git clone https://github.com/your-username/BankNoteProject.git
   ```
2. **Mở dự án trên Android Studio**:
   - Sử dụng phiên bản Android Studio mới nhất (Hỗ trợ KSP & Gradle version tương ứng).
   - Chọn `File` > `Open` > Trỏ tới thư mục `BankNoteProject`.
3. **Đồng bộ hóa Gradle** (Sync Project with Gradle Files).
4. **Chạy ứng dụng** (Run) trên máy ảo (Emulator) hoặc thiết bị vật lý.
---
## 🤝 Đóng góp (Contributing)
Mọi ý kiến đóng góp, báo lỗi (issues) và pull requests đều được hoan nghênh. Xin vui lòng tạo issue trước khi submit một pull request lớn.
## 📝 Giấy phép (License)
Dự án được phát hành dưới bản quyền **MIT License**.
