package com.aresteam.hcmus.english4kids;

/**
 * Created by Wolf on 23-Jun-16.
 */
public class Mode2Manager extends StageInfo {

    public static final int[] butterflyIds = {b, u, t, t, e, r, f, l, y};
    public static final int[] chickenIds = {c, h, i, c, k, e, n};
    public static final int[] dogIds = {d, o, g};
    public static final int[] fishIds = {f, i, s, h};
    public static final int[] horseIds = {h, o, r, s, e};
    public static final int[] mouseIds = {m, o, u, s, e};
    public static final int[] parrotIds = {p, a, r, r, o, t};
    public static final int[] pigIds = {p, i, g};
    public static final int[] sheepIds = {s, h, e, e, p};
    public static final int[] turtleIds = {t, u, r, t, l, e};

    private int stageId;
    private int background;
    private int charCount;
    private int sound;
    private int[] charResourceIds;

    public static final Mode2Manager[] stages = {
            new Mode2Manager(MODE2_STAGE0, BUTTERFLY, BUTTERFLY_SOUND, butterflyIds),
            new Mode2Manager(MODE2_STAGE1, CHICKEN, CHICKEN_SOUND,  chickenIds),
            new Mode2Manager(MODE2_STAGE2, DOG, DOG_SOUND, dogIds),
            new Mode2Manager(MODE2_STAGE3, FISH, FISH_SOUND, fishIds),
            new Mode2Manager(MODE2_STAGE4, HORSE, HORSE_SOUND, horseIds),
            new Mode2Manager(MODE2_STAGE5, MOUSE, MOUSE_SOUND, mouseIds),
            new Mode2Manager(MODE2_STAGE6, PARROT, PARROT_SOUND, parrotIds),
            new Mode2Manager(MODE2_STAGE7, PIG, PIG_SOUND, pigIds),
            new Mode2Manager(MODE2_STAGE8, SHEEP, SHEEP_SOUND, sheepIds),
            new Mode2Manager(MODE2_STAGE9, TURTLE, TURTLE_SOUND, turtleIds)
    };

    private Mode2Manager(int stageId, int background, int sound,  int[] charResourceIds) {
        this.stageId = stageId;
        this.background = background;
        this.sound = sound;
        this.charResourceIds = charResourceIds;
        this.charCount = charResourceIds.length;
    }

    public int getStageId() {
        return stageId;
    }

    public int getBackground() {
        return background;
    }

    public int getCharCount() {
        return charCount;
    }

    public int[] getCharResourceIds() {
        return charResourceIds;
    }

    public int getSound() {
        return sound;
    }
}
