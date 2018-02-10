package com.asdf.utils;

/**
 * Created by hasee on 2018/2/3.
 */


public class OpenCVJniHelper {
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("opencv_java");
        //不依赖opencv manager则必须加载上面两个库,否则找不到
        System.loadLibrary("native-lib");
    }
    /**
     *
     * @param buf
     * @param w
     * @param y
     */
    public native static int[] jni_toGray(int[] buf, int w, int y);
}
