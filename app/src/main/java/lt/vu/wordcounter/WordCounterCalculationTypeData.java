package lt.vu.wordcounter;

public class WordCounterCalculationTypeData {
    private WordCounterCalculationType type;
    private String name;

    public WordCounterCalculationTypeData(WordCounterCalculationType type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public WordCounterCalculationType getType()
    {
        return this.type;
    }

    public String getName()
    {
        return this.name;
    }
}