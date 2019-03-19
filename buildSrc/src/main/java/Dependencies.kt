object Modules {
    val core = ":core"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.2.0"

    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28

    val googleServices = "4.2.0"
    val firebase = "16.0.4"
    val googleAuth = "16.0.1"

    val fabric = "1.26.1"

    val appcompat = "1.0.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val constraintLayout = "1.1.2"
    val fragment = "1.0.0"
    val animatedVectorDrawable = "1.0.0"
    val multidex = "2.0.0"
    val androidArch = "2.0.0"

    val kotlin = "1.3.11"
    val ktx = "1.0.0"
    val kotlinCoroutines = "1.0.1"

    val rxJava = "2.2.4"
    val rxAndroid = "2.1.0"
    val rxkotlin = "2.3.0"

    val dagger = "2.16"
    val gson = "2.8.5"

    val retrofit = "2.5.0"
    val retrofitRxAdapter = "1.0.0"
    val okhttp = "3.9.0"
    val okhttpDownloader = "1.1.0"
    val picasso = "2.5.2"

    val loggingInterceptor = "3.12.0"
    val glide = "4.8.0"
    val rxpaper = "1.2.0"
    val lifecycle = "2.0.0"
    val leakCanary = "1.6.2"
    val crashlytics = "2.9.6"
    val koin = "1.0.2"

    val junit = "4.12"
    val testRunner = "1.1.0"
    val espresso = "3.1.0"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.23.4"
}

object AndroidLibs {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val animatedVectorDrawable = "androidx.vectordrawable:vectordrawable-animated:${Versions.animatedVectorDrawable}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"
}

object AndroidArchLibs {
    val core = "androidx.arch.core:core:${Versions.androidArch}"
    val coreCommon = "androidx.arch.core:core-common:${Versions.androidArch}"
    val coreRuntime = "androidx.arch.core:core-runtime:${Versions.androidArch}"
    val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidArch}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidArch}"
    val liveData = "androidx.lifecycle:lifecycle-livedata-core:${Versions.androidArch}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.androidArch}"
    val paggingCommon = "androidx.paging:paging-common:${Versions.androidArch}"
    val paggingRuntime = "androidx.paging:paging-runtime:${Versions.androidArch}"
    val paggingRxjava2 = "androidx.paging:paging-rxjava2:${Versions.androidArch}"
    val roomCommon = "androidx.room:room-common:${Versions.androidArch}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.androidArch}"
    val roomMigration = "androidx.room:room-migration:${Versions.androidArch}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.androidArch}"
    val roomRxjava2 = "androidx.room:room-rxjava2:${Versions.androidArch}"
    val roomTesting = "androidx.room:room-testing:${Versions.androidArch}"
    val dataBinding = "com.android.databinding:compiler:${Versions.gradle}"
}

object KotlinLibs {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

}

object ReactiveXLibs {
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
}

object SquareLibs {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    val okhttpDownloader = "com.jakewharton.picasso:picasso2-okhttp3-downloader:${Versions.okhttpDownloader}"
    val retrofitRxAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofitRxAdapter}"
}

object GoogleLibs {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object FirebaseLibs {
    val auth = "com.google.firebase:firebase-auth:16.2.0"
    val core = "com.google.firebase:firebase-core:16.0.8"
}

object TestLibs {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object FacebookLibs{
    val fresco  = "com.facebook.fresco:fresco:1.12.1"
}

object ThirdLibs{
    val foldingCell = "com.ramotion.foldingcell:folding-cell:1.2.3"
}