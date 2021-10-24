import java.util.concurrent.TimeUnit;

public class Trial {
    private long t1;
    private long t2;
    private double t3;

    public Trial() {
        t1 = SUtil.DEFAULT_MILLISECOND;
        t2 = SUtil.DEFAULT_MILLISECOND;
        t3 = SUtil.DEFAULT_MILLISECOND;
    }

    public long getT1() {
        return t1;
    }

    public long getT2() {
        return t2;
    }

    public double getT3() {
        return t3;
    }

    public void setT1(long t1) {
        this.t1 = t1;
    }

    public void setT2(long t2) {
        this.t2 = t2;
    }

    public void setT2(String t2) {
        if (t2 != null)
            setT2(Long.parseLong(t2));
        else
            setT2(0);
    }

    public void setT3(double t3) {
        this.t3 = t3;
    }

    public void printTrial(int trialNumber) {
        String t1 = getTInSecAndMillis(this.t1);
        String t2 = getTInSecAndMillis(this.t2);
        String t3 = Double.toString(this.t3);
        String formatString = "%10d %10s %20s %10s%n";
        System.out.format(formatString, (trialNumber+1), t1, t2, t3);
    }

    public void printT1T2InSecAndMillis() {
        String t1 = getTInSecAndMillis(this.t1);
        String t2 = getTInSecAndMillis(this.t2);
        String formatString = "t1: %10s%nt2: %10s%n";
        System.out.format(formatString, t1, t2);
    }

    public String getTInSecAndMillis(long t) {
        int seconds = (int) (t / SUtil.FACTOR_MILLISECOND) % SUtil.FACTOR_SECOND;
        int millis = (int) t % SUtil.FACTOR_MILLISECOND;
        //return seconds + "." + millis;
        return Long.toString(t);
        //return String.format("%d.%d", TimeUnit.MILLISECONDS.toSeconds(t), TimeUnit.MILLISECONDS.toMillis(t));
    }

}
