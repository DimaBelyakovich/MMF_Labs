public class Calculations {
    public int getMinTimeAsyncProc(Processor processor){
        //p <= n
        return processor.getTimeSum() + (processor.n - 1)*processor.getMaxTime();
    }

    public int getMinTimeSyncProc(Processor processor){
        int sigmaMax = 0;

        for (int i = 0; i < processor.t.length - 1; i++) {
            int curElem = processor.t[i];
            int nextElem = processor.t[i+1];
            sigmaMax += (curElem - nextElem) > 0 ? (curElem - nextElem) : 0;
        }

        return processor.getTimeSum() + (processor.n - 1)*(processor.getMaxTime() + sigmaMax);
    }
}
