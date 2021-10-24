package com.example.contadordevidas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contadordevidas.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ImageButton arrow2;
    private ImageButton arrow1;
    private ImageButton getLife1;
    private ImageButton getLife2;
    private ImageButton takeLife1;
    private ImageButton takeLife2;
    private Button getPoison1;
    private Button takePoison1;
    private Button getPoison2;
    private Button takePoison2;
    private TextView displey1;
    private TextView displey2;
    private View view;

    int life1;
    int life2;
    int poison1;
    int poison2;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        
        if (savedInstanceState!=null){
            life1= (int) savedInstanceState.get("life1");
            life2= (int) savedInstanceState.get("life2");
            poison1= (int) savedInstanceState.get("poison1");
            poison2= (int) savedInstanceState.get("poison2");
        } else {
            life1=life2=20;
            poison1=poison2=0;
        }

        view = inflater.inflate(R.layout.fragment_first, container, false);


        arrow2 = (ImageButton) view.findViewById(R.id.arrow2);
        arrow1 = (ImageButton) view.findViewById(R.id.arrow1);
        getLife1 = (ImageButton) view.findViewById(R.id.getLife1);
        getLife2 = (ImageButton) view.findViewById(R.id.getLife2);
        takeLife1 = (ImageButton) view.findViewById(R.id.takeLife1);
        takeLife2 = (ImageButton) view.findViewById(R.id.takeLife2);
        getPoison1 = (Button) view.findViewById(R.id.getPoison1);
        takePoison1 = (Button) view.findViewById(R.id.takePoison1);
        getPoison2 = (Button) view.findViewById(R.id.getPoison2);
        takePoison2 = (Button) view.findViewById(R.id.takePoison2);
        displey1 = (TextView) view.findViewById(R.id.displey1);
        displey2 = (TextView) view.findViewById(R.id.displey2);

        
        updateView();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.arrow1:
                        life1++;
                        life2--;
                        break;
                    case R.id.arrow2:
                        life2++;
                        life1--;
                        break;
                    case R.id.getPoison1:
                        poison1++;
                        break;
                    case R.id.getPoison2:
                        poison2++;
                        break;
                    case R.id.getLife1:
                        life1++;
                        break;
                    case R.id.getLife2:
                        life2++;
                        break;
                    case R.id.takeLife1:
                        life1--;
                        break;
                    case R.id.takeLife2:
                        life2--;
                        break;
                    case R.id.takePoison1:
                        poison1--;
                        break;
                    case R.id.takePoison2:
                        poison2--;
                        break;
                }
                updateView();
            }
        };

        arrow1.setOnClickListener(listener);
        arrow2.setOnClickListener(listener);
        getLife1.setOnClickListener(listener);
        getLife2.setOnClickListener(listener);
        takeLife1.setOnClickListener(listener);
        takeLife2.setOnClickListener(listener);
        getPoison1.setOnClickListener(listener);
        getPoison2.setOnClickListener(listener);
        takePoison1.setOnClickListener(listener);
        takePoison2.setOnClickListener(listener);


        return view;

    }

    private void updateView() {
        if (poison1<0) poison1=0;
        if (poison2<0) poison2=0;
        displey1.setText(String.format("%d/%d",life1,poison1));
        displey2.setText(String.format("%d/%d",life2,poison2));
        if (life1==0||poison1==10){
            endGameTransition();
        }
        if (life2==0||poison2==10){
            endGameTransition();
        }
    }

    private void reset() {
        life1=life2=20;
        poison1=poison2=0;
        updateView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_reset){
            reset();
            Snackbar.make(view, "New game", Snackbar.LENGTH_INDEFINITE).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//    }
    
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("life1",life1);
        outState.putInt("life2",life2);
        outState.putInt("poison1",poison1);
        outState.putInt("poison2",poison2);
        super.onSaveInstanceState(outState);
    }

    private void endGameTransition() {

        NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        
    }

}
