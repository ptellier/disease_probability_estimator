package ui.muiswing;

import mdlaf.themes.MaterialLiteTheme;
import mdlaf.utils.MaterialColors;

import javax.swing.plaf.ColorUIResource;

public class CustomMaterialLiteTheme extends MaterialLiteTheme {

    @Override
    protected void installColor() {
        ColorUIResource secondBackground = new ColorUIResource(238, 238, 238);
        ColorUIResource disableBackground = new ColorUIResource(210, 212, 213);
        ColorUIResource accentColor = new ColorUIResource(231, 231, 232);
        ColorUIResource selectedForeground = new ColorUIResource(84, 110, 122);
        ColorUIResource selectedBackground = new ColorUIResource(220, 239, 237);
        this.backgroundPrimary = new ColorUIResource(240, 240, 240);
        this.highlightBackgroundPrimary = new ColorUIResource(0, 188, 212);

        this.textColor = new ColorUIResource(84, 110, 122);
        this.disableTextColor = new ColorUIResource(148, 167, 176);

        this.buttonBackgroundColor = new ColorUIResource(184, 216, 248);
        this.buttonBackgroundColorMouseHover = new ColorUIResource(161, 188, 215);
        this.buttonDefaultBackgroundColorMouseHover = this.buttonBackgroundColorMouseHover;
        this.buttonDefaultBackgroundColor = secondBackground;
        this.buttonDisabledBackground = disableBackground;
        this.buttonFocusColor = this.textColor;
        this.buttonDefaultFocusColor = this.buttonFocusColor;
        this.buttonBorderColor = new ColorUIResource(211, 225, 232);
        this.buttonColorHighlight = selectedBackground;

        this.selectedInDropDownBackgroundComboBox = this.buttonBackgroundColorMouseHover;
        this.selectedForegroundComboBox = this.textColor;

        this.menuBackground = this.backgroundPrimary;
        this.menuBackgroundMouseHover = this.buttonBackgroundColorMouseHover;

        this.arrowButtonColorScrollBar = this.buttonBackgroundColor;
        this.trackColorScrollBar = accentColor;
        this.thumbColorScrollBar = disableBackground;

        this.trackColorSlider = this.textColor;
        this.haloColorSlider = MaterialColors.bleach(this.highlightBackgroundPrimary, 0.5f);

        this.highlightColorTabbedPane = this.buttonColorHighlight;
        this.borderHighlightColorTabbedPane = this.buttonColorHighlight;
        this.focusColorLineTabbedPane = this.highlightBackgroundPrimary;
        this.disableColorTabTabbedPane = disableBackground;

        this.backgroundTable = this.backgroundPrimary;
        this.backgroundTableHeader = this.backgroundPrimary;
        this.selectionBackgroundTable = this.buttonBackgroundColorMouseHover;
        this.gridColorTable = this.backgroundPrimary;
        this.alternateRowBackgroundTable = this.backgroundPrimary;

        this.backgroundTextField = accentColor;
        this.inactiveForegroundTextField = this.textColor;
        this.inactiveBackgroundTextField = accentColor;
        this.selectionBackgroundTextField = selectedBackground;
        this.selectionForegroundTextField = selectedForeground;
        super.disabledBackgroudnTextField = disableBackground;
        super.disabledForegroundTextField = this.disableTextColor;
        this.inactiveColorLineTextField = this.textColor;
        this.activeColorLineTextField = this.highlightBackgroundPrimary;

        this.mouseHoverButtonColorSpinner = this.buttonBackgroundColorMouseHover;
        this.titleBackgroundGradientStartTaskPane = secondBackground;
        this.titleBackgroundGradientEndTaskPane = secondBackground;
        this.titleOverTaskPane = selectedForeground;
        this.specialTitleOverTaskPane = selectedForeground;
        this.backgroundTaskPane = this.backgroundPrimary;
        this.borderColorTaskPane = new ColorUIResource(211, 225, 232);
        this.contentBackgroundTaskPane = secondBackground;

        this.selectionBackgroundList = selectedBackground;
        this.selectionForegroundList = selectedForeground;

        this.backgroundProgressBar = disableBackground;
        this.foregroundProgressBar = this.highlightBackgroundPrimary;

        this.withoutIconSelectedBackgroundToggleButton = MaterialColors.COSMO_DARK_GRAY;
        this.withoutIconSelectedForegoundToggleButton = MaterialColors.BLACK;
        this.withoutIconBackgroundToggleButton = MaterialColors.GRAY_300;
        this.withoutIconForegroundToggleButton = MaterialColors.BLACK;

        this.colorDividierSplitPane = MaterialColors.COSMO_DARK_GRAY;
        this.colorDividierFocusSplitPane = selectedBackground;

        super.backgroundSeparator = MaterialColors.GRAY_300;
        super.foregroundSeparator = MaterialColors.GRAY_300;
    }

    public boolean getButtonBorderEnable() {
        return false;
    }

    public boolean getButtonBorderEnableToAll() {
        return false;
    }
}
