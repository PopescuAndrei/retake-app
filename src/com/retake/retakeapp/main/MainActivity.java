package com.retake.retakeapp.main;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.map.FragmentMap;
import com.retake.retakeapp.schedule.ScheduleFragment;
import com.retake.retakeapp.streaming.StreamingFragment;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity {
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
		case R.id.action_settings:
			return true;
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
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		BaseFragment fragment = null;
		boolean fragmentSchedule = false;
		switch (position) {
		case 0:
			break;
		case 1:
			fragment = new FragmentMap();
			launchMap(1);
			break;
		case 2:
			fragment = new AchievementsFragment();
			break;
		case 3:
			fragment = new NotificationFragment();
			break;
		case 4:
			fragment = new ScheduleFragment();
			launchSchedule(4);
			break;
		case 5:
			fragment = new TournamentsFragment();
			break;
		case 6:
			fragment = new StreamingFragment();
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
}
