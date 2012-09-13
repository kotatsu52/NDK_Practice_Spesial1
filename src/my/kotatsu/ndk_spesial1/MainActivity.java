package my.kotatsu.ndk_spesial1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    android.os.Handler handler = new android.os.Handler();
    CircleView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = new CircleView(getApplication());
        // View クラスのインスタンスを生成する
        // View に設定する
        setContentView(view);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}


//表示用のクラス
class CircleView extends View {

	 // View の初期化
	 public CircleView(Context context) {
		 super(context);
		 setFocusable(true);
	 }


	 // 実際に描画を行うメソッド
	 protected void onDraw(Canvas canvas) {
	     float[] x = new float[3600];
	     float[] y = new float[3600];
	     int i = 0;
	     float r = 100.0f;
	     float x_offset = 150.0f, y_offset = 150.0f;
	     float time;
		 Path path = new Path();
		 Paint paint = new Paint();
	     paint.setColor(Color.BLACK);
	     paint.setStyle(Paint.Style.FILL);
	     paint.setStrokeWidth(16.0f);
	     paint.setAntiAlias(true);

	     //---------------------------//
	     // JAVA
	     //---------------------------//
	     long start = System.currentTimeMillis();
	     //円座標取得処理
	     for( i = 0 ; i < 3600 ; i++ ){
	    	 x[i] = r * (float)Math.cos(2*Math.PI*(double)i/3600.0f) + x_offset;
	    	 y[i] = r * (float)Math.sin(2*Math.PI*(double)i/3600.0f) + y_offset;
	     }
	     long stop = System.currentTimeMillis();
	     paint.setTextSize(30);
	     canvas.drawText("JAVA処理速度:"+(stop-start)+"ms", 0, 30, paint);
	     for( i = 0 ; i < 3600 ; i++ ){
	    	 if( i == 0 ){
	    		 path.moveTo(x[i], y[i]);
	    	 }else{
	    		 path.lineTo(x[i], y[i]);
	    	 }
	     }
	     canvas.drawPath(path, paint);
	     
	     //---------------------------//
	     // NDK
	     //---------------------------//
	     time = circle_xy(x,y);	     
	     canvas.drawText("NDK処理速度:"+time+"ms", 0, 280, paint);
	     for( i = 0 ; i < 3600 ; i++ ){
	    	 if( i == 0 ){
	    		 path.moveTo(x[i], y[i]);
	    	 }else{
	    		 path.lineTo(x[i], y[i]);
	    	 }
	     }
	     canvas.drawPath(path, paint);

	 }
	    public native float circle_xy(float[] x, float[] y);

	    static {
	        System.loadLibrary("ndk_practice");
	    }
}