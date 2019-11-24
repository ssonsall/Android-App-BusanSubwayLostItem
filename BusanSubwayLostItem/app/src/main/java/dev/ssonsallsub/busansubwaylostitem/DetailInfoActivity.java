package dev.ssonsallsub.busansubwaylostitem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailInfoActivity extends AppCompatActivity {

    TextView resultDetail,manageNoV,destV,titleV,findPlaceV,findDateV,lostOfficeV, stateV,telNoV,contentV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        resultDetail = findViewById(R.id.resultDetail);

        manageNoV = findViewById(R.id.manageNo);
        destV = findViewById(R.id.dest);
        titleV = findViewById(R.id.title);

        findPlaceV = findViewById(R.id.findPlace);
        findDateV = findViewById(R.id.findDate);
        lostOfficeV = findViewById(R.id.lostOffice);

        stateV = findViewById(R.id.state);
        telNoV = findViewById(R.id.telNo);
        contentV = findViewById(R.id.content);

        showDetail();
    }

    public void showDetail() {
        Intent intent = getIntent();
        Integer tvId = intent.getExtras().getInt(MainActivity.TEXTVIEW_ID);
        String manageno = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getManageno().toString();
        String dest = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getDest().toString();
        String title = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getTitle().toString();
        String findPlace = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getFindplace().toString();
        String findDate = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getFinddate().toString();
        String lostOffice = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getLostoffice().toString();;
        String state = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getState().toString();;
        String telNo = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getTelno().toString();;
        String content = MainActivity.lostDataList.get(MainActivity.idForDetail1.get(tvId)).getResponse().getBody().getItem().get(MainActivity.idForDetail2.get(tvId)).getContent().toString();;


        if(state.equals("1")) state = "보관중";
        else if(state.equals("2")) state = "본인에게 전달";
        else if(state.equals("3")) state = "경찰서에 전달";
        else state = "유실물센터로 이동";

        manageNoV.setText("<관리번호>\n"+manageno);
        destV.setText("<데이터 작성한 역>\n"+dest);
        titleV.setText("<분실물 종류>\n"+title);
        findPlaceV.setText("<발견 장소>\n"+findPlace);
        findDateV.setText("<발견 일시>\n"+findDate);
        lostOfficeV.setText("<보관 중 또는 보관했던 장소>\n"+lostOffice);
        stateV.setText("<처리상태>\n"+state);
        telNoV.setText("<전화번호>\n"+telNo);
        contentV.setText("<분실물 설명>\n"+content);

        resultDetail.setBackgroundResource(R.drawable.result_border);
        manageNoV.setBackgroundResource(R.drawable.result_border);
        destV.setBackgroundResource(R.drawable.result_border);
        titleV.setBackgroundResource(R.drawable.result_border);
        findPlaceV.setBackgroundResource(R.drawable.result_border);
        findDateV.setBackgroundResource(R.drawable.result_border);
        lostOfficeV.setBackgroundResource(R.drawable.result_border);
        stateV.setBackgroundResource(R.drawable.result_border);
        telNoV.setBackgroundResource(R.drawable.result_border);
        contentV.setBackgroundResource(R.drawable.result_border);

        //resultDetail.setText("역 : " + dest + "\n" + "분실물 종류 : " + title + "\n" + "발견장소 : " + findPlace + "\n" + "발견일시 : " + findDate + "\n" + "보관장소 : " + lostOffice+ "\n" + "처리상태 : " + state  + "\n" + "전화번호 : " + telNo + "\n" + "분실물 설명 : " + content);
    }
}
