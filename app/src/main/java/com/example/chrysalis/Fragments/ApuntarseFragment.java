package com.example.chrysalis.Fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chrysalis.R;

//import com.google.android.material.slider.Slider;


/**

 */
public class ApuntarseFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Button boton;
    View.OnClickListener Endlisener;




    private OnFragmentInteractionListener mListener;

    public ApuntarseFragment(View.OnClickListener listener) {

        Endlisener = listener;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //Definiendo atributos
        View vista = inflater.inflate(R.layout.layout_apuntarse_fragment, container, false);

        //vista.setVisibility(View.GONE);

        LinearLayout Exterior  = vista.findViewById(R.id.LLFragmentApunt);
        LinearLayout Interior = vista.findViewById(R.id.LL2FragmentApuntarse);

        boton = vista.findViewById(R.id.BotonApuntarse);
        final Switch botone = vista.findViewById(R.id.switcgFragment);

        //final Slider barra = vista.findViewById(R.id.NumeroDeInvitadosBarra);
        final SeekBar barra = vista.findViewById(R.id.NumeroDeInvitadosBarra);
        final TextView NumeroDeInvitados = vista.findViewById(R.id.NumeroDeInv);

        //Inicializando atributos

        barra.setEnabled(false);
        //final ColorStateList startcolor = barra.getThumbColor();

        NumeroDeInvitados.setText("0");

        // OnclickLiseners
        Interior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Exterior.setOnClickListener(Endlisener);


       barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               NumeroDeInvitados.setText(""+progress);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });




        botone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    barra.setEnabled(true);
                    //barra.setThumbColor(ColorStateList.valueOf(Color.GREEN));
                }
                else
                {
                    //barra.setValue(0f);
                    barra.setEnabled(false);
                    //barra.setThumbColor(startcolor);

                }
            }
        });

        boton.setOnClickListener(Endlisener);

        //Devolviendo la vista
        return vista;




    }
    public void Invisible()
    {
        this.getView().setVisibility(View.GONE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
