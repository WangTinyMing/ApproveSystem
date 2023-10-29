package org.myAPP.javaClass.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import org.myAPP.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ApprovingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApprovingFragment extends Fragment {
    private WebView web;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn0 = getActivity().findViewById(R.id.back0);
        btn0.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentLayout, new TeacherNavigatorFragment());
            fragmentTransaction.commit();
        });
        web = getActivity().findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);

        String uid = getActivity().getIntent().getStringExtra("uid");
        web.setWebViewClient(new RequestFragment.MyWebViewClient());
        web.loadUrl("http://10.0.2.2:8080/teacApping");
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ApprovingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApprovingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApprovingFragment newInstance(String param1, String param2) {
        ApprovingFragment fragment = new ApprovingFragment();
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
        return inflater.inflate(R.layout.fragment_approving, container, false);
    }
}