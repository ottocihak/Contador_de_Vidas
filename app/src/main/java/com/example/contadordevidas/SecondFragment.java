package com.example.contadordevidas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contadordevidas.databinding.FragmentSecondBinding;

import java.text.BreakIterator;

public class SecondFragment extends Fragment {

    Button button;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        button = view.findViewById(R.id.button_second);
        TextView textViewP1 = (TextView) view.findViewById(R.id.textViewP1);
        TextView textViewP2 = (TextView) view.findViewById(R.id.textViewP2);
        if (life1||poison1==10){
            textViewP1.setText("You Lose");
            textViewP2.setText("You Win");
        }
        if (life2==0||poison2==10){
            textViewP1.setText("You Win");
            textViewP2.setText("You Lose");
        }
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}