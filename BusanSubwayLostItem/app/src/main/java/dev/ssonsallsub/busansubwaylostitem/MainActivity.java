package dev.ssonsallsub.busansubwaylostitem;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TEXTVIEW_ID = "text view id";
    EditText userKeyword;
    String startDate = "", endDate = "", startYspinner = "", startMspinner = "", startDspinner = "", endYspinner = "", endMspinner = "", endDspinner = "";
    String showStartDate = "" ,showEndDate = "";
    Gson gson;
    static ArrayList<LostData> lostDataList;
    LinearLayout resultLayout;
    TextView totalCnt, showStart, showEnd;
    static Integer resultTvId;
    URL url;
    static ArrayList<Integer> idForDetail1, idForDetail2;
    Spinner spinnerStartY, spinnerStartM, spinnerStartD, spinnerEndY, spinnerEndM, spinnerEndD;
    //ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userKeyword = findViewById(R.id.userKeyword);
        resultLayout = findViewById(R.id.resultLayout);
        totalCnt = findViewById(R.id.totalCnt);
        showStart = findViewById(R.id.showStartDate);
        showEnd = findViewById(R.id.showEndDate);
        resultTvId = 0;
        spinnerStartY = findViewById(R.id.spinnerYearStart);
        spinnerStartM = findViewById(R.id.spinnerMonthStart);
        spinnerStartD = findViewById(R.id.spinnerDayStart);
        spinnerEndY = findViewById(R.id.spinnerYearEnd);
        spinnerEndM = findViewById(R.id.spinnerMonthEnd);
        spinnerEndD = findViewById(R.id.spinnerDayEnd);


        spinnerStartY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                startYspinner = (String) adapterView.getItemAtPosition(i);
                showStartDate = startYspinner + startMspinner + startDspinner;
                showStart.setText(showStartDate);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStartM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                startMspinner = (String) adapterView.getItemAtPosition(i);
                showStartDate = startYspinner + startMspinner + startDspinner;
                showStart.setText(showStartDate);
