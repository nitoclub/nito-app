# iOS

NITO の iOS アプリのディレクトリです。

## KMP の iOS 用フレームワークのビルド

初回セットアップ時や KMP 側のコードを変更した際には、以下のコマンドで KMP の iOS 用フレームワークをビルドしてください。

```shell
# リポジトリルートディレクトリに移動
cd ../../
# KMP の iOS 用フレームワークをビルド
./gradlew :app:ios-combined:assembleNitoCombinedReleaseXCFramework
```

## Xcode プロジェクトを開く

```shell
# Xcode プロジェクトを開く
open App/Nito/Nito.xcodeproj
```
