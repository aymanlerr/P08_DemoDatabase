package sg.edu.rp.c346.id22015131.demodatabase;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etTask, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);


        btnInsert.setOnClickListener(v -> {
            String desc = etTask.getText().toString();
            String date = etDate.getText().toString();

            DBHelper db = new DBHelper(MainActivity.this);
            db.insertTask(desc, date);

        });

        btnGetTasks.setOnClickListener(v -> {
            DBHelper db = new DBHelper(MainActivity.this);
            ArrayList<String> data = db.getTaskContent();
            ArrayList<Task> results = db.getTasks();
            db.close();

            String txt = "";
            for (int i = 0; i < data.size(); i++) {
                Log.d("Database Content", i + ". " + data.get(i));
                txt += i + ". " + data.get((i)) + "\n";
            }

            ArrayAdapter aaTasks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
            lv.setAdapter(aaTasks);

            tvResults.setText(txt);
        });
    }
}