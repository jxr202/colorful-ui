# colorful-ui

# 日期选择库



# 1.init：

①.在Project中的build.gradle添加：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }	//添加这一句
		}
	}
	
②.在app中的build.gradle添加：

	dependencies {
	        compile 'com.github.jxr202:colorful-ui:v1.0.1'	//添加这一句.
	}
	
	
# 2.用法，在layout文件中加入 :

    <com.jxr202.colorful_ui.DateSelectView
        android:id="@+id/dateSelectView"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#f9f9f9"
        android:minHeight="43dp"
        jxr202:leftViewSrc="@drawable/date_left"
        jxr202:rightViewSrc="@drawable/date_right"
        jxr202:titleText="@string/date_title"
        jxr202:titleTextSize="16dp"
        jxr202:titleTextId="@+id/date_title"
        jxr202:todayTextBackground="@drawable/bg_date_today"
        jxr202:todayText="@string/date_today"
        jxr202:todayTextSize="16dp"
        jxr202:todayTextColor="#ffffff"/>
		
	
# 3.事件监听，在点击某个控件时的回调：

    private DateSelectView mDateSelectView;

    private DateSelectView.SimpleDateClickListener mListener = new DateSelectView.SimpleDateClickListener() {

        @Override
        public void onDateLeftClick() {
            Log.i(TAG, "onDateLeftClick...");
        }

        @Override
        public void onDateRightClick() {
            Log.i(TAG, "onDateRightClick...");
        }

        @Override
        public void onDateTitleClick() {
            Log.i(TAG, "onDateTitleClick...");
        }

        @Override
        public void onDateTodayClick() {
            Log.i(TAG, "onDateTodayClick...");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDateSelectView = (DateSelectView) findViewById(R.id.dateSelectView);

        mDateSelectView.setOnDateClickListener(mListener);

    }
	
	
	
	