package com.nati.cameratest;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
	Button cameraButton;
	SurfaceView cameraSurface;
	Camera camera;
	boolean previewing;
	SurfaceHolder surfaceHolder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	void init()
	{
		cameraButton=(Button)findViewById(R.id.button1);
		cameraSurface=(SurfaceView)findViewById(R.id.cameraSurface);
		surfaceHolder = cameraSurface.getHolder();
        surfaceHolder.addCallback(this);
        
        cameraButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(previewing)
				{
					camera.stopPreview();
					previewing=false;
				}
				else
				{
					try {
						camera.setPreviewDisplay(surfaceHolder);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                camera.startPreview();
					previewing=true;
				}
			}
		});
        
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if(previewing){
            camera.stopPreview();
            previewing = false;
        }
 
        if (camera != null){
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                previewing = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera = Camera.open();
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.stopPreview();
        camera.release();
        camera = null;
        previewing = false;
		
	}

	

}
