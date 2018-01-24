package hackers.smartalarmproject;

import android.content.Intent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;

public class ReadingView extends AppCompatActivity {
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_view);

        speechRecognizer = createSpeechRecognizer(this);
    }

    public void startReading(View view) {
        speechRecognizer.startListening(new Intent());
    }
}
