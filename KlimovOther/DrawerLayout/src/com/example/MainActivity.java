package com.example;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private String[] mCatTitles;
	private ListView mDrawerListView;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;

	private String TAG = "myLogs";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setTitle("DrawerLayout");

		Log.d(TAG, "onCreate");

		// получаем массив строк
		mCatTitles = getResources().getStringArray(R.array.cats_array_ru);
		// находим ListView выдвигающейся менюки
		mDrawerListView = (ListView) findViewById(R.id.left_drawer);
		// находим весь DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// подключим адаптер для списка
		mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mCatTitles));

		// установим слушатель для щелчков по элементам списка
		mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

		mTitle = mDrawerTitle = getTitle();
		
		// Включаем значок у ActionBar для управления выдвижной панелью щелчком
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
		// устанавливаем слушателя для выдвигания
		// mDrawerLayout.setDrawerListener(new DrawerListener());
		// Set the drawer toggle as the DrawerListener
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);		
		

		// Обработчик слушателя
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.ic_drawer, /*
										 * nav drawer icon to replace 'Up' caret
										 */
				R.string.drawer_open, /* "open drawer" description */
				R.string.drawer_close /* "close drawer" description */
		) {

			/**
			 * Called when a drawer has settled in a completely closed state.
			 */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				Log.d(TAG, "onDrawerClosed()");
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				Log.d(TAG, "onDrawerOpened()");
			}
		};

	
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	// Слушатель для элементов списка в выдвижной панели
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Log.d(TAG, "onItemClick() " + "Выбран пункт " + position);
			// Toast.makeText(getApplicationContext(), "Выбран пункт " +
			// position, Toast.LENGTH_SHORT).show();
			selectItem(position);
		}
	}

	// слушатель выдвигания DrawerLayout.DrawerListener
	private class DrawerListener implements DrawerLayout.DrawerListener {

		@Override
		public void onDrawerClosed(View arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onDrawerClosed()");
		}

		@Override
		public void onDrawerOpened(View arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onDrawerOpened()");
		}

		@Override
		public void onDrawerSlide(View arg0, float arg1) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onDrawerSlide()");
		}

		@Override
		public void onDrawerStateChanged(int arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onDrawerStateChanged()");
		}

	}
	
   
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        Log.d(TAG, "onOptionsItemSelected()");
        
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
        	Log.d(TAG, "mDrawerToggle.onOptionsItemSelected");
            return true;
        }
        
        return true;

    }
	
	
	
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate()");
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged()");
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    
    
    
    
    
	/**
	 * Фрагмент, который появится в основной части разметки и покажет кота
	 */
	public static class CatFragment extends android.app.Fragment {
		public static final String ARG_CAT_NUMBER = "cat_number";

		public CatFragment() {
			// Для фрагмента требуется пустой конструктор
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_cat, container, false);
			int i = getArguments().getInt(ARG_CAT_NUMBER);

			// имена котов на англ. для нахождения имен файлов
			String catName = getResources().getStringArray(R.array.cats_array)[i];

			String catNameTitle = getResources().getStringArray(R.array.cats_array_ru)[i];

			int imageId = getResources().getIdentifier(catName.toLowerCase(Locale.ROOT), "drawable",
					getActivity().getPackageName());

			((ImageView) rootView.findViewById(R.id.imageViewCat)).setImageResource(imageId);

			// ???
			getActivity().setTitle(catNameTitle);
			return rootView;
		}

	}

	private void selectItem(int position) {
		// Обновляем содержимое экрана, заменяя фрагменты
		android.app.Fragment fragment = new CatFragment();
		Bundle args = new Bundle();
		args.putInt(CatFragment.ARG_CAT_NUMBER, position);
		fragment.setArguments(args);

		// менеджер фрагментов
		android.app.FragmentManager fragmentManager = getFragmentManager();
		// заменяем фрагмент
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		// обновим выбранный элемент списка и закрываем панель:
		// делаем пункт меню выбранным
		mDrawerListView.setItemChecked(position, true);

		// меняем заголовок
		setTitle(mCatTitles[position]);

		// закрываем
		mDrawerLayout.closeDrawer(mDrawerListView);
	}
}