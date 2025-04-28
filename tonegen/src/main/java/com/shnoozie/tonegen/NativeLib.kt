package com.shnoozie.tonegen

class NativeLib {

    /**
     * A native method that is implemented by the 'tonegen' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'tonegen' library on application startup.
        init {
            System.loadLibrary("tonegen")
        }
    }
}