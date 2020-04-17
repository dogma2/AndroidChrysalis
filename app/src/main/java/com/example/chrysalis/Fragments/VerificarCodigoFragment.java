package com.example.chrysalis.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chrysalis.R;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerificarCodigoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class VerificarCodigoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerificarCodigoFragment.
     */
    // TODO: Rename and change types and number of parameters




    public VerificarCodigoFragment(View.OnClickListener lisener) {
        Lisener = lisener;
        // Required empty public constructor
    }
    private View.OnClickListener Lisener;
    private TextInputEditText textInputLayout;
    private LinearLayout CodigoIncorrecto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_verificarcodigo_fragment, container, false);
        CodigoIncorrecto = v.findViewById(R.id.LinearLayoutCodigoIncorrecto);
        CodigoIncorrecto.setVisibility(View.INVISIBLE);

        v.setOnClickListener(Lisener);
        LinearLayout exitLayout = v.findViewById(R.id.LinearLayoutNonExitVerificarCodigo);
        exitLayout.setOnClickListener(null);

        Button boton = v.findViewById(R.id.buttonVerificarCodigo);
        textInputLayout =  v.findViewById(R.id.editTextCodigo);




        //En caso que el codigo sea correcto
        boton.setOnClickListener(Lisener);
        return v;
    }

    public String GetInputText(){
        String input = textInputLayout.getText().toString();

        return  input;
    }
    public void SetCodeIsWrongText()
    {
     CodigoIncorrecto.setVisibility(View.VISIBLE);   //que apareza que el codigo no es correcto.
    }
}
