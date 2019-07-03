package com.example.halaljava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import android.widget.*;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link newItemFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link newItemFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newItemFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int counter = 0;

    private OnFragmentInteractionListener mListener;

    public newItemFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newItemFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static newItemFrag newInstance(String param1, String param2) {
        newItemFrag fragment = new newItemFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_item, container, false);
    }

    public void setText(String s){
        TextView textView = (TextView) getActivity().findViewById(R.id.centerText);
        textView.setText(s);
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Finance finance = new Finance(getContext());
        EditText itemNameText = getView().findViewById(R.id.itemTextField);
        EditText itemAmount = getView().findViewById(R.id.item_amount);
        final Spinner categoryDropDown = getView().findViewById(R.id.categoryAll);
        ArrayAdapter<CharSequence> categoryAllAdapter = ArrayAdapter.createFromResource(getContext(),R.array.category_expense_array,android.R.layout.simple_spinner_dropdown_item);
        categoryDropDown.setAdapter(categoryAllAdapter);

        view.findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code action here.
                String s = "The count is: " + ++counter;
                setText(s);
            }
        });

        view.findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                counter = 0;
//                Snackbar.make(v, "Counter is reset!", Snackbar.LENGTH_LONG).show();
//                String s = "The count is: " + counter;
//                setText(s);
                finance.deleteDBTest();

            }
        });

        view.findViewById(R.id.database_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finance.addExpense(finance.getExpense(new Expense()));
                Snackbar.make(v, "New row inserted!", Snackbar.LENGTH_LONG).show();
            }
        });

        itemNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    finance.setItemTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        itemAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    finance.setItemAmount(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        view.findViewById(R.id.positive_amount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte b = 1;
                finance.setItemType(b);
            }
        });

        view.findViewById(R.id.negative_amount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte b = 0;
                finance.setItemType(b);
            }
        });

        categoryDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                finance.setCategory(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
