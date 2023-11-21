![NITO](docs/images/hero.svg)

# NITO

NITO のユーザーアプリです。

<a href='https://play.google.com/store/apps/details?id=club.nito.app&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Google Play で手に入れよう' src='docs/images/google-play/badge.png' height='100px'/></a>

## Screenshots

<img alt='screenshot-01' src='docs/images/google-play/screenshot-01.png' width='300px'/><img alt='screenshot-02' src='docs/images/google-play/screenshot-02.png' width='300px'/><img alt='screenshot-03' src='docs/images/google-play/screenshot-03.png' width='300px'/>

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
