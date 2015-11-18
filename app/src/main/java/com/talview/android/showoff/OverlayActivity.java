package com.talview.android.showoff;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.talview.android.showoff.R;

import java.util.ArrayList;

public class OverlayActivity extends AppCompatActivity {

    int arrowwidth = 60;
    int arrowHeight = 120;
    int horizontal_arrow_width = 120;
    int horizontal_arrow_height = 60;
    private RelativeLayout parent;
    static ArrayList<ToolTip> mLocations = new ArrayList<ToolTip>();
    int screenHeight = 0;
    int screenWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);
        parent = (RelativeLayout) findViewById(R.id.parent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;


        addTooltip(mLocations);
    }

    private void addTooltip(ArrayList<ToolTip> toolTips) {
        for (ToolTip toolTip : toolTips) {
//
            if (toolTip.getPosition() == ToolTip.POSITION_BOTTOM) {
                addTooltipBelow(toolTip);
            } else if (toolTip.getPosition() == ToolTip.POSITION_TOP) {
                addTooltipAbove(toolTip);
            } else if (toolTip.getPosition() == ToolTip.POSITION_RIGHT) {
                addTooltipRight(toolTip);
            } else if (toolTip.getPosition() == ToolTip.POSITION_LEFT) {
                addTooltipLeft(toolTip);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overlay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    static void createTooltip(View view, int position, int gravity) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        ToolTip toolTip = new ToolTip(location);
        toolTip.setPosition(position);
        toolTip.setDescriptionGravity(gravity);
        toolTip.setVertical_padding(view.getHeight());
        toolTip.setHorizontal_padding(view.getWidth());
        mLocations.add(toolTip);
    }


    private void addTooltipBelow(ToolTip toolTip) {

        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.right_tilt1);
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(60, 120);
        parms.setMargins(toolTip.getX(), toolTip.getY() + toolTip.getVertical_padding() / 2, 0, 0);
        iv.setLayoutParams(parms);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt = new TextView(this);
        txt.setText(toolTip.getDescription());
        txt.setTypeface(null, Typeface.ITALIC);
        txt.setTextColor(Color.WHITE);
        txt.setGravity(toolTip.getDescriptionGravity());
        txt.setPadding(20, toolTip.getY() + arrowHeight + toolTip.getVertical_padding() / 2, 20, 0);
        parent.addView(iv);
        parent.addView(txt);

    }

    private void addTooltipAbove(ToolTip toolTip) {

        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.right_tilt1);
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(arrowwidth, arrowHeight);
        parms.setMargins(toolTip.getX(), toolTip.getY() - arrowHeight - toolTip.getVertical_padding() / 2, 0, 0);
        iv.setLayoutParams(parms);
        iv.setRotation(180);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt = new TextView(this);
        RelativeLayout.LayoutParams txtParms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtParms.setMargins(20, toolTip.getY() - arrowHeight - toolTip.getVertical_padding(), 20, 0);
        txt.setText(toolTip.getDescription());
        txt.setTypeface(null, Typeface.ITALIC);
        txt.setTextColor(Color.WHITE);
        txt.setGravity(toolTip.getDescriptionGravity());
        txt.setTextColor(Color.WHITE);
        txt.setLayoutParams(txtParms);
        parent.addView(iv);
        parent.addView(txt);

    }

    private void addTooltipRight(ToolTip toolTip) {

        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.arrow_straight_small);
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(arrowwidth, arrowHeight);
        parms.setMargins(toolTip.getX() + toolTip.getHorizontal_padding(), toolTip.getY() - horizontal_arrow_height, 0, 0);
        iv.setLayoutParams(parms);
        iv.setRotation(270);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt = new TextView(this);
        RelativeLayout.LayoutParams txtParms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtParms.setMargins(toolTip.getX() + toolTip.getHorizontal_padding() + horizontal_arrow_height, toolTip.getY() - iv.getHeight(), 20, 0);
        txt.setText(toolTip.getDescription());
        txt.setTypeface(null, Typeface.ITALIC);
        txt.setTextColor(Color.WHITE);
        txt.setGravity(toolTip.getDescriptionGravity());
        txt.setTextColor(Color.WHITE);
        txt.setLayoutParams(txtParms);
        parent.addView(iv);
        parent.addView(txt);

    }


    private void addTooltipLeft(ToolTip toolTip) {

        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.arrow_straight_small);
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(arrowwidth, arrowHeight);
        parms.setMargins(toolTip.getX()-horizontal_arrow_width, toolTip.getY() - horizontal_arrow_height, 0 , 0);
        iv.setLayoutParams(parms);
        iv.setRotation(90);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView txt = new TextView(this);
        RelativeLayout.LayoutParams txtParms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtParms.setMargins(20, toolTip.getY() - horizontal_arrow_height, toolTip.getX()  , 0);
        txt.setText(toolTip.getDescription());
        txt.setTypeface(null, Typeface.ITALIC);
        txt.setTextColor(Color.WHITE);
        txt.setGravity(toolTip.getDescriptionGravity());
        txt.setTextColor(Color.WHITE);
        txt.setLayoutParams(txtParms);
        parent.addView(iv);
        parent.addView(txt);

    }

    static class ToolTip {
        public static final int POSITION_TOP = 0;
        public static final int POSITION_RIGHT = 1;
        public static final int POSITION_BOTTOM = 2;
        public static final int POSITION_LEFT = 3;
        int vertical_padding = 30;
        int horizontal_padding = 150;
        int x;
        int y;
        String description = "apparently you forgot to describe... this is a tutorial afterall !!!";
        int descriptionGravity = Gravity.LEFT;
        int position = 0;

        public ToolTip(int[] location) {
            x = location[0];
            y = location[1];
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getVertical_padding() {
            return vertical_padding;
        }

        public void setVertical_padding(int vertical_padding) {
            this.vertical_padding = vertical_padding;
        }

        public int getDescriptionGravity() {
            return descriptionGravity;
        }

        public void setDescriptionGravity(int descriptionGravity) {
            this.descriptionGravity = descriptionGravity;
        }

        public int getHorizontal_padding() {
            return horizontal_padding;
        }

        public void setHorizontal_padding(int horizontal_padding) {
            this.horizontal_padding = horizontal_padding;
        }
    }
}
