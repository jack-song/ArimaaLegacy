package email.com.gmail.songjiapei.arimaa;

import email.com.gmail.songjiapei.arimaa.GameEngine.GameState;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameActivity extends Activity {

	static final String TAG = "GameActivity";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		
		//load a game's data if possible
	    GameView gview = (GameView) findViewById(R.id.pieces);
	    SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
	    gview.loadGame(pref);
	    
	    
		//update the graphics in the game
		updateGraphics();
		updateStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		updateGraphics();
		updateStatus();
	}

	//do not allow any orientations other than portrait
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch (item.getItemId())
		{
		case R.id.new_option:
			GameView gview = (GameView) findViewById(R.id.pieces);
			gview.resetGame();
			gview.invalidate();
			updateStatus();
			break;
		
		case R.id.settings_option:
			openSettings();
			break;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		
		return true;
		
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    super.onSaveInstanceState(savedInstanceState);
	    
	    GameView gview = (GameView) findViewById(R.id.pieces);
	    
	    gview.saveGame(getPreferences(Context.MODE_PRIVATE));
	}

	public void updateStatus(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean twoview = pref.getBoolean("twoview", true);
		
		GameView gview = (GameView) findViewById(R.id.pieces);
		GameEngine.GameState state = gview.getState();
		int turnSteps = gview.getTurnSteps();
		
		switch(state){
		case GOLDTURN:
			setStatus("GOLD - " + (4 - turnSteps));
			setGoldButtonsMode();
			break;
			
		case SILVERTURN:
			setStatus("SILVER - " + (4 - turnSteps));
			setSilverButtonsMode();
			break;
			
		case GOLDPLACE:
			setStatus("GOLD SETUP");	
			setGoldButtonsMode();
			break;
			
		case SILVERPLACE:
			setStatus("SILVER SETUP");
			setSilverButtonsMode();
			break;
		case GAMEOVERGOLD:
			setStatus("GOLD WINS");	
			setNoButtonsMode();
			break;
		default:
			setStatus("SILVER WINS");
			setNoButtonsMode();
		}
		
		if(!twoview && state != GameState.GAMEOVERGOLD && state != GameState.GAMEOVERSILVER)
			setGoldButtonsMode();
	}
	
	public void setStatus(String status){
		TextView goldStatus = (TextView) findViewById(R.id.status);
		TextView silverStatus = (TextView) findViewById(R.id.ustatus);
		
		goldStatus.setText(status);
		silverStatus.setText(status);
	}
	
	public void setGoldButtonsMode(){
		Button goldDoneButton = (Button) findViewById(R.id.donebutton);
		Button goldBackButton = (Button) findViewById(R.id.backbutton);
		
		Button silverDoneButton = (Button) findViewById(R.id.udonebutton);
		Button silverBackButton = (Button) findViewById(R.id.ubackbutton);
				
		goldDoneButton.setEnabled(true);
		goldBackButton.setEnabled(true);
		
		silverDoneButton.setEnabled(false);
		silverBackButton.setEnabled(false);
	}
	
	public void setSilverButtonsMode(){
		Button goldDoneButton = (Button) findViewById(R.id.donebutton);
		Button goldBackButton = (Button) findViewById(R.id.backbutton);
		
		Button silverDoneButton = (Button) findViewById(R.id.udonebutton);
		Button silverBackButton = (Button) findViewById(R.id.ubackbutton);
		
		goldDoneButton.setEnabled(false);
		goldBackButton.setEnabled(false);
		
		silverDoneButton.setEnabled(true);
		silverBackButton.setEnabled(true);
		
	}
	
	public void setNoButtonsMode(){
		Button goldDoneButton = (Button) findViewById(R.id.donebutton);
		Button goldBackButton = (Button) findViewById(R.id.backbutton);
		
		Button silverDoneButton = (Button) findViewById(R.id.udonebutton);
		Button silverBackButton = (Button) findViewById(R.id.ubackbutton);
		
		Button goldStatus = (Button) findViewById(R.id.status);
		Button silverStatus = (Button) findViewById(R.id.ustatus);
		goldStatus.setBackgroundColor(Color.TRANSPARENT);
		silverStatus.setBackgroundColor(Color.TRANSPARENT);
		
		goldDoneButton.setEnabled(false);
		goldBackButton.setEnabled(false);
		
		silverDoneButton.setEnabled(false);
		silverBackButton.setEnabled(false);
	}
		
	public void openSettings(){
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	
	public void updateGraphics(){
		GameView gview = (GameView) findViewById(R.id.pieces);
		BackgroundView bview = (BackgroundView) findViewById(R.id.board);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		
    	boolean twoview = pref.getBoolean("twoview", true);
    	gview.setTwoView(twoview);
		
		ViewGroup top = (ViewGroup) findViewById(R.id.topsection);
		
		if(twoview){
			top.setVisibility(View.VISIBLE);
		}
		
		else{
			top.setVisibility(View.GONE);
		}
		
		if(gview.isTiled()){
			gview.updateGraphicsSelection(Integer.parseInt(pref.getString("pieceset_selection", "2")));
			bview.updateGraphicsSelection(Integer.parseInt(pref.getString("backset_selection", "1")));
		}
		
	}
		
	public void doneTurn(View v){
		GameView gview = (GameView) findViewById(R.id.pieces);
		gview.advanceState();
		updateStatus();
	}
	
	public void backTurn(View v){
		GameView gview = (GameView) findViewById(R.id.pieces);
		gview.revertMove();
		updateStatus();
		gview.invalidate();
	}
	


}
