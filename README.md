# RealmDemo
**Android Realm Demo : Library management system/Realm案例：图书馆管理系统**<br/>
Realm数据库是基于C++编写的一个不同于SQLite数据库的数据库引擎，是一个可以替代SQLite以及ORMlibraries的移动端数据库。
图书馆管理系统，其实说白了就是数据库的增删改查，本案例中的增删改查只针对本地Realm的数据库。
Realm提供给我们的增删改查操作足够快，在UI线程中执行操作就行。
但是如果遇到较复杂的增删改查，或操作的数据较多时，就可以子线程进行操作，使用异步进行增删改查。
案例分为两个模块，一个为学生管理，和图书管理，而图书管理则使用异步进行增删改查。<br/>
**效果图：**<br>
<img src="http://upload-images.jianshu.io/upload_images/3550596-ed06764d5aa8f210.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="image"><br/>
<img src="http://upload-images.jianshu.io/upload_images/3550596-e16e62ae5bba7f32.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="image"><br/>
#<a href="http://blog.csdn.net/donkor_">我的博客<a/><br>
欢迎关注我的微信公众号(donkor)，扫一扫下方二维码，即可关注。<br>有什么问题也可以直接留言，看到之后我会及时回复您。<br>
<img src="http://upload-images.jianshu.io/upload_images/3550596-2b35610e944cfbb6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="image">
