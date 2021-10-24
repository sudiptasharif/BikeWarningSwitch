public class Trial {
    private long t1;
    private long t2;
    private double t3;

    public Trial() {
        t1 = SUtil.DEFAULT_MILLISECOND;
        t2 = SUtil.DEFAULT_MILLISECOND;
        t3 = SUtil.DEFAULT_MILLISECOND;
    }

    public Trial(long t1) {

        this.t1 = t1;
        t2 = SUtil.DEFAULT_MILLISECOND;
        t3 = SUtil.DEFAULT_MILLISECOND;
    }

    public void setT1(long t1) {
        this.t1 = t1;
    }

    public void setT2(long t2) {
        this.t2 = t2;
    }

    public void setT2(String t2) {
        this.t2 = Long.parseLong(t2);
    }

    public void setT3(double t3) {
        this.t3 = t3;
    }

    public void printTrial(int trialNumber) {
        String t1 = getTInSecAndMillis(this.t1);
        String t2 = getTInSecAndMillis(this.t2);
        String t3 = Double.toString(this.t3);
        String formatString = "%10d %10s %10s %10s%n";
        System.out.format(formatString, (trialNumber+1), t1, t2, t3);
    }

    public String getTInSecAndMillis(long t) {
        double seconds = (t / SUtil.FACTOR_MILLISECOND) % SUtil.FACTOR_SECOND;
        double millis = t % SUtil.FACTOR_MILLISECOND;
        return seconds + "." + millis;
    }

}
