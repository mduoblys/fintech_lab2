package lt.vu.wordcounter;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;

public class WordCounterProcessor {
    private WordCounterCalculationType type;
    private String text;

    public WordCounterProcessor() {
    }

    public void setType(WordCounterCalculationType type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WordCounterProcessorResult calculate(Context context) throws Exception {
        if (text.length() == 0) {
            throw new Exception(context.getString(R.string.result_text_not_present));
        }

        switch (type)
        {
            case Words:
                String[] splitText = text.split(" ");
                String[] result = Arrays.stream(splitText).filter(f -> !f.isEmpty()).toArray(String[]::new);

                return new WordCounterProcessorResult(
                        context.getString(R.string.result_words),
                        result.length
                );
            case Symbols:
                return new WordCounterProcessorResult(
                        context.getString(R.string.result_symbols),
                        text.length()
                );
            default:
                throw new Exception(context.getString(R.string.result_unexpected));
        }
    }

    public static ArrayList<WordCounterCalculationTypeData> getAvailableTypesData(Context context)  {
        ArrayList<WordCounterCalculationTypeData> types = new ArrayList<>();

        types.add(new WordCounterCalculationTypeData(
                WordCounterCalculationType.Words,
                context.getString(R.string.calculation_type_words)));
        types.add(new WordCounterCalculationTypeData(
                WordCounterCalculationType.Symbols,
                context.getString(R.string.calculation_type_symbols)));

        return types;
    }
}
