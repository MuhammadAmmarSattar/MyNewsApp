object DaggerHilt {
    const val version = "2.44"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"


    private const val hiltCompilerTest1= "1.0.0"
    const val hiltCompilerTest = "androidx.hilt:hilt-compiler:${hiltCompilerTest1}"
}