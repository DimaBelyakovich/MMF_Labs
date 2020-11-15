import java.util.Arrays;

public class Processor {
    int p; //num of processors
    int s; //num of blocks
    int n; //num of processes
    //int[] t = {3,1,4,1,5,2,3}; //time of block 7
    int[] t = {1,3,4,5,3,2,1}; //time of block 8

    public Processor() {
        this.p = 3;
        this.s = 7;
        this.n = 7;
    }

    public int getTimeSum(){
        return Arrays.stream(t).sum();
    }

    public int getMaxTime(){
        return Arrays.stream(t).max().getAsInt();
    }
}
