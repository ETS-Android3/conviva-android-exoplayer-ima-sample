#!/bin/sh

rm -rf build
mkdir build
chmod +x ./gradlew
./gradlew clean


./gradlew :assembleRelease --refresh-dependencies
cp build/outputs/apk/release/project-release.apk ./artifacts/conviva-android-exoplayer-ima-sample-release-$CIRCLE_TAG.$CIRCLE_BUILD_NUM.apk

./gradlew :assembleDebug
cp build/outputs/apk/debug/project-debug.apk ./artifacts/conviva-android-exoplayer-ima-sample-debug-$CIRCLE_TAG.$CIRCLE_BUILD_NUM.apk