package com.tapcard;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    int windowwidth;
    int screenCenter;
    float x_cord, y_cord, x, y;
    int Likes = 0;
    RelativeLayout parentView;
    float alphaValue = 0;
    private Context m_context;
    Animation animation;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_context = MainActivity.this;
        parentView = (RelativeLayout) findViewById(R.id.layoutview);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        screenCenter = windowwidth / 2;
        final int[] myImageList = new int[]{R.drawable.user, R.drawable.doctor, R.drawable.logo,
                R.drawable.cats, R.drawable.puppy};

        LayoutInflater inflate = (LayoutInflater) m_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Log.e("123", "i" + i);
        final View m_view = inflate.inflate(R.layout.layout, null);
        animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animation.setAnimationListener(new Animation.AnimationListener() {
            int finalI = 0;

            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("123", "start" + finalI);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (finalI < 4) {
                    Log.e("123", "end" + finalI);
                    finalI++;
                    LayoutInflater inflate = (LayoutInflater) m_context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    //Log.e("123", "i" + i);
                    final View m_view = inflate.inflate(R.layout.layout, null);

                    final ImageView m_image = (ImageView) m_view.findViewById(R.id.sp_image);
                    LinearLayout m_topLayout = (LinearLayout) m_view.findViewById(R.id.sp_color);
                    //LinearLayout m_bottomLayout = (LinearLayout) m_view.findViewById(R.id.sp_linh);
                    // final RelativeLayout myRelView = new RelativeLayout(this);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        m_view.setLayoutParams(new LinearLayout.LayoutParams((windowwidth - 500), getWindowManager().getDefaultDisplay().getHeight()-50));
                        m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
                        m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));
                    } else {
                        m_view.setLayoutParams(new LinearLayout.LayoutParams((windowwidth - 80), (int) (0.75*getWindowManager().getDefaultDisplay().getHeight())));
                        m_view.setX(40);
                        m_view.setY((float) (0.15*getWindowManager().getDefaultDisplay().getHeight()));
                    }

                    m_view.setTag(i);
                    m_image.setBackgroundResource(myImageList[finalI]);
                    m_image.startAnimation(animation);
                    m_topLayout.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            x_cord = event.getRawX();
                            y_cord = event.getRawY();
                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
                                m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));

                            }
                            else
                            {
                                m_view.setX(40);
                                m_view.setY((float) (0.15*getWindowManager().getDefaultDisplay().getHeight()));
                            }

                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    x = event.getX();
                                    y = event.getY();
                                    // Log.v("On touch", x + " " + y);

                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    x_cord = event.getRawX();
                                    y_cord = event.getRawY();
                                    m_view.setX(x_cord - x);
                                    m_view.setY(y_cord - y);
                                    m_view.setY(y_cord - y);
                                    y_cord = (int) event.getRawY();
                                    m_view.setX(x_cord - screenCenter + 40);
                                    m_view.setY(y_cord - 150);
                                    if (x_cord >= screenCenter) {
                                        m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                        if (x_cord > (screenCenter + (screenCenter / 2))) {
                                            //    imageLike.setAlpha(1);
                                            if (x_cord > (windowwidth - (screenCenter / 4))) {
                                                Likes = 2;
                                            } else {
                                                Likes = 0;
                                            }
                                        } else {
                                            Likes = 0;
                                        }
                                    } else {
                                        // rotate
                                        m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                        if (x_cord < (screenCenter / 2)) {
                                            //        imagePass.setAlpha(1);
                                            if (x_cord < screenCenter / 4) {
                                                Likes = 1;
                                            } else {
                                                Likes = 0;
                                            }
                                        } else {
                                            Likes = 0;
                                            //       imagePass.setAlpha(0);
                                        }
                                        //       imageLike.setAlpha(0);
                                    }
                                    break;
                                case MotionEvent.ACTION_UP:
                                    x_cord = event.getRawX();
                                    y_cord = event.getRawY();

                                    Log.e("X Point", "" + x_cord + " , Y " + y_cord);

                                    if (Likes == 0) {
                                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                            m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
                                            m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));
                                        }
                                        else
                                        {
                                            m_view.setX(40);
                                            m_view.setY((float) (0.15*getWindowManager().getDefaultDisplay().getHeight()));
                                        }
                                        m_view.setRotation(0);
                                    } else if (Likes == 1) {
                                        parentView.removeView(m_view);
                                        if(parentView.getChildCount()!=0)
                                        parentView.getChildAt(parentView.getChildCount()-1).setRotation(0);

                                    } else if (Likes == 2) {

                                        parentView.removeView(m_view);
                                        if(parentView.getChildCount()!=0)
                                        parentView.getChildAt(parentView.getChildCount()-1).setRotation(0);

                                    }
                                    break;
                                default:
                                    break;
                            }
                            return true;
                        }
                    });

                    parentView.addView(m_view);
                    if (finalI == 0) {
                        m_view.setRotation(-1);
                    } else if (finalI == 1) {
                        m_view.setRotation(-5);

                    } else if (finalI == 2) {
                        m_view.setRotation(3);

                    } else if (finalI == 3) {
                        m_view.setRotation(7);

                    } else if (finalI == 4) {
                        m_view.setRotation(-2);

                    } else if (finalI == 5) {
                        m_view.setRotation(5);

                    }

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        final ImageView m_image = (ImageView) m_view.findViewById(R.id.sp_image);
        m_image.startAnimation(animation);

        LinearLayout m_topLayout = (LinearLayout) m_view.findViewById(R.id.sp_color);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            m_view.setLayoutParams(new LinearLayout.LayoutParams((windowwidth - 500), getWindowManager().getDefaultDisplay().getHeight()-50));
            m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
            m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));

        }  else {
            m_view.setLayoutParams(new LinearLayout.LayoutParams((windowwidth - 80), (int) (0.75*getWindowManager().getDefaultDisplay().getHeight())));
            m_view.setX(40);
            m_view.setY((float) (0.15*getWindowManager().getDefaultDisplay().getHeight()));
        }
        m_image.setBackgroundResource(myImageList[0]);

        m_topLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x_cord = event.getRawX();
                y_cord = event.getRawY();

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
                    m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));
                }
                else
                {
                    m_view.setX(40);
                    m_view.setY(250);
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        x_cord = event.getRawX();
                        y_cord = event.getRawY();
                        m_view.setX(x_cord - x);
                        m_view.setY(y_cord - y);
                        y_cord = (int) event.getRawY();
                        m_view.setX(x_cord - screenCenter + 40);
                        m_view.setY(y_cord - 150);
                        if (x_cord >= screenCenter) {
                            m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                            if (x_cord > (screenCenter + (screenCenter / 2))) {
                                //    imageLike.setAlpha(1);
                                if (x_cord > (windowwidth - (screenCenter / 4))) {
                                    Likes = 2;
                                } else {
                                    Likes = 0;
                                }
                            } else {
                                Likes = 0;
                            }
                        } else {
                            // rotate
                            m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                            if (x_cord < (screenCenter / 2)) {
                                //        imagePass.setAlpha(1);
                                if (x_cord < screenCenter / 4) {
                                    Likes = 1;
                                } else {
                                    Likes = 0;
                                }
                            } else {
                                Likes = 0;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        x_cord = event.getRawX();
                        y_cord = event.getRawY();

//                        Log.e("X Point", "" + x_cord + " , Y " + y_cord);

                        if (Likes == 0) {
                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                m_view.setX((float) 0.35*(getWindowManager().getDefaultDisplay().getHeight()));
                                m_view.setY((getWindowManager().getDefaultDisplay().getHeight()/2)-(getWindowManager().getDefaultDisplay().getHeight()/2-30));

                            }
                            else
                            {
                                m_view.setX(40);
                                m_view.setY(250);
                            }
                            m_view.setRotation(0);
                        } else if (Likes == 1) {
                            parentView.removeView(m_view);
                            if(parentView.getChildCount()!=0)
                            parentView.getChildAt(parentView.getChildCount()-1).setRotation(0);

                        } else if (Likes == 2) {
                            parentView.removeView(m_view);
                            if(parentView.getChildCount()!=0)
                            parentView.getChildAt(parentView.getChildCount()-1).setRotation(0);

                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        parentView.addView(m_view);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
