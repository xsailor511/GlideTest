package richard.ztesoft.com.glidetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.picasso_image)
    ImageView picasso_image;
    @InjectView(R.id.glide_image)
    ImageView glide_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        /**
         * Picasso不能加载gif图片
         */
        Picasso.with(this)
                .load("http://img4.duitang.com/uploads/item/201306/14/20130614163948_SvNm8.thumb.600_0.gif")
                .placeholder(R.drawable.defult_upload)
                .error(R.drawable.error)
                //.resize(768, 432)//pic加载的是整个图片http://scimg.jb51.net/allimg/160618/77-16061Q44U6444.jpg
                //.fit()//这样就会加载wrap_content 的图标
                //.centerCrop()
                //.transform(new CircleTransform())
                .into(picasso_image);

        /**
         * 实际上Glide是Picasso的翻版，只不过要更好用，占用内存更小，速度更快
         * 适合Fragment，Activity等控价，生命周期也是跟随具体控件
         * Glide可以加载动态Gif图片
         */
        Glide.with(this)
                .load("http://img4.duitang.com/uploads/item/201306/14/20130614163948_SvNm8.thumb.600_0.gif")
                //.placeholder(R.drawable.defult_upload)//默认图片会意向glide加载的图片分辨率，使scaleType无效
                //.error(R.drawable.error)
                //.transform(new CircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸的图片，下次再加载就会取出全尺寸的，然后调整大小
                .into(glide_image);//glide加载适合imageView的图片大小
    }
}
