package ru.mirea.allik.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.allik.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    private ActivityMainBinding binding;
    Thread mainThread = Thread.currentThread();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-03-20, НОМЕР ПО СПИСКУ: 1, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: ДРАЙВ");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = count++;
                        Log.d("ThreadProject", String.format("Запущен поток No %d студентом " +
                                "группы No %s номер по списку No %d ", numberThread, "БСБО-03-20", 1));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();
                Float days = Float.parseFloat(binding.editCountDays.getText().toString());
                Float lessons = Float.parseFloat(binding.editCountLessons.getText().toString());
                binding.textAvg.setText("Среднее количество пар в день: " + lessons / days);
            }
        });
    }

}