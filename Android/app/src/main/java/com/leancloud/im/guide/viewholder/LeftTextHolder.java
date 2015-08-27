package com.leancloud.im.guide.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.leancloud.im.guide.Constants;
import com.leancloud.im.guide.R;
import com.leancloud.im.guide.activity.AVSingleChatActivity;

import java.text.SimpleDateFormat;

import de.greenrobot.event.EventBus;

/**
 * Created by wli on 15/8/13.
 * 聊天时居左的文本 holder
 */

public class LeftTextHolder extends AVCommonViewHolder {

  private TextView timeView;
  private TextView contentView;
  private TextView nameView;

  public LeftTextHolder(Context context, ViewGroup root) {
    super(context, root, R.layout.chat_left_text_view);
  }

  @Override
  public void findView() {
    timeView = (TextView) itemView.findViewById(R.id.chat_left_text_tv_time);
    nameView = (TextView) itemView.findViewById(R.id.chat_left_text_tv_name);
    contentView = (TextView) itemView.findViewById(R.id.chat_left_text_tv_content);

    nameView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        LeftChatItemClickEvent clickEvent = new LeftChatItemClickEvent();
        clickEvent.userId = nameView.getText().toString();
        EventBus.getDefault().post(clickEvent);
      }
    });
  }

  @Override
  public void bindData(Object o) {
    AVIMMessage message = (AVIMMessage)o;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    String time = dateFormat.format(message.getTimestamp());

    String content =  getContext().getString(R.string.unspport_message_type);
    if (message instanceof AVIMTextMessage) {
      content = ((AVIMTextMessage)message).getText();
    }

    contentView.setText(content);
    timeView.setText(time);
    nameView.setText(message.getFrom());
  }

  public void showTimeView(boolean isShow) {
    timeView.setVisibility(isShow ? View.VISIBLE : View.GONE);
  }
}