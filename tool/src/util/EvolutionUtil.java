package util;

import entity.EvoInfo;
import entity.LifeInfo;

import java.util.*;

public class EvolutionUtil {

    public String[] cellsNumStr;

    public static List<Integer> positionList = new ArrayList();
    public Random random = new Random();
    private final String blank = "000000000";

    static {
        positionList.add(1);
        positionList.add(2);
        positionList.add(3);
        positionList.add(4);
        positionList.add(5);
        positionList.add(6);
        positionList.add(7);
        positionList.add(8);
        positionList.add(9);
        positionList.add(10);
        positionList.add(11);
        positionList.add(12);
        positionList.add(13);
        positionList.add(14);
        positionList.add(15);
        positionList.add(16);
    }

    public LifeInfo calculate(String numStr) {
        LifeInfo lifeInfo = new LifeInfo();
        GeneStepUtilHashSet geneStepUtil = new GeneStepUtilHashSet();
        List<int[]> litGrids = ListGrids.getListGrids(0, numStr, 12);
        Iterator iterator = litGrids.iterator();

        while (iterator.hasNext()) {
            int[] litGrid = (int[]) iterator.next();
            geneStepUtil.addCell(litGrid[0], litGrid[1]);
        }
        Integer sum = 0;
        System.out.println("evolution start:" + numStr + " time:" + new Date());
        Set<GeneStepUtilHashSet.Cell> step;
        List<EvoInfo> evoInfos = new ArrayList<>();
        EvoInfo evoInfo;
        for (int i = 1; i <= 8640; ++i) {
            step = geneStepUtil.step();
            evoInfo = new EvoInfo();
            evoInfo.setAlgebra(i);
            evoInfo.setLivingCells(step.size());
            evoInfos.add(evoInfo);
            sum = sum + step.size();
            System.out.println("Generation-" + i + ":Number of living cells:" + step.size() + ",count power value:" + sum);
        }
        System.out.println("evolution end:" + numStr + " time:" + new Date());
        lifeInfo.setEvoInfos(evoInfos);
        return lifeInfo;
    }


    /**
     * generate life numStr
     */
    public String generateLifeNumStr(List<String> cells) {
        List<Integer> po = new ArrayList();
        po.addAll(positionList);
        List<Integer> nPosition = getNPosition(po, cells.size());
        Collections.shuffle(nPosition);
        HashMap<Integer, String> map = new HashMap();
        for (int i = 0; i < nPosition.size(); ++i) {
            map.put(nPosition.get(i), cells.get(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= 16; j++) {
            if (null == map.get(j)) {
                sb.append(blank);
            } else {
                sb.append((map.get(j)));
            }
        }
        return sb.toString();
    }

    /**
     * random get N position
     */
    public List<Integer> getNPosition(List<Integer> list, int n) {
        List<Integer> result = new ArrayList();
        int times = n;
        for (int i = 0; i < times; i++) {
            int num = random.nextInt(list.size());
            result.add(list.get(num));
            list.remove(num);
        }
        return result;
    }

    /**
     * random get N cell
     */

    public List<String> getNCell(String[] set, int n) {
        List<String> result = new ArrayList();
        int times = n;
        for (int i = 0; i < times; ++i) {
            int num = new Random().nextInt(set.length);
            result.add(set[num]);
        }
        return result;
    }
}
