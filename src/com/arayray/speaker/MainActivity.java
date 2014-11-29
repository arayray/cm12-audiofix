package com.arayray.speaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MainActivity mContext;
	private PackageManager mPackageManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = this;
		mPackageManager = getPackageManager();
		
		Button buttonStart = (Button) this.findViewById(R.id.buttonStart);
		buttonStart.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        Intent serviceIntent = new Intent(mContext, SpeakerFix.class);
		        mContext.startService(serviceIntent);
		        
		        // NOTIFY USER
		        Toast.makeText(mContext, "Service started.", Toast.LENGTH_SHORT).show();
		        
		        showDialog();
		    }
		});
		
		/*
		Button buttonEnd = (Button) this.findViewById(R.id.buttonEnd);
		buttonEnd.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        Intent serviceIntent = new Intent(mContext, SpeakerFix.class);
		        Log.d("MotoXEarpiece", "startService!");
		        mContext.stopService(serviceIntent);
		    }
		});
		*/
	}
	
	public void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);

		dialog
				.setMessage("This app's icon will be removed from the launcher and the app will now run in the background, no further input is needed.")
				.setCancelable(false)
				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						mPackageManager.setComponentEnabledSetting(getComponentName(), 
								PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
						mContext.finish();
					}
				}).create();
		dialog.show();
	}
}