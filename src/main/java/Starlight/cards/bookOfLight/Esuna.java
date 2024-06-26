package Starlight.cards.bookOfLight;

import Starlight.actions.CleansePowerAction;
import Starlight.cards.abstracts.AbstractMagickCard;
import Starlight.util.CustomTags;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.purple.Pray;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;

import static Starlight.TheStarlightMod.makeID;

public class Esuna extends AbstractMagickCard {
    public final static String ID = makeID(Esuna.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int BLK = 9;
    private static final int UP_BLK = 3;
    private static final int REMOVE = 1;

    public Esuna() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = block = BLK;
        baseMagicNumber = magicNumber = REMOVE;
        tags.add(CustomTags.STARLIGHT_LIGHT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.atb(new VFXAction(new SanctityEffect(p.hb.cX, p.hb.cY)));
        Wiz.atb(new CleansePowerAction(p, magicNumber, c -> c.type == AbstractPower.PowerType.DEBUFF, powers -> {}));
    }

    public void upp() {
        upgradeBlock(UP_BLK);
    }

    @Override
    public String cardArtCopy() {
        return Pray.ID;
    }
}