import data.CellData;
import util.EvolutionUtil;
import util.GeneStepUtilHashSet;
import util.ListGrids;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * evolution tool
     *
     * @param args
     */
    public static void main(String[] args) {
        int threadCount = Runtime.getRuntime().availableProcessors() / 4;
        EvolutionUtil evolutionUtil = new EvolutionUtil();
        Random random = new Random();
        String[] cellNumStrSet = CellData.cellData.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ExecutorService evolutionService = new ThreadPoolExecutor(threadCount, threadCount, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int j = 0; j < threadCount; j++) {
            evolutionService.execute(() -> {
                String filePath = "result" + Thread.currentThread().getId() + ".txt";
                while (true) {
                    boolean valid = false;
                    List<String> nCell = evolutionUtil.getNCell(cellNumStrSet, random.nextInt(15) + 2);
                    String lifeNumStr = evolutionUtil.generateLifeNumStr(nCell);
                    GeneStepUtilHashSet geneStepUtil = new GeneStepUtilHashSet();
                    List<int[]> litGrids = ListGrids.getListGrids(0, lifeNumStr, 12);
                    Iterator iterator = litGrids.iterator();
                    while (iterator.hasNext()) {
                        int[] litGrid = (int[]) iterator.next();
                        geneStepUtil.addCell(litGrid[0], litGrid[1]);
                    }
                    Integer sum = 0;
                    System.out.println("evolution start:" + sdf.format(new Date()));
                    Set<GeneStepUtilHashSet.Cell> step;
                    for (int i = 1; i <= 8640; ++i) {
                        step = geneStepUtil.step();
                        sum = sum + step.size();
                    }
                    System.out.println("evolution end :" + sdf.format(new Date()));
                    if (sum > 18329471l) {
                        valid = true;
                    }
                    if (valid) {
                        String content = "Gene-" + lifeNumStr + ":30D HashRate:" + sum + "; result:" + valid;
                        System.out.println(content);
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                            writer.write(content);
                            writer.newLine();
                            System.out.println("Content written to file: " + content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Not meeting the conditions, abandon");
                    }
                }
            });
        }

    }
}
