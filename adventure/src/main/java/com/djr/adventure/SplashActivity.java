package com.djr.adventure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private TextView mTextViewTop;
    private TextView mTextViewBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mTextViewTop = (TextView)findViewById(R.id.text_top_splash);
        mTextViewBottom = (TextView)findViewById(R.id.text_bottom_splash);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        mTextViewTop.setTypeface(roboto);
        mTextViewBottom.setTypeface(roboto);


		Thread timer  = new Thread() {
			public void run() {
				try {
					sleep(3000);
					Intent intent = new Intent(SplashActivity.this,YelpActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
				}
				catch(InterruptedException ie) {
						ie.printStackTrace();
				}
			}
		};	
		timer.start();
		
	}
		
}
