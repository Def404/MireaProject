package ru.mirea.baskakov.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SensorFragment extends Fragment implements SensorEventListener {

    private TextView textViewGravityOX;
    private TextView textViewGravityOY;
    private TextView textViewGravityOZ;

    private TextView textViewTemp;

    private TextView textViewMagOX;
    private TextView textViewMagOY;
    private TextView textViewMagOZ;

    private SensorManager sensorManager;

    private Sensor gravitySensor;
    private Sensor tempSensor;
    private Sensor magSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        textViewGravityOX = view.findViewById(R.id.textViewGravityOX);
        textViewGravityOY = view.findViewById(R.id.textViewGravityOY);
        textViewGravityOZ = view.findViewById(R.id.textViewGravityOZ);
        textViewTemp = view.findViewById(R.id.textViewTemp);
        textViewMagOX = view.findViewById(R.id.textViewMagOX);
        textViewMagOY = view.findViewById(R.id.textViewMagOY);
        textViewMagOZ = view.findViewById(R.id.textViewMagOZ);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY){

            float valueOX = sensorEvent.values[0];
            float valueOY = sensorEvent.values[1];
            float valueOZ = sensorEvent.values[2];

            textViewGravityOX.setText("OX: "  + valueOX);
            textViewGravityOY.setText("OY: "  + valueOY);
            textViewGravityOZ.setText("OZ: "  + valueOZ);
        } else if(sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){

            float valueTemp = sensorEvent.values[0];

            textViewTemp.setText("Temp: " + valueTemp);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){

            float valueOX = sensorEvent.values[0];
            float valueOY = sensorEvent.values[1];
            float valueOZ = sensorEvent.values[2];

            textViewMagOX.setText("OX: "  + valueOX);
            textViewMagOY.setText("OY: "  + valueOY);
            textViewMagOZ.setText("OZ: "  + valueOZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}