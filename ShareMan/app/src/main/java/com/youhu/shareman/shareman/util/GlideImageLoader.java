package com.youhu.shareman.shareman.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by yangmu on 2017/7/3.
 */

//        Banner banner = (Banner) findViewById(R.id.banner);
//                //设置banner样式
//                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//                //设置图片加载器
//                banner.setImageLoader(new GlideImageLoader());
//                //设置图片集合
//                List<String> imgs = new ArrayList<>();
//                for (Integer i:images) {
////            imgs.add(i);
//                imgs.add("http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic41.nipic.com%2F20140529%2F18243620_101015342117_2.gif&thumburl=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3478162561%2C2168919095%26fm%3D28%26gp%3D0.jpg");
//                }
//                banner.setImages(imgs);
//                //设置banner动画效果
//                banner.setBannerAnimation(Transformer.DepthPage);
//                //设置标题集合（当banner样式有显示title时）
////        List<String> titles = new ArrayList<>();
////        titles.add("11111");
////        titles.add("22222");
////        titles.add("33333");
////        titles.add("44444");
////        banner.setBannerTitles(titles);
//                //设置自动轮播，默认为true
//                banner.isAutoPlay(true);
//                //设置轮播时间
//                banner.setDelayTime(1500);
//                //设置指示器位置（当banner模式中有指示器时）
//                banner.setIndicatorGravity(BannerConfig.CENTER);
//
//                banner.setOnBannerListener(new OnBannerListener() {
//@Override
//public void OnBannerClick(int position) {
//        ToastUtil.showShort(WelcomeActivity.this,"我被点击了"+position);
//        }
//        });
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
//        Glide.with(context).load(path).transform(new GlideRoundTransform(context, 27)).into(imageView);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
        return super.createImageView(context);
    }
}