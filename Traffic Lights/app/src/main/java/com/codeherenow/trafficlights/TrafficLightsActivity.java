/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.codeherenow.trafficlights;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TrafficLightsActivity extends Activity implements OnClickListener {

	private ImageView redlight;
	private ImageView yellowlight;
	private ImageView greenlight;

	private Button red;
	private Button yellow;
	private Button green;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.traffic_lights);


        redlight=(ImageView)findViewById(R.id.Red_light);
		yellowlight=(ImageView)findViewById(R.id.Yellow_light);
		greenlight=(ImageView)findViewById(R.id.Green_light);

		red=(Button)findViewById(R.id.red_button);
		yellow=(Button)findViewById(R.id.Yellow_button);
        green=(Button)findViewById(R.id.Green_button);

		red.setOnClickListener(this);
		green.setOnClickListener(this);
		yellow.setOnClickListener(this);

		redlight.setOnClickListener(this);
		yellowlight.setOnClickListener(this);
		greenlight.setOnClickListener(this);

	}

	public void onClick(View v)
	{
		redlight.setImageResource(R.drawable.light_off);
		yellowlight.setImageResource(R.drawable.light_off);
		greenlight.setImageResource(R.drawable.light_off);

		if (v==red || v== redlight)
		{
			glowRed();
		}

		else if (v==yellow ||v== yellowlight)
		{
			glowYellow();
		}

		else if(v==green ||v== greenlight)
		{
			glowGreen();
		}
	}

	public void glowRed()
	{
		redlight.setImageResource(R.drawable.red_on);
	}

	public void glowYellow()
	{
		yellowlight.setImageResource(R.drawable.yellow_on);
	}
    public void glowGreen()
	{
		greenlight.setImageResource(R.drawable.green_on);
	}

}
