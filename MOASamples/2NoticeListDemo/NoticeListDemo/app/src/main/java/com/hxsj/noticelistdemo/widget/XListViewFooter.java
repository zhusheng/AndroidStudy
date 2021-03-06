/**
 * @file XFooterView.java
 * @create Mar 31, 2012 9:33:43 PM
 * @author Maxwin
 * @description XListView's footer
 */
package com.hxsj.noticelistdemo.widget;
import com.hxsj.noticelistdemo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class XListViewFooter extends LinearLayout {
	//正常状态
	public final static int STATE_NORMAL = 0;
	//准备状态
	public final static int STATE_READY = 1;
	//加载状态
	public final static int STATE_LOADING = 2;
	private Context mContext;
	//布局容器
	private View mContentView;
	private View mProgressBar;
	private TextView mHintView;
	public XListViewFooter(Context context) {
		super(context);
		initView(context);
	}
	public XListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	public TextView getHintView() {
		return mHintView;
	}
	/*
	* 设置当前的状态
	* */
	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
		} else if (state == STATE_LOADING) {
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
		}
	}
	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}
	public int getBottomMargin() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		return lp.bottomMargin;
	}
	/**
	 * 正常状态
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}
	/**
	 * 加载状态
	 */
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}
	/**
	 * 隐藏底部布局，在非下拉状态下
	 */
	public void hide() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}
	/**
	 * 显示底部容器
	 */
	public void show() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}
	private void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);
		addView(moreView);
		moreView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		//初始化控件
		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_hint_textview);
	}
}
