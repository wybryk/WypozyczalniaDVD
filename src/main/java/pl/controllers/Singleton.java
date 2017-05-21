package pl.controllers;

import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;

/**
 * Created by Mateusz on 2017-05-21.
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private KontoFx kontoFx;
    private KlientFx klientFx;

    public KontoFx getKontoFx() {
        return kontoFx;
    }

    public void setKontoFx(KontoFx kontoFx) {
        this.kontoFx = kontoFx;
    }

    public KlientFx getKlientFx() {
        return klientFx;
    }

    public void setKlientFx(KlientFx klientFx) {
        this.klientFx = klientFx;
    }
}
