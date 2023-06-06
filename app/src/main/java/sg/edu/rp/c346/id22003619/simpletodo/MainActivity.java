package sg.edu.rp.c346.id22003619.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText etTask;
Button btnAdd;
Button btnDelete;
Button btnClear;
ArrayList<String> tasks;
ListView lvTasks;
Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask=findViewById(R.id.editTextTask);
        btnAdd=findViewById(R.id.btnAdd);
        btnDelete=findViewById(R.id.btnDelete);
        btnClear=findViewById(R.id.btnClear);
        lvTasks=findViewById(R.id.lvTasks);
        spinner=findViewById(R.id.spinner);

        tasks=new ArrayList<String>();
        ArrayAdapter aatasks=new ArrayAdapter(this, android.R.layout.simple_list_item_1,tasks);
        lvTasks.setAdapter(aatasks);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        if (tasks.size()<1) {
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTask.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter a task to add", Toast.LENGTH_SHORT).show();
                }
                else {
                    String ntask = etTask.getText().toString();
                    tasks.add(ntask);
                    etTask.setText("");
                    aatasks.notifyDataSetChanged();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etTask.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter an index number to delete", Toast.LENGTH_SHORT).show();
                 }
                else if(Integer.parseInt(etTask.getText().toString())>tasks.size()-1 || tasks.size()<0) {
                Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                 }
                else {
                    tasks.remove(Integer.parseInt(etTask.getText().toString()));
                    etTask.setText("");
                    aatasks.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                aatasks.notifyDataSetChanged();
            }
        });
    }
}