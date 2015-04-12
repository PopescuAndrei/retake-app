package com.retake.retakeapp.main;

import java.io.IOException;
import java.util.ArrayList;

import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retakeapp.R;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconManager;
import com.retake.retakeapp.beacon.fragments.BeaconFragment;
import com.retake.retakeapp.beacon.fragments.CouponFragment;
import com.retake.retakeapp.beacon.logging.LogDialogFragment;
import com.retake.retakeapp.beacon.logging.MyLogger;
import com.retake.retakeapp.map.FragmentMap;
import com.retake.retakeapp.notifications.NotificationFragment;
import com.retake.retakeapp.schedule.ScheduleFragment;
import com.retake.retakeapp.streaming.StreamingFragment;
import com.retake.retakeapp.tournaments.TournamentsFragment;
import com.retake.retakeapp.tournaments.TournamentsListFragment;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity implements
		LogDialogFragment.LogDialogListener {
	private DrawerLayout mDrawerLayout;

	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	private ScheduleFragment fragmentSchedule;
	private FragmentMap fragmentMap;
	private StreamingFragment fragmentStreaming;
	private NotificationFragment fragmentNotification;
	private AchievementsFragment fragmentAchievements;
	private TournamentsListFragment fragmentTournaments;
	private HomeFragment fragmentHome;
	private BeaconFragment fragmentBeacon;
	private CouponFragment fragmentCoupon;
	private TournamentsFragment tournamentFragment;

	public static final String LOGGER_KEY = "LoggerKey";
	private MyLogger mLogger;
	public static final String EXTRA_COUPONS = "coupons";
	private OnyxBeaconManager mManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		// What's hot, We will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1)));
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

		try {
			mLogger = new MyLogger(this);
		} catch (IOException exception) {
			mLogger = null;
		}

		Bundle bundle = new Bundle();
		bundle.putSerializable(LOGGER_KEY, mLogger);

		// Initialize OnyxBeaconManager
		mManager = OnyxBeaconApplication.getOnyxBeaconManager(this);
		// Enable beacons and coupons retrieval
		mManager.setCouponEnabled(true);
		mManager.setAPIContentEnabled(true);
		mManager.setLogger(mLogger);

	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		this.invalidateOptionsMenu();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		// case R.id.action_settings:
		// return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		switch (position) {
		case 0:
			launchHome(0);
			break;
		case 1:
			launchMap(1);
			break;
		case 2:
			launchAchievements(2);
			break;
		case 3:
			launchCoupons(3);
			break;
		case 4:
			launchSchedule(4);
			break;
		case 5:
			launchTournaments(5);
			break;
		case 6:
			launchStreaming(6);
			break;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void launchSchedule(int position) {
		if (fragmentSchedule == null) {
			fragmentSchedule = new ScheduleFragment();
		}

		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentSchedule) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentSchedule.isAdded()) {
				transaction.add(R.id.frame_container, fragmentSchedule);
			} else {
				transaction.show(fragmentSchedule);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchMap(int position) {
		if (fragmentMap == null) {
			fragmentMap = new FragmentMap();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentMap) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentMap.isAdded()) {
				transaction.add(R.id.frame_container, fragmentMap);
			} else {
				transaction.show(fragmentMap);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchStreaming(int position) {
		if (fragmentStreaming == null) {
			fragmentStreaming = new StreamingFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentStreaming) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentStreaming.isAdded()) {
				transaction.add(R.id.frame_container, fragmentStreaming);
			} else {
				transaction.show(fragmentStreaming);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchNotification(int position) {
		if (fragmentNotification == null) {
			fragmentNotification = new NotificationFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentNotification) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentNotification.isAdded()) {
				transaction.add(R.id.frame_container, fragmentNotification);
			} else {
				transaction.show(fragmentNotification);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchAchievements(int position) {
		if (fragmentAchievements == null) {
			fragmentAchievements = new AchievementsFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentAchievements) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentAchievements.isAdded()) {
				transaction.add(R.id.frame_container, fragmentAchievements);
			} else {
				transaction.show(fragmentAchievements);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchTournaments(int position) {
		if (fragmentTournaments == null) {
			fragmentTournaments = new TournamentsListFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentTournaments) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentTournaments.isAdded()) {
				transaction.add(R.id.frame_container, fragmentTournaments);
			} else {
				transaction.show(fragmentTournaments);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchHome(int position) {
		if (fragmentHome == null) {
			fragmentHome = new HomeFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentHome) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentHome.isAdded()) {
				transaction.add(R.id.frame_container, fragmentHome);
			} else {
				transaction.show(fragmentHome);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchCoupons(int position) {
		if (fragmentCoupon == null) {
			fragmentCoupon = new CouponFragment();
		}
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		setTitle(navMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != fragmentCoupon) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!fragmentCoupon.isAdded()) {
				transaction.add(R.id.frame_container, fragmentCoupon);
			} else {
				transaction.show(fragmentCoupon);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void launchTournamentBracket(String tournament) {
		tournamentFragment = new TournamentsFragment(tournament);
		tournamentFragment.setTournament(tournament);
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		android.support.v4.app.Fragment currentFrag = getSupportFragmentManager()
				.findFragmentById(R.id.frame_container);
		mDrawerLayout.closeDrawer(mDrawerList);
		if (currentFrag != tournamentFragment) {

			if (currentFrag != null) {
				transaction.remove(currentFrag);
			}

			if (!tournamentFragment.isAdded()) {
				transaction.add(R.id.frame_container, tournamentFragment);
			} else {
				transaction.show(tournamentFragment);
			}

			transaction.addToBackStack(null);
			transaction.commit();

		}
	}

	public void onDialogPositiveClick(DialogFragment dialog) {
		EditText logName = (EditText) dialog.getDialog().findViewById(
				R.id.logName);
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		dialog.getDialog().cancel();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mManager.isBluetoothAvailable()) {
			// Enable scanner in foreground mode and register receiver
			mManager.setForegroundMode(true);
		} else {
			Toast.makeText(this, "Please turn on bluetooth", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		// Set scanner in background mode
		mManager.setForegroundMode(false);
	}

	public MyLogger getLogger() {
		return mLogger;
	}

	/** check for internet connection */
	public boolean checkInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}
}
