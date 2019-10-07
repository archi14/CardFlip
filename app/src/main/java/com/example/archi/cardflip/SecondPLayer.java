package com.example.archi.cardflip;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Random;


public class SecondPLayer extends Fragment {
    PopupWindow popupWindow;
    private  int [] mCardFront = new int[]{R.mipmap.ac, R.mipmap.iic, R.mipmap.iiic, R.mipmap.ivc, R.mipmap.vc, R.mipmap.vic, R.mipmap.viic, R.mipmap.viiic, R.mipmap.ixc, R.mipmap.xc, R.mipmap.jc, R.mipmap.qc, R.mipmap.kc, R.mipmap.as, R.mipmap.iis, R.mipmap.iiis, R.mipmap.ivs, R.mipmap.vs, R.mipmap.vis, R.mipmap.viis, R.mipmap.viiis, R.mipmap.ixs, R.mipmap.xs, R.mipmap.js, R.mipmap.qs, R.mipmap.ks, R.mipmap.ah, R.mipmap.iih, R.mipmap.iiih, R.mipmap.ivh, R.mipmap.vh, R.mipmap.vih, R.mipmap.viih, R.mipmap.viiih, R.mipmap.ixh, R.mipmap.xh, R.mipmap.jh, R.mipmap.qh, R.mipmap.kh, R.mipmap.ad, R.mipmap.iid, R.mipmap.iiid, R.mipmap.ivd, R.mipmap.vd, R.mipmap.vid, R.mipmap.viid, R.mipmap.viiid, R.mipmap.ixd, R.mipmap.xd, R.mipmap.jd, R.mipmap.qd, R.mipmap.kd};
    private View mCardFrontLayout;
    private View mCardBackLayout;
    ImageView cardFront,chip;
    Button raise,deal,fold;
    boolean popupopen=false;
    Random rand = new Random();
    private AnimatorSet mSetRightOut,mSetLeftIn;
    private boolean mIsBackVisible = true;
    public SecondPLayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        loadAnimations();
        changeCameraDistance();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (!mIsBackVisible && !popupopen) {
                        mSetRightOut.setTarget(mCardFrontLayout);
                        mSetLeftIn.setTarget(mCardBackLayout);
                        mSetRightOut.start();
                        mSetLeftIn.start();
                        mIsBackVisible = true;
                        chip.setVisibility(View.INVISIBLE);

                    } else if(!popupopen){
                        int random = rand.nextInt(52);
                        cardFront.setImageResource(mCardFront[random]);
                        mSetRightOut.setTarget(mCardBackLayout);
                        mSetLeftIn.setTarget(mCardFrontLayout);
                        mSetRightOut.start();
                        mSetLeftIn.start();
                        mIsBackVisible = false;
                        chip.setVisibility(View.VISIBLE);
                        chip.setAlpha(0.0f);
                        chip.animate().translationY(-chip.getHeight()).alpha(1.0f).setListener(null);
                        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View customView = layoutInflater.inflate(R.layout.popup,null);
                        raise = customView.findViewById(R.id.raise);
                        deal = customView.findViewById(R.id.deal);
                        fold = customView.findViewById(R.id.fold);
                        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow.showAtLocation(view, Gravity.CENTER,0,500);
                        popupopen = true;
                        raise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                popupopen = false;
                            }
                        });

                        deal.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                popupopen = false;
                            }
                        });

                        fold.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                popupopen = false;
                            }
                        });
                    }

            }
        });
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density*distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),R.animator.in_animation);
    }

    private void findViews(View view) {
        mCardBackLayout = view.findViewById(R.id.card_back);
        mCardFrontLayout = view.findViewById(R.id.card_front);
        cardFront = view.findViewById(R.id.cardfront);
        chip = view.findViewById(R.id.chip);
    }




}
