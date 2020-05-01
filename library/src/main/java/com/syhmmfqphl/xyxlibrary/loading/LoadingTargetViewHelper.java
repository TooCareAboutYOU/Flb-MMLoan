/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.syhmmfqphl.xyxlibrary.loading;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmkjflb.xyxlibrary.R;

public class LoadingTargetViewHelper {

	private IVaryViewHelper helper;

	public LoadingTargetViewHelper(View view) {
		this(new com.syhmmfqphl.xyxlibrary.loading.VaryViewHelper(view));
	}

	public LoadingTargetViewHelper(IVaryViewHelper helper) {
		super();
		this.helper = helper;
	}

	public void loadingError(View.OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.message);
		LinearLayout linearLayout = layout.findViewById(R.id.ll_message);
		if (null != onClickListener) {
			linearLayout.setOnClickListener(onClickListener);
		}
		helper.showLayout(layout);
	}

	public void loadingEmpty(int resources) {
		View layout = helper.inflate(R.layout.empty_data);
		TextView textView = layout.findViewById(R.id.tv_empty_data);
		textView.setText(resources);
		helper.showLayout(layout);
	}

	public void showLoading() {
		View layout = helper.inflate(R.layout.main_loading);
		helper.showLayout(layout);
	}

	public void hideLoading() {
		helper.hideView();
	}
}
