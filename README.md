Component
=========
***Android 通用类***
- - -
- 使用说明：
**集成：**

第一步：

root gradle:

	allprojects {
    	     repositories {
        	maven { url 'https://jitpack.io' }
        	jcenter()
    	    }
	}

第二步：

	compile 'com.github.huangyanbin:component:vcompile 'com.github.huangyanbin:component:1.1'


**1.功能介绍**

    android MVP模式
    OKHttp 请求  支持多种缓存
    网络请求解析 支持拦截解析 AsyncCallBack JsonCallBack
    错误和加载布局填充（指定替代View）
    RecyclerView 错误和加载布局、没有更多、已到底部显示填充
    RecyclerView 分割线
    RecyclerView 滚回顶部
    键盘弹起和隐藏监听
    状态栏显示和隐藏
    通用Dailog和提示Dialog封装
    Fragmentation
    butterknife
    rxjava
    rxAndroid
    rxlifecycle
    Activity和Fragment 侧滑返回






