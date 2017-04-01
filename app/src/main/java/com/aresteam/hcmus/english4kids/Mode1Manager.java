package com.aresteam.hcmus.english4kids;

/**
 * Created by Wolf on 21-Jun-16.
 */
public class Mode1Manager extends StageInfo {
    private int stageId;
    private int background;
    private int backgroundComplete;
    private int sound;
    private int btnCount;
    private Points btnPoints;

    public static final Mode1Manager[] stages = {
            new Mode1Manager(MODE1_STAGE0, BALLOON_BACKGROUND, BALLOON_COMPLETE, BALLOON_SOUND, Points.stages[Points.BALLOON]),
            new Mode1Manager(MODE1_STAGE1, BEAR_BACKGROUND, BEAR_COMPLETE, BEAR_SOUND, Points.stages[Points.BEAR]),
            new Mode1Manager(MODE1_STAGE2, CAKE_BACKGROUND, CAKE_COMPLETE, CAKE_SOUND, Points.stages[Points.CAKE]),
            new Mode1Manager(MODE1_STAGE3, CAR_BACKGROUND, CAR_COMPLETE, CAR_SOUND, Points.stages[Points.CAR]),
            new Mode1Manager(MODE1_STAGE4, DEER_BACKGROUND, DEER_COMPLETE, DEER_SOUND, Points.stages[Points.DEER]),
            new Mode1Manager(MODE1_STAGE5, DINOSAUR_BACKGROUND, DINOSAUR_COMPLETE, DINOSAUR_SOUND, Points.stages[Points.DINOSAUR]),
            new Mode1Manager(MODE1_STAGE6, FOX_BACKGROUND, FOX_COMPLETE, FOX_SOUND, Points.stages[Points.FOX]),
            new Mode1Manager(MODE1_STAGE7, GUITAR_BACKGROUND, GUITAR_COMPLETE, GUITAR_SOUND, Points.stages[Points.GUITAR]),
            new Mode1Manager(MODE1_STAGE8, PLANE_BACKGROUND, PLANE_COMPLETE, PLANE_SOUND, Points.stages[Points.PLANE]),
            new Mode1Manager(MODE1_STAGE9, SPACESHIP_BACKGROUND, SPACESHIP_COMPLETE, SPACESHIP_SOUND, Points.stages[Points.SPACESHIP])
    };

    private Mode1Manager(int stageId, int background, int backgroundComplete, int sound, Points btnPoints) {
        this.stageId = stageId;
        this.background = background;
        this.backgroundComplete = backgroundComplete;
        this.sound = sound;
        this.btnPoints = btnPoints;
        this.btnCount = btnPoints.size();
    }

    public int getStageId() {
        return stageId;
    }

    public int getBackground() {
        return background;
    }

    public int getBackgroundComplete() {
        return backgroundComplete;
    }

    public int getBtnCount() {
        return btnCount;
    }

    public Points getBtnPoints() {
        return btnPoints;
    }

    public int getSound() {
        return sound;
    }
}
