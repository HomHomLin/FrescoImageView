package lib.lhh.fiv.library;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Created by Linhh on 16/2/18.
 */
public class FrescoImageView extends SimpleDraweeView {

//    private final static String TAG = "FrescoThumbnailView";
    public final static String HTTP_PERFIX = "http://";
    public final static String HTTPS_PERFIX = "https://";
    public final static String FILE_PERFIX = "file://";

    private String mThumbnailUrl = null;
    private int  mDefaultResID = 0;

    private ImageRequest mRequest;

    private boolean mAnim = true;//默认开启动画

    private Postprocessor mPostProcessor;

    private DraweeController mController;

    public FrescoImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public FrescoImageView(Context context) {
        super(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void setController(int resid){
        if(resid == 0){
            return;
        }

        if(mPostProcessor != null) {
            mRequest = ImageRequestBuilder.newBuilderWithResourceId(resid)
                    .setPostprocessor(mPostProcessor)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();

        }else{
            mRequest = ImageRequestBuilder.newBuilderWithResourceId(resid)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();
        }

        mController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(mRequest)
                .setAutoPlayAnimations(mAnim)
                .setOldController(this.getController())
                .build();

        this.setController(mController);
    }

    private void setController(Uri uri, Uri lowResUri){

        if(uri == null){
            return;
        }

        if(mPostProcessor != null) {
            mRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(mPostProcessor)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();

        }else{
            mRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();
        }

        ImageRequest lowResRequest = ImageRequest.fromUri(lowResUri);

        if(lowResRequest == null) {
            mController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(mRequest)
                    .setAutoPlayAnimations(mAnim)
                    .setOldController(this.getController())
                    .build();

        }else{
            mController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(mRequest)
                    .setLowResImageRequest(lowResRequest)
                    .setAutoPlayAnimations(mAnim)
                    .setOldController(this.getController())
                    .build();
        }
        this.setController(mController);
    }

    public void setPostProcessor(Postprocessor postProcessor){
        this.mPostProcessor = postProcessor;
    }

    public Postprocessor getPostProcessor(){
        return this.mPostProcessor;
    }

    public void loadView(String lowUrl ,String url, int defaultResID) {
        try {
            if (url == null || url.length() <= 0) {
                this.getHierarchy().setPlaceholderImage(defaultResID);
                this.setController(defaultResID);
                mThumbnailUrl = url;
                return;
            }
            mThumbnailUrl = url;
            mDefaultResID = defaultResID;

            if (mThumbnailUrl.startsWith(HTTP_PERFIX) || mThumbnailUrl.startsWith(HTTPS_PERFIX)) {

                Uri uri = Uri.parse(mThumbnailUrl);
                this.getHierarchy().setPlaceholderImage(defaultResID);

                Uri lowUri = null;

                if(!TextUtils.isEmpty(lowUrl)){
                    lowUri = Uri.parse(mThumbnailUrl);
                }

                setController(uri ,lowUri);

            } else {
                this.getHierarchy().setPlaceholderImage(defaultResID);
                this.setController(defaultResID);
            }

        }catch (OutOfMemoryError e){
            e.printStackTrace();
        }

    }

    public void loadView(String url, int defaultResID) {
        this.loadView(null, url, defaultResID);
    }

    /**
     * 获得当前使用的图片URL
     * @return
     */
    public String getThumbnailUrl(){
        return this.mThumbnailUrl;
    }

    /**
     * 获得当前使用的默认占位图
     * @return
     */
    public int getDefaultResID(){
        return this.mDefaultResID;
    }

    public void loadLocalImage(String path, int defaultRes){
        this.getHierarchy().setPlaceholderImage(defaultRes);
        if(null == path || path.length() == 0){
            this.setController(defaultRes);
            return;
        }
        if(!path.startsWith(FILE_PERFIX)){
            path = FILE_PERFIX + path;
        }
        Uri uri = Uri.parse(path);
        setController(uri, null);
    }

    public void setCornerRadius(float radius){
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setCornerRadius(float radius, int overlay_color){
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius).
                setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR).
                setOverlayColor(overlay_color);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setCircle(int overlay_color){
        RoundingParams roundingParams = RoundingParams.asCircle().
                setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR).
                setOverlayColor(overlay_color);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setAnim(boolean b){
        mAnim = b;
    }
}

