/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.util;

import javax.swing.JComponent;
import javax.swing.RepaintManager;

/**
 *
 * @author BuzzW
 */
public class NullRepaintManager extends RepaintManager {

    public void addDirtyRegion(JComponent jc, int i, int i1, int i2, int i3) {
    }

    public void markCompletelyDirty(JComponent jc) {
    }

    public void paintDirtyRegions() {
    }

    public static void install() {
        RepaintManager r = new NullRepaintManager();
        r.setDoubleBufferingEnabled(false);
        RepaintManager.setCurrentManager(r);
    }

    public void addInvalidComponent(JComponent c) {
    }
}
