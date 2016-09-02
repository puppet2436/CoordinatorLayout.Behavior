package zeno.name.behavior;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OffsetActivity extends AppCompatActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_offset);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.tv1) public void onClickText(View v)
  {
    //每次点击向下移动50
    ViewCompat.offsetTopAndBottom(v, 50);
  }

}
