package com.continental.travelbuddy.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.continental.travelbuddy.R;
import com.continental.travelbuddy.ui.slideshow.EstadoFragment;
import com.continental.travelbuddy.ui.slideshow.MenuFragment;
import com.continental.travelbuddy.ui.slideshow.RecoFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    public ViewPager viewPager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        View contenedor=(View)container.getParent();
        appBar=(AppBarLayout)contenedor.findViewById(R.id.appbar);
        tabs=new TabLayout(getActivity());

        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);

        viewPager=(ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        SharedPreferences prefs = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String id_fragment_suggestions = prefs.getString("id_fragment_suggestions", "0");
        int id_ver_fragment = Integer.valueOf(id_fragment_suggestions);
        viewPager.setCurrentItem(id_ver_fragment);
        guardarPreferencias("0");

        tabs.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tabs);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter (FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    MenuFragment menuFragment=new MenuFragment();
                    return menuFragment;
                case 1:
                    EstadoFragment estadoFragment =new EstadoFragment();
                    return estadoFragment;

                case 2:
                    RecoFragment recoFragment =new RecoFragment();
                    return recoFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    String tab_text_1=("Servicios");
                    return tab_text_1;
                case 1:
                    String tab_text_2=("Estado");
                    return tab_text_2;
                case 2:
                    String tab_text_3=("Sugerencias");
                    return tab_text_3;

            }

            return super.getPageTitle(position);
        }
    }

    private void guardarPreferencias(String id_fragment_suggestions){
        SharedPreferences preferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id_fragment_suggestions",id_fragment_suggestions);
        editor.commit();
    }
}