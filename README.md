# NITO

NITO のユーザーアプリのリポジトリです。

## ディレクトリ構成

```text
.
│   アプリケーションのディレクトリ
├── app
│
│   ビルドロジックを集約したディレクトリ
├── build-logic
│
│   各 feature モジュールで使用する共通のモジュールを集約したディレクトリ
├── core
│
│   各機能モジュールのディレクトリ
└── feature
```

## アーキテクチャ構成

- [Kotlin Multiplatform](https://kotlinlang.org/lp/multiplatform/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=ja)
- [SwiftUI](https://developer.apple.com/jp/xcode/swiftui/)
