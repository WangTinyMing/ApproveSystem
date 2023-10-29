package org.myAPP.javaClass.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.myAPP.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherNavigatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherNavigatorFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Button btn0 = getActivity().findViewById(R.id.approve);
        btn0.setOnClickListener(view1 -> {

            fragmentTransaction.replace(R.id.contentLayout,new ApprovingFragment());
            fragmentTransaction.commit();

        });
        getActivity().findViewById(R.id.approved).setOnClickListener(view1 -> {
            //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentLayout,new ApprovedFragment());
            fragmentTransaction.commit();
        });
        getActivity().findViewById(R.id.logout).setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(getActivity() ,LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeacherNavigatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherNavigatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherNavigatorFragment newInstance(String param1, String param2) {
        TeacherNavigatorFragment fragment = new TeacherNavigatorFragment();
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
        return inflater.inflate(R.layout.fragment_teacher_navigator, container, false);
    }
}