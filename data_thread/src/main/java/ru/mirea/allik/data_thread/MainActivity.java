package ru.mirea.allik.data_thread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ru.mirea.allik.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.maintext.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.maintext.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.maintext.setText("Порядок вызова: runn1, runn2, runn3. Метод runOnUiThread позволяет выполнить код в UI-потоке " +
                        "\n Метод postDelayed используется для запуска задачи через определенное" +
                        " время (указывается в миллисекундах).\n Метод post используется для добавления задачи в очередь " +
                        "на выполнение на главном потоке без задержки.\n Последовательность запуска процессов будет " +
                        "зависеть от того, как они используются в приложении. Если требуется выполнить код " +
                        "на главном потоке, то используется метод runOnUiThread. Если требуется запустить " +
                        "задачу через некоторое время, то используется метод postDelayed. Если требуется " +
                        "добавить задачу в очередь на выполнение на главном потоке, то используется метод post");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.maintext.postDelayed(runn3, 2000);
                    binding.maintext.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}