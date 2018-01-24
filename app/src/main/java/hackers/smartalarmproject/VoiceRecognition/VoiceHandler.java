package hackers.smartalarmproject.VoiceRecognition;

import android.content.Intent;
import android.speech.RecognizerIntent;

import java.util.Locale;

import hackers.smartalarmproject.AlarmSystem.ReadingMaterial;

/**
 * Created by Will on 2018/1/14.
 */

public class VoiceHandler {
    public static Intent getVoiceHandlerIntent(ReadingMaterial readingMaterial) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                readingMaterial.getReading());
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,
                200000);
        return intent;
    }
}