//                startDate = startYspinner.substring(0,4) + startMspinner.substring(0,2) + startDspinner.substring(0,2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStartD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                startDspinner = (String) adapterView.getItemAtPosition(i);
                showStartDate = startYspinner + startMspinner + startDspinner;
                showStart.setText(showStartDate);
      //          startDate = startYspinner.substring(0,4) + startMspinner.substring(0,2) + startDspinner.substring(0,2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEndY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                endYspinner = (String) adapterView.getItemAtPosition(i);
                showEndDate = endYspinner + endMspinner + endDspinner;
                showEnd.setText(showEndDate);
       //         endDate = endYspinner.substring(0,4) + endMspinner.substring(0,2) + endDspinner.substring(0,2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEndM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                endMspinner = (String) adapterView.getItemAtPosition(i);
                showEndDate = endYspinner + endMspinner + endDspinner;
                showEnd.setText(showEndDate);
        //        endDate = endYspinner.substring(0,4) + endMspinner.substring(0,2) + endDspinner.substring(0,2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEndD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                endDspinner = (String) adapterView.getItemAtPosition(i);
                showEndDate = endYspinner + endMspinner + endDspinner;
                showEnd.setText(showEndDate);
         //       endDate = endYspinner.substring(0,4) + endMspinner.substring(0,2) + endDspinner.substring(0,2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lostDataList = new ArrayList<>();
        idForDetail1 = new ArrayList<>();
        idForDetail2 = new ArrayList<>();

    }

    public void convertDateToInt() {
        startDate = startYspinner.substring(0,4) + startMspinner.substring(0,2) + startDspinner.substring(0,2);
        endDate = endYspinner.substring(0,4) + endMspinner.substring(0,2) + endDspinner.substring(0,2);
    }

    public void goToDetailInfo(View view) {
        Intent intent = new Intent(this, DetailInfoActivity.class);
        intent.putExtra(TEXTVIEW_ID, view.getId());
        startActivity(intent);
    }

    public void btnSearchClicked(View v) { //버튼 클릭
        try{
            convertDateToInt();
            new GetDataTask().execute();
        }catch (Exception e){}
    }

    class GetDataThread extends Thread {

        @Override
        public void run() {

            Integer pageNumber = 1;
            String inputTmp = "";
            StringBuffer inputData = new StringBuffer();
            LostData lostData;
            try {
                while (true) {
                    url = new URL("http://data.humetro.busan.kr/voc/api/open_api_lostinfo.tnn?act=json&serviceKey=7qaj9SuiNNGjLz8tAtAcF2g1ffdaTKABk3NtQtmsN71JtjhKfeU33uRWDp3QCXYva%2BxBcccl8jRr3rW%2Bpo9qoA%3D%3D&pageNo=" + pageNumber + "&numOfRows=10&sdate=" + startDate + "&edate=" + endDate);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));
                    while ((inputTmp = br.readLine()) != null) {
                        inputData.append(inputTmp);
                    }
                    gson = new Gson(); //gson으로 파싱해서 데이터 저장하기
                    lostData = gson.fromJson(inputData.toString(), LostData.class);
                    lostDataList.add(lostData);
                    inputData.setLength(0);
                    if (pageNumber * 10 >= Integer.parseInt(lostDataList.get(0).getResponse().getBody().getTotalCount().toString())) {
                        br.close();
                        break;
                    }
                    pageNumber++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class GetDataTask extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            resultLayout.removeAllViews();
        }

        @Override
        protected Boolean doInBackground(Object[] objects) {
            try {


                lostDataList.clear();
                idForDetail1.clear();
                idForDetail2.clear();
                resultTvId = 0;

                publishProgress();
                GetDataThread getDataThread = new GetDataThread();
                getDataThread.start(); //데이터 받아오기
                try {
                    getDataThread.join(); //인풋 값에 대한 결과 값을 모두 받을 때까지 기다렸다가 메인쓰레드 계속 진행
                } catch (Exception e) {
                }

            }catch (Exception e){}
            return true;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            ProgressBar p = new ProgressBar(MainActivity.this);
            TextView pt = new TextView(MainActivity.this);
            pt.setText("...데이터가 많으면 시간이 많이 걸립니다...");
            pt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            resultLayout.addView(p);
            resultLayout.addView(pt);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try{
                resultLayout.removeAllViews();
                resultLayout.addView(totalCnt);
                for (int i = 0; i < lostDataList.size(); i++) {
                    for (int j = 0; j < lostDataList.get(i).getResponse().getBody().getItem().size(); j++) {
                        String dest = lostDataList.get(i).getResponse().getBody().getItem().get(j).getDest().toString();
                        String title = lostDataList.get(i).getResponse().getBody().getItem().get(j).getTitle().toString();
                        String findPlace = lostDataList.get(i).getResponse().getBody().getItem().get(j).getFindplace().toString();
                        String findDate = lostDataList.get(i).getResponse().getBody().getItem().get(j).getFinddate().toString();
                        if (lostDataList.get(i).getResponse().getBody().getItem().get(j).getTitle().toString().contains(userKeyword.getText().toString()) && !lostDataList.get(i).getResponse().getBody().getItem().get(j).getState().equals("2")) {
                            TextView tv = new TextView(MainActivity.this);
                            tv.setId(resultTvId);
                            tv.setText("역 : " + dest + "\n" + "분실물 종류 : " + title + "\n" + "발견장소 : " + findPlace + "\n" + "발견일시 : " + findDate);
                            tv.setClickable(true);
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    goToDetailInfo(view);
                                }
                            });
                            tv.setBackgroundResource(R.drawable.result_border);
                            idForDetail1.add(i);
                            idForDetail2.add(j);
                            resultTvId++;
                            resultLayout.addView(tv);
                        }
                    }
                }
                totalCnt.setText("총 데이터 수 : " + resultTvId.toString());
            } catch (Exception e) {
                totalCnt.setText("총 데이터 수 : " + resultTvId.toString());
                e.printStackTrace();
            }
        }
    }

}
