package com.cncom.app.library.appcompat.widget;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.cncom.app.library.appcompat.R;


/**
 * Created by bestjoy on 15/6/16.
 */
public class SwitchPreferenceCompat extends CheckBoxPreference {
    private SwitchCompat mSwitchCompat;
    public SwitchPreferenceCompat(Context context) {
        super(context);
        init();
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//    public void setSwitchPreferenceCompatChecked(boolean checked) {
//        mSwitchCompat.setChecked(checked);
//    }

    private void init() {
        setWidgetLayoutResource(R.layout.preference_widget_switchcompat);
    }

    @Override

    protected void onBindView(View view) {
        super.onBindView(view);
        mSwitchCompat.setOnCheckedChangeListener(null);
        mSwitchCompat.setChecked(isChecked());

        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (callChangeListener(isChecked)) {
                    SwitchPreferenceCompat.this.setChecked(isChecked);
                }
            }

        });

    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        View view =  super.onCreateView(parent);
        mSwitchCompat = (SwitchCompat) view.findViewById(R.id.switchCompatWidget);
        return view;
    }

    public SwitchCompat getmSwitchCompat() {
        return mSwitchCompat;
    }

}
