package com.github.may2beez.farmhelperv2.util;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Tuple;
import net.minecraft.world.chunk.Chunk;

import java.util.*;

public class PlotUtils {
    @Getter
    private static final HashMap<Integer, List<Tuple<Integer, Integer>>> PLOTS = new HashMap<>();
    private static final Minecraft mc = Minecraft.getMinecraft();

    static {
        for (int i = 0; i <= 24; i++) {
            PLOTS.put(i, new ArrayList<>());
        }

        PLOTS.get(21).addAll(getChunks(-15, -10, -15, -10));
        PLOTS.get(13).addAll(getChunks(-9, -4, -15, -10));
        PLOTS.get(9).addAll(getChunks(-3, 2, -15, -10));
        PLOTS.get(14).addAll(getChunks(3, 8, -15, -10));
        PLOTS.get(22).addAll(getChunks(9, 14, -15, -10));
        PLOTS.get(15).addAll(getChunks(-15, -10, -9, -4));
        PLOTS.get(5).addAll(getChunks(-9, -4, -9, -4));
        PLOTS.get(1).addAll(getChunks(-3, 2, -9, -4));
        PLOTS.get(6).addAll(getChunks(3, 8, -9, -4));
        PLOTS.get(16).addAll(getChunks(9, 14, -9, -4));
        PLOTS.get(10).addAll(getChunks(-15, -10, -3, 2));
        PLOTS.get(2).addAll(getChunks(-9, -4, -3, 2));
        PLOTS.get(0).addAll(getChunks(-3, 2, -3, 2)); // BARN
        PLOTS.get(3).addAll(getChunks(3, 8, -3, 2));
        PLOTS.get(11).addAll(getChunks(9, 14, -3, 2));
        PLOTS.get(17).addAll(getChunks(-15, -10, 3, 8));
        PLOTS.get(7).addAll(getChunks(-9, -4, 3, 8));
        PLOTS.get(4).addAll(getChunks(-3, 2, 3, 8));
        PLOTS.get(8).addAll(getChunks(3, 8, 3, 8));
        PLOTS.get(18).addAll(getChunks(9, 14, 3, 8));
        PLOTS.get(23).addAll(getChunks(-15, -10, 9, 14));
        PLOTS.get(19).addAll(getChunks(-9, -4, 9, 14));
        PLOTS.get(12).addAll(getChunks(-3, 2, 9, 14));
        PLOTS.get(20).addAll(getChunks(3, 8, 9, 14));
        PLOTS.get(24).addAll(getChunks(9, 14, 9, 14));
    }

    public static List<Tuple<Integer, Integer>> getPlotChunksBasedOnLocation(BlockPos pos) {
        Chunk chunk = mc.theWorld.getChunkFromBlockCoords(pos);
        for (Map.Entry<Integer, List<Tuple<Integer, Integer>>> entry : PLOTS.entrySet()) {
            if (entry.getValue().stream().anyMatch(t -> t.getFirst() == chunk.xPosition && t.getSecond() == chunk.zPosition)) {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }

    public static List<Tuple<Integer, Integer>> getPlotChunksBasedOnLocation() {
        return getPlotChunksBasedOnLocation(Minecraft.getMinecraft().thePlayer.getPosition());
    }

    public static int getPlotNumberBasedOnLocation(BlockPos pos) {
        Chunk chunk = mc.theWorld.getChunkFromBlockCoords(pos);
        for (Map.Entry<Integer, List<Tuple<Integer, Integer>>> entry : PLOTS.entrySet()) {
            if (entry.getValue().stream().anyMatch(t -> t.getFirst() == chunk.xPosition && t.getSecond() == chunk.zPosition)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static int getPlotNumberBasedOnLocation() {
        return getPlotNumberBasedOnLocation(Minecraft.getMinecraft().thePlayer.getPosition());
    }

    public static List<Tuple<Integer, Integer>> getPlotBasedOnNumber(int plotNumber) {
        return PLOTS.get(plotNumber);
    }

    private static List<Tuple<Integer, Integer>> getChunks(int fromX, int toX, int fromZ, int toZ) {
        List<Tuple<Integer, Integer>> chunks = new ArrayList<>();
        int maxX = Math.max(fromX, toX);
        int minX = Math.min(fromX, toX);
        int maxZ = Math.max(fromZ, toZ);
        int minZ = Math.min(fromZ, toZ);
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                chunks.add(new Tuple<>(x, z));
            }
        }
        return chunks;
    }
}
