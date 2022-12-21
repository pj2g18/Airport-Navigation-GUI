package controller;

import javafx.scene.control.Tooltip;
import javafx.stage.Window;
//Fixes tool tips so that window behind does not come forward when they are actiavted
//Class copied from
//https://stackoverflow.com/questions/42416738/displaying-a-tooltip-in-javafx-brings-its-stage-into-the-foreground/42461484
public class FixedTooltip extends Tooltip {

    public FixedTooltip() {
        super();
    }

    @Override
    protected void show() {
        Window owner = getOwnerWindow();
        if (owner.isFocused())
            super.show();
    }

}