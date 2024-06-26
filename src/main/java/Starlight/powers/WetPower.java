package Starlight.powers;

import Starlight.TheStarlightMod;
import Starlight.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WetPower extends AbstractPower implements HealthBarRenderPower {

    public static final String POWER_ID = TheStarlightMod.makeID(WetPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final Color hpBarColor = new Color(0x0763dbff);

    public WetPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.loadRegion("like_water");
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new GainBlockAction(AbstractDungeon.player, this.amount, true));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        int reduce = (int) Math.ceil(amount/2f);
        Wiz.atb(new ReducePowerAction(owner, owner, this, reduce));
    }

    /*@Override
    public void atEndOfRound() {
        int reduce = (int) Math.ceil(amount/2f);
        Wiz.atb(new ReducePowerAction(owner, owner, this, reduce));
        //Wiz.atb(new ReducePowerAction(owner, owner, this, 1));
    }*/

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int getHealthBarAmount() {
        if (!(owner instanceof AbstractPlayer) && Wiz.adp().hasPower(WhirlpoolPower.POWER_ID)) {
            return amount * Wiz.adp().getPower(WhirlpoolPower.POWER_ID).amount;
        }
        return 0;
    }

    @Override
    public Color getColor() {
        return hpBarColor;
    }
}
