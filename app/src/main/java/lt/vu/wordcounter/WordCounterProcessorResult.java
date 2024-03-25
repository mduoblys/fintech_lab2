package lt.vu.wordcounter;

public class WordCounterProcessorResult {
    private String resultName;
    private int calculationResult;

    public WordCounterProcessorResult(String resultName, int calculationResult) {
        this.resultName = resultName;
        this.calculationResult = calculationResult;
    }

    public String formatResult()
    {
        return String.format(
                "%s: %d",
                resultName,
                calculationResult
        );
    }
}
