package Starlight.potions;

import Starlight.TheStarlightMod;
import Starlight.powers.SpellPower;
import Starlight.util.Wiz;
import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class SPPotion extends CustomPotion {


    public static final String POTION_ID = TheStarlightMod.makeID(SPPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final int EFFECT = 10;

    public SPPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOLT, PotionColor.FIRE);
        isThrown = false;
        targetRequired = false;
    }

    @Override
    public void use(AbstractCreature target) {
        Wiz.applyToSelf(new SpellPower(Wiz.adp(), potency));
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascensionLevel) {
        return EFFECT;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(
                BaseMod.getKeywordTitle(potionStrings.DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(potionStrings.DESCRIPTIONS[2].toLowerCase())
        ));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SPPotion();
    }
}