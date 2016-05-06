# Fresco-ImageView

FrescoImageView是一种Android平台的图像控件，可以异步加载网络图片、项目资源和本地图片，并且支持双指缩放、图片的基本处理以及Fresco的所有特性。

该控件基于Facebook的图像加载库Fresco封装，Fresco的所有方法和属性都可以使用。

Fresco-ImageView本身继承自DraweeView，所以可以直接把它当作DraweeView使用，除使用控件封装的加载方法外还可以通过Fresco原始的ImageRequest来加载图片。

## 特性
 * 直接继承Fresco的DraweeView，本质是View，同时兼容Fresco的所有参数和方法
 * 加载图片只需一步，无需繁杂的设置
 * 支持双指缩放，并且支持点击事件，替代PhotoView的控件

Project site： <https://github.com/HomHomLin/FrescoImageView>.

最新版本:v1.3.0

##导入项目

**Gradle dependency:**
``` groovy
compile 'homhomlin.lib:frescoimageview:1.3.0'
```

or

**Maven dependency:**
``` xml
<dependency>
  <groupId>homhomlin.lib</groupId>
  <artifactId>frescoimageview</artifactId>
  <version>1.3.0</version>
</dependency>
```


##用法

###导入Fresco

在项目导入FrescoImageView后，还需要导入Fresco，如下：

``` groovy
compile 'com.facebook.fresco:fresco:0.10.0'
```

截止至当前Readme编写时间，Fresco的最新版本为0.10.0。

FrescoImageView本身并不包含Fresco，如果你还需要OKHTTP请查阅Fresco用法或者看这个[DEMO](https://github.com/HomHomLin/FrescoImageView/blob/master/app/src/main/java/com/lhh/frescoimageview/demo/App.java)。

Fresco-0.10.0与之前版本有所区别，它将GIF和WEBP的库分离出来，所以如果你的项目使用了Fresco-0.10.0并且需要FrescoImageView实现gif和webp的功能，那么请添加以下依赖：

``` groovy
compile 'com.facebook.fresco:animated-webp:0.10.0'
compile 'com.facebook.fresco:animated-gif:0.10.0'
```

如果需要Android2.3版本实现Gif，需要添加额外依赖：

``` groovy
compile 'com.facebook.fresco:animated-base-support:0.10.0'
```

###配置Fresco

在项目的AndroidManifest.xml中添加网络访问权限（视需求而定），example中使用了OkHttp，如果你有需要请参照example。

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

在项目的Application类中配置Fresco，这里其实是Fresco的内容。

``` java
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
```

###添加控件到XML

在需要添加的界面xml中添加组件。

FrescoImageView提供两种组件，分别是FrescoImageView（普通控件）和FrescoZoomImageView（可缩放控件），根据需要添加控件，以下以FrescoZoomImageView为例：

``` xml
<lib.lhh.fiv.library.FrescoZoomImageView
    android:id="@+id/fiv"
    fresco:actualImageScaleType="fitCenter"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

在代码中find该组件。

```java
FrescoZoomImageView frescoImageView = (FrescoZoomImageView)findViewById(R.id.fiv);
```

###加载网络图片

调用void loadView(String url, int defaultResID)方法。

url表示需要加载的网络图片地址，defaultResID表示占位图（默认图）。

```java
frescoImageView.loadView(mImgUrl,R.mipmap.ic_launcher);
```

如果需要先显示默认图，然后加载显示一张低分辨率的图，最后再显示原图，可以使用void loadView(String lowUrl ,String url, int defaultResID)方法，lowUrl表示低分辨率图片地址。

###加载本地图片

调用void loadLocalImage(String path, int defaultRes)方法。

path表示本地图片的绝对路径。

```java
frescoImageView.loadLocalImage(path,R.mipmap.ic_launcher);
```

###加载项目资源

选择以上任意一个方法，非defaultResId参数填写为null即可。

```java
frescoImageView.loadLocalImage(null,R.mipmap.ic_launcher);
```

###点击监听

如果你使用的是FrescoZoomImageView，则需要调用setOnDraweeClickListener(OnClickListener l)方法来设置点击事件的监听。

如果你使用的是FrescoImageView，则可以直接使用setOnClicklistener(OnClickListener l)。

###开启和关闭gif动画

如果你的图片是gif类型的，则我们可以控制FrescoImageView的Gif动画，通过FrescoImageView的setAnim(boolean anim)来控制，默认情况下，我们是开启动画的。

###设置圆形

如果你需要将显示的图片变为圆形，则可以通过asCircle()方法。

```java
frescoImageView.asCircle();
```

###Gif图片设置为圆形

由于Fresco的原因，如果你需要将gif设置为圆形，则需要使用setCircle(int overlay_color)方法，overlay_color为背景图颜色。

```java
frescoImageView.setCircle(Color.WHITE);
```

###设置圆角

通过setCornerRadius(float radius)方法，并传入需要的角度即可实现圆角和边角的切割。

```java
frescoImageView.setCornerRadius(10);
```

###设置图像处理器

你可能需要对图片做额外的处理，那么你可以编写一个Fresco的PostProcessor，并通过setPostProcessor(PostProcessor)方法来设置一个处理器。

```java
frescoImageView.setPostProcessor(postProcessor);
```

###设置是否点击重试加载

有时候图片会加载失败，这时候你可以设置是否允许让用户点击该图片重试加载，通过setTapToRetryEnabled(boolean tapToRetryEnabled)方法来设置。

```java
frescoImageView.setTapToRetryEnabled(true);
```

###设置图片边框

通过setBorder(int color, float width)方法来设置。

```java
frescoImageView.setBorder(Color.BLACK, 3.0f);
```

###清除图片圆角属性

也许你曾经设置过图片的圆角属性了，通过clearRoundingParams()方法可以清除他们来恢复到初始状态。

```java
frescoImageView.clearRoundingParams();
```

###设置加载监听

有时候我们想监听图片加载的情况，通过setControllerListener(ControllerListener controllerListener)方法可以添加监听。

通过ControllerListener controllerListener = new BaseControllerListener()可以创建监听器。

```java
frescoImageView.setControllerListener(controllerListener);
```

###其他

FrescoImageView基于Fresco封装，因此Fresco的用法同样适用于FrescoImageView。

同样，FrescoZoomImageView也支持以上说的api和Fresco的方法。

具体请看Fresco：[Fresco的Github](https://github.com/facebook/fresco)

## Developed By

 * Linhonghong - <linhh90@163.com>

##License
Copyright 2016 LinHongHong

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.