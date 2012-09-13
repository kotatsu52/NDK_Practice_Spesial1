#include <math.h>
#include <time.h>
#include <jni.h>
#include <android/log.h>

float
Java_my_kotatsu_ndk_1spesial1_CircleView_circle_1xy( JNIEnv* env, jobject thiz, float *xbuf, float *ybuf )
{
	int i = 0;
	clock_t start,stop;
	float *x  =  (*env)->GetFloatArrayElements(env,xbuf,0);
	float *y  =  (*env)->GetFloatArrayElements(env,ybuf,0);
    float r = 100.0f;
    float x_offset = 150.0f, y_offset = 400.0f;

	start = clock();
    //★円座標取得処理

	stop = clock();

	(*env)->ReleaseFloatArrayElements(env,xbuf,x,0);
	(*env)->ReleaseFloatArrayElements(env,ybuf,y,0);

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);
}

