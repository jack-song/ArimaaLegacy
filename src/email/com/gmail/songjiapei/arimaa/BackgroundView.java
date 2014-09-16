package email.com.gmail.songjiapei.arimaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class BackgroundView extends View {
	
	//bitmap of background
	private Bitmap back_map;
	
	//user's choice of which background image to use
	protected int backset = 1;
	
	//size of background image, currently use as a SQUARE
	int width, height;
	
	//tie background image choice to user selection in preferences
	public BackgroundView(Context context) {
		super(context);
		initialize(context);
	}

	public BackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}

	public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
	}
	
	private void initialize(Context context){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		backset = Integer.parseInt(pref.getString("backset_selection", "1"));
	}

	//get image size
	@Override
	protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
		super.onSizeChanged(xNew, yNew, xOld, yOld);
		
		//make sure it's a square background
		width = xNew;
		height = width;
	}
	
	//set to square view, based on height
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		//first update the image selection for background
		updateGraphicsSelection (backset);
		
		//draw the background
		canvas.drawBitmap(back_map, 0, 0, null);

	}
	
	public void updateGraphicsSelection(int new_backset_selection){
		
		//if no changes were made, do not do anything
		//special case: if the background bitmap has not been initialized
		if(new_backset_selection == backset && back_map != null)
			return;
		
		Bitmap pre_board = back_map;
		backset = new_backset_selection;
		
		switch(new_backset_selection){
		case 1:
			pre_board = BitmapFactory.decodeResource(getResources(), R.drawable.traditional);
			break;

		case 2:
			pre_board = BitmapFactory.decodeResource(getResources(), R.drawable.stone);
			break;
		case 3:
			pre_board = BitmapFactory.decodeResource(getResources(), R.drawable.minimalistdark);
			break;
		default:
			pre_board = BitmapFactory.decodeResource(getResources(), R.drawable.minimalistlight);
			break;
		}
		
		//resize the bitmap to the size of the background
		back_map = Bitmap.createScaledBitmap(pre_board, width, height, true);
		
		pre_board.recycle();
	}

}
