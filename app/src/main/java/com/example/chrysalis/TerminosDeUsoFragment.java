package com.example.chrysalis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;



public class TerminosDeUsoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View.OnClickListener Lisener;

    public TerminosDeUsoFragment(View.OnClickListener lisener) {
        // Required empty public constructor
        Lisener = lisener;
    }

    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.layout_terminosdeuso_fragment, container, false);
        TextView texto = v.findViewById(R.id.textViewTerminosDeUso);
        final Button boton = v.findViewById(R.id.buttonTerminosDeUso);

        CheckBox checkBox = v.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    boton.setEnabled(true);
                }
                else
                {
                    boton.setEnabled(false);
                }
            }
        });
        texto.setText(GetInfo.GetTerminosDeUso());

        boton.setOnClickListener(Lisener);


        return v;
    }


}
