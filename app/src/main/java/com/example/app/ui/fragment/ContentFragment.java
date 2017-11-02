package com.example.app.ui.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.bean.CategoryEntity;
import com.example.app.common.CommonViewHolder;
import com.example.app.common.Constant;
import com.example.app.common.MyRecyclerViewAdapter;
import com.example.app.mvp.MvpContract;
import com.example.app.mvp.Presenter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements MvpContract.IView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private Presenter presenter = new Presenter(this);
    private MyRecyclerViewAdapter<CategoryEntity.ResultsBean> recyclerViewAdapter;
    List<CategoryEntity.ResultsBean> dataSource = new ArrayList<>();
    private static final String TAG = "ContentFragment";
    private String title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        title = getArguments().getString(Constant.CONSTANT_TITLE);
        ((MainActivity) getActivity()).getToolbar().setTitle(title);
        requestDynamicPermissions();
        initData();
        initAdapter();
        return view;
    }

    public void initData() {
        Log.i(TAG, "initData: ");
        presenter.fetchData(title, 10, 1);

    }

    public void initAdapter() {
        recyclerViewAdapter = new MyRecyclerViewAdapter<CategoryEntity.ResultsBean>(getActivity(), dataSource) {
            @Override
            public int getItemViewLayoutID() {
                return R.layout.item_content;
            }


            @Override
            public void convert(CommonViewHolder viewHolder, CategoryEntity.ResultsBean item, int position) {
                Log.i(TAG, "convert: " + item.toString());
                if (item.getImages() != null) {
                    Log.i(TAG, "convert: image url is == " + item.getImages());
                    ImageView imageView = viewHolder.getView(R.id.imgv1_item_content_content_fragment);
                    imageView.setVisibility(View.VISIBLE);
                    Glide.with(getActivity()).load(item.getImages().get(0))
                            .into(imageView);


                }
                viewHolder.setText(R.id.tv_item_content_content_fragment, item.getDesc());
                if (item.getPublishedAt() != null) {
                    viewHolder.getView(R.id.tv_when_content_content_fragment).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.tv_when_content_content_fragment, item.getPublishedAt());
                }
                viewHolder.setText(R.id.tv_who_content_content_fragment, item.getWho());
            }

        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadDataSuccess(List<CategoryEntity.ResultsBean> dataList) {
        Log.i(TAG, "onLoadDataSuccess: " + dataList.size());
        dataSource.clear();
        dataSource.addAll(dataList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadDataFailed() {

    }

    private void requestDynamicPermissions() {
        // 检查权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            RxPermissions rxPermissions = new RxPermissions(getActivity());
            rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {
                                // `permission.name` is granted !
//                                T.info("可以保存妹子到本地啦");
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // Denied permission without ask never again
                                // 禁止 以后不在询问
//                                T.info("无法保存妹子啦");
                            } else {
                                // Denied permission with ask never again
                                // Need to go to the settings
                                // 当前禁止
//                                T.info("无法保存妹子啦");
                            }
                        }
                    });
        }
    }
}
