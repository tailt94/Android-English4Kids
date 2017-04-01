package com.aresteam.hcmus.english4kids;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Wolf on 21-Jun-16.
 */
public class Points extends ArrayList<PointF> {
    public static final int BALLOON = 0;
    public static final int BEAR = 1;
    public static final int CAKE = 2;
    public static final int CAR = 3;
    public static final int DEER = 4;
    public static final int DINOSAUR = 5;
    public static final int FOX = 6;
    public static final int GUITAR = 7;
    public static final int PLANE = 8;
    public static final int SPACESHIP = 9;

    public static final Points[] stages = {
            new Points(BALLOON),
            new Points(BEAR),
            new Points(CAKE),
            new Points(CAR),
            new Points(DEER),
            new Points(DINOSAUR),
            new Points(FOX),
            new Points(GUITAR),
            new Points(PLANE),
            new Points(SPACESHIP)
    };


    public Points(int stageId) {
        switch(stageId) {
            case BALLOON:
                add(new PointF(961, 915));
                add(new PointF(755, 666));
                add(new PointF(629, 432));
                add(new PointF(724, 251));
                add(new PointF(961, 153));
                add(new PointF(1190, 251));
                add(new PointF(1274, 432));
                add(new PointF(1157, 666));
                break;
            case BEAR:
                add(new PointF(518, 666));
                add(new PointF(582, 446));
                add(new PointF(861, 343));
                add(new PointF(1137, 382));
                add(new PointF(1394, 493));
                add(new PointF(1335, 853));
                add(new PointF(1065, 862));
                add(new PointF(797, 853));
                add(new PointF(724, 697));
                break;
            case CAKE:
                add(new PointF(1042, 276));
                add(new PointF(911, 407));
                add(new PointF(716, 516));
                add(new PointF(735, 714));
                add(new PointF(629, 837));
                add(new PointF(800, 968));
                add(new PointF(1042, 1000));
                add(new PointF(1252, 968));
                add(new PointF(1478, 837));
                add(new PointF(1369, 714));
                add(new PointF(1385, 516));
                add(new PointF(1173, 407));
                break;
            case CAR:
                add(new PointF(311, 605));
                add(new PointF(660, 600));
                add(new PointF(1000, 544));
                add(new PointF(1302, 650));
                add(new PointF(1676, 784));
                add(new PointF(1355, 948));
                add(new PointF(1000, 916));
                add(new PointF(679, 912));
                add(new PointF(453, 934));
                add(new PointF(297, 756));
                break;
            case DEER:
                add(new PointF(1148, 212));
                add(new PointF(875, 114));
                add(new PointF(1031, 323));
                add(new PointF(872, 452));
                add(new PointF(696, 588));
                add(new PointF(819, 831));
                add(new PointF(933, 647));
                add(new PointF(1067, 828));
                add(new PointF(1204, 569));
                add(new PointF(1277, 309));
                add(new PointF(1335, 125));
                break;
            case DINOSAUR:
                add(new PointF(649, 281));
                add(new PointF(693, 463));
                add(new PointF(850, 432));
                add(new PointF(735, 591));
                add(new PointF(819, 736));
                add(new PointF(763, 848));
                add(new PointF(1053, 932));
                add(new PointF(1182, 767));
                add(new PointF(1257, 563));
                add(new PointF(1059, 574));
                add(new PointF(1023, 376));
                add(new PointF(858, 195));
                break;
            case FOX:
                add(new PointF(523, 862));
                add(new PointF(677, 666));
                add(new PointF(858, 544));
                add(new PointF(1080, 533));
                add(new PointF(1291, 404));
                add(new PointF(1388, 574));
                add(new PointF(1243, 692));
                add(new PointF(1162, 887));
                add(new PointF(917, 879));
                add(new PointF(758, 781));
                break;
            case GUITAR:
                add(new PointF(1282, 186));
                add(new PointF(1042, 340));
                add(new PointF(886, 396));
                add(new PointF(777, 530));
                add(new PointF(615, 658));
                add(new PointF(685, 839));
                add(new PointF(875, 890));
                add(new PointF(1000,722));
                add(new PointF(1112, 566));
                add(new PointF(1165, 421));
                break;
            case PLANE:
                add(new PointF(434, 519));
                add(new PointF(479, 351));
                add(new PointF(682, 452));
                add(new PointF(880, 435));
                add(new PointF(802, 259));
                add(new PointF(1129, 418));
                add(new PointF(1313, 432));
                add(new PointF(1494, 552));
                add(new PointF(1277, 586));
                add(new PointF(1059, 664));
                add(new PointF(732, 809));
                add(new PointF(830, 602));
                add(new PointF(677, 591));
                add(new PointF(479, 666));
                break;
            case SPACESHIP:
                add(new PointF(565, 895));
                add(new PointF(752, 591));
                add(new PointF(900, 449));
                add(new PointF(939, 315));
                add(new PointF(1109, 267));
                add(new PointF(1346, 161));
                add(new PointF(1257, 359));
                add(new PointF(1193, 549));
                add(new PointF(1051, 588));
                add(new PointF(889, 731));
                break;
        }
    }
}
