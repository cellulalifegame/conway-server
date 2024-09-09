package entity;


import java.util.List;

public class LifeInfo {
    /**
     * life numStr
     */
    private String numStr;
    private List<EvoInfo> evoInfos;

    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
    }

    public List<EvoInfo> getEvoInfos() {
        return evoInfos;
    }

    public void setEvoInfos(List<EvoInfo> evoInfos) {
        this.evoInfos = evoInfos;
    }
}
