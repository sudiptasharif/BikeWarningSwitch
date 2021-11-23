public class Trial {
    private static final String TAG = "Trial";
    private long t2;
    private long t3;

    public Trial() {
        t2 = SUtil.DEFAULT_MILLISECOND;
        t3 = SUtil.DEFAULT_MILLISECOND;
    }

    public void setT2(long t2) {
        this.t2 = t2;
    }

    public void setT3(long t3) {
        this.t3 = t3;
    }

    public void setT3(String t3) {
        if (t3 != null)
            setT3(Long.parseLong(t3));
        else
            setT3(0);
    }

    public void printTrial(int trialNumber) {
        String t2 = getTInSecAndMillis(this.t2);
        String t3 = getTInSecAndMillis(this.t3);
        String formatString = "%10d %10s %20s%n";
        System.out.format(formatString, (trialNumber+1), t2, t3);
    }

    public void printTsInSecAndMillis() {
        String t2 = getTInSecAndMillis(this.t2);
        String t3 = getTInSecAndMillis(this.t3);
        String formatString = "T2: %10s%nT3: %10s%n";
        System.out.format(formatString, t2, t3);
    }

    public String getTInSecAndMillis(long t) {
        //return SUtil.formatDate(t, SUtil.DATE_FORMAT_ss_SSS);
        return SUtil.formatDate(t, SUtil.DATE_FORMAT_HH_MM_ss_SSS);
    }

}
