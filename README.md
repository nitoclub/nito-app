# NITO

NITO のユーザーアプリのリポジトリです。

<a href='https://play.google.com/store/apps/details?id=club.nito.app&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Google Play で手に入れよう' src='docs/images/google-play/badge.png' height='100px'/></a>

## ディレクトリ構成

```text
.
│   アプリケーションのディレクトリ
├── app
│
│   BFF のディレクトリ
├── bff
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
