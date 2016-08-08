package richard.ztesoft.com.glidetest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by richard on 16/8/5.
 * 可以看到Glide加载的图片质量要差于Picasso（ps：我看不出来哈），为什么？
 * 这是因为Glide默认的Bitmap格式是RGB_565 ，比ARGB_8888格式的内存开销要小一半。
 * 下面是Picasso在ARGB8888下与Glide在RGB565下的内存开销图（应用自身占用了8m，因此以8为基准线比较）：
 *
 *
 * 如果你对默认的RGB_565效果还比较满意，可以不做任何事，但是如果你觉得难以接受，
 * 可以创建一个新的GlideModule将Bitmap格式转换到ARGB_8888：
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        //builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //磁盘缓存最大占用空间
        //builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 100*1024*1024));
        //设置缓存路径和缓存最大占用空间，自带存储
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "", 100*1024*1024));

//        builder.setDiskCache(new DiskCache.Factory() {
//            @Override public DiskCache build() {
//                File cacheLocation = getMyCacheLocationBlockingIO();
//                cacheLocation.mkdirs();
//                return DiskLruCacheWrapper.get(cacheLocation, 100*1024*1024);
//            }
//        });

//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
//
//        Log.i("xsailor","defaultMemoryCacheSize"+defaultMemoryCacheSize);
//        Log.i("xsailor","defaultBitmapPoolSize"+defaultBitmapPoolSize);

        /**
         * dynamically adjust Glide's memory footprint
         * during certain phases of your application.
         */
        //Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);

        /**
         * memory cache
         */
        //builder.setMemoryCache(new LruResourceCache(10*1024*1024));

        /**
         * bitmap pool
         */
        //builder.setBitmapPool(new LruBitmapPool(10*1024*1024));
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "", 100*1024*1024));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.

    }

    private File getMyCacheLocationBlockingIO(){
        return new File(Environment.getExternalStorageDirectory()+"/glide");
    }
}
