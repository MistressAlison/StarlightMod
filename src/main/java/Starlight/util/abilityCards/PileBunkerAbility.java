/*
package Starlight.util.abilityCards;

import Starlight.cards.abstracts.AbstractAbilityCard;
import Starlight.util.AbilityManager;

import static Starlight.TheStarlightMod.makeID;

public class PileBunkerAbility extends AbstractAbilityCard {
    public final static String ID = makeID(PileBunkerAbility.class.getSimpleName());
    public static final AbilityManager.AbilityType TYPE = AbilityManager.AbilityType.PILE_BUNKER;

    public PileBunkerAbility() {
        super(ID, TYPE);
        baseInfo = info = TYPE.scale();
        baseSecondMagic = secondMagic = AbilityManager.getAbilityLevel(TYPE) * TYPE.scale();
    }

    @Override
    public void upp() {
        upgradeSecondMagic(TYPE.scale());
    }

    @Override
    public void upgradeAbility() {
        AbilityManager.addAbilityLevel(TYPE, 1);
    }
}
*/
