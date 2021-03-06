package com.example.halaljava;

import android.R.layout;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.view.DragStartHelper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import com.example.halaljava.database.FinanceAdapter;
import com.example.halaljava.database.FinanceBaseHelper;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link editItemFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link editItemFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editItemFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    RecyclerView recycler;
    FinanceAdapter financeAdapter;
    public editItemFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editItemFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static editItemFrag newInstance(String param1, String param2) {
        editItemFrag fragment = new editItemFrag();
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
        return inflater.inflate(R.layout.fragment_edit_item, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Finance finance = new Finance(getContext());
        String itemTitle;
        String category;
        double itemAmount;
        byte itemType;
        Date itemDate;

        ArrayList<Expense> arrayList = finance.getMonthlyExpenses();
        recycler = getView().findViewById( R.id.monthlyExpensesView);
        financeAdapter = new FinanceAdapter(arrayList);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        recycler.setLayoutManager(mLayoutManager);
        //recycler.setItemAnimator( new DefaultItemAnimator());
        recycler.setAdapter(financeAdapter);
        //recycler.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        financeAdapter.notifyDataSetChanged();
        SwipeController swipeController = new SwipeController();
//        ItemTouchHelperAdapter mAdapter = new ItemTouchHelperAdapter() {
//            @Override
//            public void onItemMove(int fromPosition, int toPosition) {
//
//            }
//
//            @Override
//            public void onItemDismiss(int position) {
//
//            }
//        };
//        ItemTouchHelper.Callback callback =
//                new SwipeController(financeAdapter);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recycler);

    }

        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
