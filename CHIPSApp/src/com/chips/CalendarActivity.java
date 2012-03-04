package com.chips;

import com.chips.gsCalendar.gsCalendarColorParam;
import com.chips.homebar.HomeBar;
import com.chips.homebar.HomeBarAction;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
		

public class CalendarActivity extends Activity implements OnClickListener 
{
	 
	TextView tvs[] ;
	Button btns[] ;
    

	class myGsCalendar extends gsCalendar
	{

		public myGsCalendar(Context context, LinearLayout layout) 
		{
			super(context, layout);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void myClickEvent(int yyyy, int MM, int dd) 
		{
			// TODO Auto-generated method stub
			
			cal.redraw( ) ;
			
			cal.applyHoliday( ) ;
			
	        cal.setSelectedDay( CalendarActivity.this.getResources( ).getDrawable( R.drawable.icon ) ) ;
	        
	        cal.setSelectedDayTextColor( 0xff009999 ) ;
			
			super.myClickEvent(yyyy, MM, dd);
		}
		
	}
	
	
	myGsCalendar cal ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        //HomeBarAction.inflateHomeBarView(this, R.layout.inventory);
        
        LinearLayout lv = (LinearLayout)findViewById( R.id.calendar_lLayout ) ;
        
        tvs = new TextView[3] ;
        tvs[0] = (TextView)findViewById( R.id.tv1 ) ;
        tvs[1] = (TextView)findViewById( R.id.tv2 ) ;
        tvs[2] = null ; 
        
        btns = new Button[4] ;
        btns[0] = null ; 
        btns[1] = null ; 
        btns[2] = (Button)findViewById( R.id.Button03 ) ;
        btns[3] = (Button)findViewById( R.id.Button04 ) ;
        
        cal = new myGsCalendar( this, lv ) ;
        
        gsCalendarColorParam cParam = new gsCalendarColorParam( ) ;
        
        cParam.m_cellColor = 0x00000000 ;
        cParam.m_textColor = 0xffffffff ;
        cParam.m_saturdayTextColor = 0xff33ccff ;
        cParam.m_lineColor = 0x99999999 ;
        cParam.m_topCellColor = 0xff003333 ;
        cParam.m_topTextColor = 0xffffffff ;
        cParam.m_topSundayTextColor = 0xffffffff ;
        cParam.m_topSaturdatTextColor = 0xffffffff ;
        
        cal.setColorParam( cParam ) ;
        
        Drawable img = getResources( ).getDrawable( R.drawable.bg ) ;
        cal.setBackground( img ) ;
        
        cal.setCalendarSize( 478, 600 ) ;
        
        cal.setTopCellSize( 35 ) ;
        
        cal.setControl( btns ) ;
        
        cal.setViewTarget( tvs ) ;
        
        cal.initCalendar( ) ;
        
        cal.setSelectedDay( getResources( ).getDrawable( R.drawable.icon ) ) ;
        
        cal.setSelectedDayTextColor( 0xff009999 ) ;
        
        //cal.setSelectedDay( 0xff000000, 0xffffffff ) ;
        
        cal.addHoliday( 324 ) ;
        
        cal.applyHoliday( ) ;	        
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}